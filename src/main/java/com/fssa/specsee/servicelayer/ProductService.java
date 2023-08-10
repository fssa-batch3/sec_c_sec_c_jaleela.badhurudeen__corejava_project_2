package com.fssa.specsee.servicelayer;

import java.sql.SQLException;

import com.fssa.specsee.daolayer.ProductDAO;
import com.fssa.specsee.exceptions.DAOException;
import com.fssa.specsee.exceptions.InvalidProductException;
import com.fssa.specsee.modelobjects.Product;
import com.fssa.specsee.validator.ProductValidator;

public class ProductService {

	private ProductService() {
		/*
		 * default constructor
		 */
	}

	/*
	 * Method to add a new product
	 */
	public static boolean addProduct(Product product) throws InvalidProductException {
		if (ProductValidator.validate(product)) {
			ProductDAO.addProduct(product);
		}
		return true;
	}

	/*
	 * Method to read and display products
	 */
	public static boolean readProduct() throws SQLException, DAOException {
		ProductDAO.readProduct();
		return true;

	}

	/*
	 * Method to update a product
	 */
	public static boolean updateProduct(Product product, int id) throws InvalidProductException, DAOException {
		if (ProductValidator.validate(product)) {
			ProductDAO.updateProduct(product, id);
		}
		return true;

	}

	/*
	 * Method to delete a product
	 */
	public static boolean deleteProduct(int id) throws DAOException {

		ProductDAO.deleteProduct(id);
		return true;

	}

	/*
	 * Method to find a product by its name
	 */
	public static boolean findProductByName(String name) throws InvalidProductException, DAOException, SQLException {
		if (ProductValidator.productNameValidator(name)) {
			ProductDAO.findProductByName(name);
		}
		return true;

	}

}
