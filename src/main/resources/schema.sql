DROP TABLE IF EXISTS Content;

CREATE TABLE IF NOT EXISTS Content (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    description TEXT,
    status VARCHAR(20) NOT NULL,
    content_type VARCHAR(20) NOT NULL,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_updated TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    url VARCHAR(255),
    CONSTRAINT check_status CHECK (status IN ('IDEA', 'IN_PROGRESS', 'COMPLETED', 'PUBLISHED')),
    CONSTRAINT check_content_type CHECK (content_type IN ('ARTICLE', 'VIDEO', 'COURSE', 'CONFERENCE_TALK'))
    );

INSERT INTO Content (title, `description`, status, content_type, date_created, date_updated, url)
VALUES ('AI Trends in 2024', 'Exploring the latest trends in Artificial Intelligence for 2024.', 'PUBLISHED', 'ARTICLE',
        NOW(), NOW(), 'https://example.com/ai-trends-2024'),
       ('Python Programming for Beginners', 'A comprehensive video tutorial for beginners to learn Python.',
        'COMPLETED', 'VIDEO', NOW(), NOW(), 'https://example.com/python-beginners'),
       ('Mastering Web Development',
        'An in-depth web development course focusing on frontend and backend technologies.', 'IN_PROGRESS', 'COURSE',
        NOW(), NULL, 'https://example.com/web-development-course'),
       ('Future of Space Exploration', 'A conference talk on the technological advancements driving space exploration.',
        'IDEA', 'CONFERENCE_TALK', NOW(), NULL, 'https://example.com/space-exploration-talk'),
       ('Building a Startup from Scratch', 'A guide to starting a new company and navigating the startup ecosystem.',
        'COMPLETED', 'ARTICLE', NOW(), NOW(), 'https://example.com/startup-guide');
