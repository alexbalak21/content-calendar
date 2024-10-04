package dev.alex.content_calendar.repository;

import dev.alex.content_calendar.model.Content;
import dev.alex.content_calendar.model.Status;
import dev.alex.content_calendar.model.Type;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentJdbcTemplateRepository {

    private final JdbcTemplate jdbcTemplate;

    public ContentJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static Content mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Content(rs.getInt("id"),
                rs.getString("title"),
                rs.getString("desc"),
                Status.valueOf(rs.getString("status")),
                Type.valueOf(rs.getString("content_type")),
                rs.getObject("date_created", LocalDate.class),
                rs.getObject("date_updated", LocalDate.class),
                rs.getString("url"));
    }

    //GET ALL CONTENTS
    public List<Content> getAllContent() {
        String sql = "SELECT * FROM Content";
        return jdbcTemplate.query(sql, ContentJdbcTemplateRepository::mapRow);
    }

    public Optional<Content> getContentById(Integer id) {
        String sql = "SELECT * FROM Content WHERE id = ?";
        try {
            Content content = jdbcTemplate.queryForObject(sql, ContentJdbcTemplateRepository::mapRow, id);
            return Optional.ofNullable(content);  // Wrap the result in Optional
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();  // Return an empty Optional if no content is found
        }
    }

    //CREATE CONTENT
    public Integer createContent(String title, String desc, Status status, Type contentType, String URL) {
        String sql = "INSERT INTO Content (title, desc, status, content_type, date_created, URL) VALUES (?, ?, ?, ?, NOW(), ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});  // Specify the "id" column for key retrieval
            ps.setString(1, title);
            ps.setString(2, desc);
            ps.setString(3, status.name());  // Use the enum name for the database value
            ps.setString(4, contentType.name());
            ps.setString(5, URL);
            return ps;
        }, keyHolder);

        // Return the generated ID
        return keyHolder.getKey().intValue();
    }

    //UPDATE CONTENT
    public void updateContent(Integer id, String title, String desc, Status status, Type contentType, String URL) {
        String sql = "UPDATE Content SET title = ?, desc = ?, status = ?, content_type = ?, date_updated = ?, URL = ? WHERE id = ?";
        jdbcTemplate.update(sql, title, desc, status, contentType, URL, id);
    }

    //DELETE CONTENT
    public void deleteContent(Integer id) {
        String sql = "DELETE FROM Content WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
