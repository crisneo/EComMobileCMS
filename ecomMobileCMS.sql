CREATE  TABLE user (
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(45) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (username));


CREATE TABLE user_role (
  user_role_id INT(11) NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NOT NULL,
  ROLE VARCHAR(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (ROLE,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES user (username));
  
  
CREATE TABLE product (
  id INT NOT NULL AUTO_INCREMENT,
  code VARCHAR(45) NOT NULL,
  name VARCHAR(45) NOT NULL,
  description VARCHAR(500) NULL,
  imageurl VARCHAR(500) NOT NULL,
  price DOUBLE NOT NULL,
  availableunits INT NOT NULL,
  
  categoryId int NOT NULL,
  offered TINYINT(1) NULL,
  imageFile LONGBLOB NULL,
  deleted TINYINT(1) NULL,
  PRIMARY KEY (id));
 
CREATE TABLE category (
  id INT NOT NULL AUTO_INCREMENT,
  code VARCHAR(45) NULL,
  name VARCHAR(45) NULL,
  description VARCHAR(200) NULL,
  image VARCHAR(50) NULL,
  PRIMARY KEY (id)); 

  
CREATE TABLE storeinfo (
  id INT NOT NULL,
  storeName VARCHAR(50) NOT NULL,
  info LONGTEXT NULL,
  banner LONGBLOB NULL,
  logo MEDIUMBLOB NULL,
  starProduct int NULL,
  email VARCHAR(60) NULL,
  payPalAccount VARCHAR(60) NULL,
  
  
  PRIMARY KEY (storeName));
  
CREATE TABLE product_sale (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NOT NULL,
  id_product INT NOT NULL,
  date DATE NULL,
  quantity INT NULL,
  totalPrice DOUBLE NOT NULL,
  PRIMARY KEY (id),
  INDEX id_user_idx (username ASC),
  INDEX id_idx (id_product ASC),
  CONSTRAINT username
    FOREIGN KEY (username)
    REFERENCES user (username)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT id
    FOREIGN KEY (id_product)
    REFERENCES product (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
  
--default users for the database
INSERT INTO user(username,password,enabled)
VALUES ('admin','123456', TRUE);
INSERT INTO user(username,password,enabled)
VALUES ('cris','cris', TRUE);
 
INSERT INTO user_role (username, ROLE)
VALUES ('cris', 'ROLE_USER');
INSERT INTO user_role (username, ROLE)
VALUES ('admin', 'ROLE_ADMIN');

INSERT INTO category(code, name, description, image) values('default', 'default', 'default','');
INSERT INTO product(code, name, description, imageUrl, price,availableunits, categoryId, offered, imageFile ) values('default', 'default', 'default','',0,0,1,0,'');
INSERT INTO storeinfo(id, storeName, info, banner, logo, starProduct, email, payPalAccount ) values (1, 'default', 'default', '', '', 1, 'business@mail','business@mail' );
