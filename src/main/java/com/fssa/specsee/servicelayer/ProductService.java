package com.fssa.specsee.servicelayer;

<<<<<<< Updated upstream
=======
import java.net.MalformedURLException;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
	public static boolean addProduct(Product product) throws InvalidProductException {
=======
	public static boolean addProduct(Product product) throws InvalidProductException, MalformedURLException {
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
	public static boolean updateProduct(Product product, int id) throws InvalidProductException, DAOException {
=======
	public static boolean updateProduct(Product product, int id) throws InvalidProductException, DAOException, MalformedURLException {
>>>>>>> Stashed changes
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
