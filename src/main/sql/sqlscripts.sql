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
