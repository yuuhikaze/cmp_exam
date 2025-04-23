USE mdb_cmp_exam;

CREATE TABLE IF NOT EXISTS auth (
    id_auth INT AUTO_INCREMENT PRIMARY KEY,
    user VARCHAR(60) NOT NULL UNIQUE,
    password VARCHAR(45) NOT NULL
);

INSERT INTO auth (user,password) VALUES ('DDR','123456'), ('Fer','654321');

CREATE TABLE IF NOT EXISTS students (
    id_students INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    surname VARCHAR(100),
    career VARCHAR(100)
);
