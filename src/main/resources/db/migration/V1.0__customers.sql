-- Create the customers table
CREATE TABLE customers (
        id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
        email VARCHAR(45) NOT NULL UNIQUE,
        password VARCHAR(200) NOT NULL,
        role VARCHAR(45) NOT NULL
);