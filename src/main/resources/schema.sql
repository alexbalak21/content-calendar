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