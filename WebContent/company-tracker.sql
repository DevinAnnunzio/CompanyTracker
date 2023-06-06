DROP TABLE IF EXISTS company;

CREATE TABLE company (
  id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  company_name VARCHAR(50) UNIQUE NOT NULL,
  ceo VARCHAR(50) DEFAULT NULL,
  stock_price DECIMAL(15,2) DEFAULT NULL,
  headquarters VARCHAR(80) DEFAULT NULL
);



INSERT INTO company (company_name) VALUES('Google');
INSERT INTO company (company_name) VALUES('Rakuten');
INSERT INTO company (company_name) VALUES('Facebook');
