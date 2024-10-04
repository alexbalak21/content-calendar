CREATE TABLE IF NOT EXISTS Cntent (
    id INT AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL ,
    desc TEXT,
    status VARCHAR(50) NOT NULL,
    content_type VARCHAR(50) NOT NULL,
    date_created TIMESTAMP NOT NULL,
    date_updated TIMESTAMP,
    url VARCHAR(255),
    PRIMARY KEY (id)
);

INSERT INTO Cntent (title, desc, status, content_type, date_created, date_updated, url)
VALUES
    ('Introduction to AI', 'An article about the basics of AI and its applications.', 'Published', 'Article', NOW(), NOW(), 'https://example.com/intro-to-ai'),
    ('Getting Started with Python', 'A beginner guide to Python programming.', 'Draft', 'Guide', NOW(), NULL, 'https://example.com/python-guide'),
    ('Top 10 JavaScript Libraries', 'A list of the most popular JavaScript libraries in 2024.', 'Published', 'List', NOW(), NOW(), 'https://example.com/js-libraries'),
    ('Healthy Living Tips', 'A collection of tips for a healthier lifestyle.', 'Published', 'Blog Post', NOW(), NOW(), 'https://example.com/healthy-living'),
    ('Space Exploration Advances', 'An article discussing recent advances in space exploration.', 'Draft', 'Article', NOW(), NULL, 'https://example.com/space-exploration');
