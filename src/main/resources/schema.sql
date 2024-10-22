DROP TABLE IF EXISTS LEADS;
 
CREATE TABLE LEADS (
  lead_id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(200) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  mobile BIGINT NOT NULL UNIQUE,
  email VARCHAR(200) NOT NULL UNIQUE,
  location_type VARCHAR(250) NOT NULL,
  location_string VARCHAR(500) NOT NULL,
  status VARCHAR(200) NOT NULL,
  communication VARCHAR(500)
);
