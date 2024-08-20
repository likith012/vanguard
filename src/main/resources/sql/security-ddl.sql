-- Create the users table
CREATE TABLE users (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(500) NOT NULL,
    enabled BOOLEAN NOT NULL
) CHARACTER SET utf8 COLLATE utf8_general_ci;

-- Create the authorities table
CREATE TABLE authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username) ON DELETE CASCADE,
    UNIQUE KEY ix_auth_username (username, authority)
) CHARACTER SET utf8 COLLATE utf8_general_ci;
