CREATE TABLE User(
                     id INT not null AUTO_INCREMENT primary key,
                     name varchar(50) not null,
                     mail varchar(50)
);

CREATE TABLE Schedule (
                          id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          userId INT NOT NULL,
                          password VARCHAR(50) NOT NULL,
                          userName VARCHAR(50) NOT NULL,
                          createAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,  -- 기본값 설정
                          updateAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          title VARCHAR(100) NOT NULL,
                          contents VARCHAR(500),
                          FOREIGN KEY (userId) REFERENCES User(id)
);
