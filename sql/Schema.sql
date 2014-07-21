USE ece458;
DROP SCHEMA ece458;
CREATE SCHEMA ece458;

CREATE TABLE `data` (
	ip VARCHAR(255),
	domain_name VARCHAR(2000),
	signature VARCHAR(255),
	PRIMARY KEY (ip)
);

INSERT INTO DATA (ip,domain_name,signature) 
VALUES('74.125.226.119','google.ca',''),
('129.97.149.158','uwaterloo.ca',''),
('173.194.43.102','youtube.com',''),
('173.252.110.27','facebook.com',''),
('199.16.156.230','twitter.com',''); 