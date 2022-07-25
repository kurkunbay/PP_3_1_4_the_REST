-- Table: user
CREATE TABLE user (
  id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
)
  ENGINE = InnoDB;

-- Table: role
CREATE TABLE role (
  id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL
)
  ENGINE = InnoDB;

-- Table for mapping user and roles: user_roles
CREATE TABLE user_roles (
  user_id INT NOT NULL,
  role_id INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES user (id),
  FOREIGN KEY (role_id) REFERENCES role (id),

  UNIQUE (user_id, role_id)
)
  ENGINE = InnoDB;

-- Insert data

INSERT INTO user VALUES (99, 'Admin', '$2a$10$BWTeesqrepW1G6R5ff3AyO0rrDPMhQSPH/h10V8d.yQah6je6SUVW');
INSERT INTO user VALUES (100, 'User', '$2a$10$BWTeesqrepW1G6R5ff3AyO0rrDPMhQSPH/h10V8d.yQah6je6SUVW');

INSERT INTO role VALUES (1, 'ROLE_USER');
INSERT INTO role VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_roles VALUES (99, 2);
INSERT INTO user_roles VALUES (100, 1);