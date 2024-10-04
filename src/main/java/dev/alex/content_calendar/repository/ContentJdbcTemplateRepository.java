package dev.alex.content_calendar.repository;


import dev.alex.content_calendar.model.Content;
import dev.alex.content_calendar.model.Status;
import dev.alex.content_calendar.model.Type;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

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

    public List<Content> getAllContent() {
        String sql = "SELECT * FROM Content";
        return jdbcTemplate.query(sql, ContentJdbcTemplateRepository::mapRow);
    }
}
