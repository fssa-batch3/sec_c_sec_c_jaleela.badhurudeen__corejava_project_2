package com.fssa.specsee.serviceLayer;

import java.sql.SQLException;

import com.fssa.specsee.DAOLayer.ProductDAO;
import com.fssa.specsee.exceptions.DAOException;
import com.fssa.specsee.exceptions.InvalidProductException;
import com.fssa.specsee.modelObjects.Product;
import com.fssa.specsee.validator.ProductValidator;

public class ProductService {
// to add a new product
	public static boolean addProduct(Product product) throws InvalidProductException {
		if (ProductValidator.validate(product)) {
			ProductDAO.addProduct(product);
		}
		return true; 
	} 

// to read a product
	public static boolean readProduct() throws InvalidProductException, SQLException, DAOException {
		ProductDAO.readProduct();
		return true;

	}

// to update a product 
	public static boolean updateProduct(Product product, int id) throws InvalidProductException,DAOException {
		if (ProductValidator.validate(product)) {
			ProductDAO.updateProduct(product, id);
		}
		return true;

	}

// to delete a product
	public static boolean deleteProduct(int id) throws InvalidProductException,DAOException {

		ProductDAO.deleteProduct(id);
		return true;

	}

// to find a product by their name
	public static boolean findProductByName(String name) throws InvalidProductException, DAOException,SQLException {
		if (ProductValidator.productNameValidator(name)) {
			ProductDAO.findProductByName(name);
		}
		return true;

	}

}
