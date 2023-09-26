CREATE DATABASE  jaleela_badhurudeen_corejava_project;
use jaleela_badhurudeen_corejava_project;
CREATE TABLE products (
  productId INT NOT NULL auto_increment PRIMARY KEY,
  productName VARCHAR(255) NOT NULL,
  productDescription TEXT,
  productPrice DOUBLE,
  productMainImageUrl VARCHAR(255),
  productCatagory VARCHAR(50)
);
select * from products;

USE jaleela_badhurudeen_corejava_project;
select * from products;


select products.productId, products.productName,products.productDescription,products.productPrice,products.productPrice,products.productCatagory 
from products join product_side_images on products.productId = product_side_images.productId;

CREATE TABLE product_side_images (
  imageId INT AUTO_INCREMENT PRIMARY KEY,
  productId INT,
  imageURL VARCHAR(255), 
  FOREIGN KEY (productId) REFERENCES products(productId) ON DELETE CASCADE
);
USE jaleela_badhurudeen_corejava_project;
select * from product_side_images;

SELECT * FROM jaleela_badhurudeen_corejava_project.products;
Alter table products add CONSTRAINT productCategory CHECK (productCategory IN ('computer_glasses', 'sun_glasses','power_glasses','contact_lens','metalics','rectangle','pilot'));
INSERT INTO your_table_name (productName, productDescription, productPrice, productMainImageUrl, productCategory)
VALUES ('Mayond', 'Vilot Round Type Computer Glass', 1199, 'https://iili.io/H8P04Yg.webp', 'sun_glasses');
