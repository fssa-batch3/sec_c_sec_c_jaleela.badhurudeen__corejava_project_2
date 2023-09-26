package com.fssa.specsee.servicelayer;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;

import com.fssa.specsee.daolayer.ProductDAO;
import com.fssa.specsee.enums.ProductCategory;
import com.fssa.specsee.exceptions.DAOException;
import com.fssa.specsee.exceptions.InvalidProductException;
import com.fssa.specsee.exceptions.ServiceException;
import com.fssa.specsee.modelobjects.Product;
import com.fssa.specsee.validator.ProductValidator;

public class ProductService {

	public ProductService() {
		/**
		 * default constructor
		 */
	}

	/**
	 * Method to add a new product
	 */
	public static boolean addProduct(Product product) throws InvalidProductException, MalformedURLException {
		if (ProductValidator.validate(product)) {
			ProductDAO.addProduct(product);
		}
		return true;
	}
//getIdByProductName

	public static int getIdByProductName(String name)
			throws InvalidProductException, MalformedURLException, SQLException {

		return ProductDAO.getIdByProductName(name);

	}

	/**
	 * Method to read and display products
	 */
	public static List<Product> readProduct() throws SQLException, DAOException {

		return ProductDAO.readProduct();

	}

	/**
	 * Method to update a product
	 */
	public static boolean updateProduct(Product product, int id)
			throws InvalidProductException, DAOException, MalformedURLException {
		if (ProductValidator.validate(product)) {
			ProductDAO.updateProduct(product, id);
		}
		return true;

	}

	/**
	 * Method to delete a product
	 */
	public static boolean deleteProduct(int id) throws DAOException {

		ProductDAO.deleteProduct(id);
		return true;

	}

	/**
	 * Method to find a product by its category
	 * 
	 * @throws ServiceException
	 * @throws InvalidProductException 
	 * @throws SQLException 
	 * @throws DAOException 
	 */
	public static void main(String[] args) throws ServiceException, DAOException, SQLException, InvalidProductException {
		List<Product> arr = getAllProducts();
		for (Product e : arr) {
			System.out.println(e);
		}
	}

	public static List<Product> findAllProductByCategory(ProductCategory productCatagory) throws ServiceException {
		try {
			return ProductDAO.findProductByCategory(productCatagory);
		} catch (DAOException e) {
			throw new ServiceException("Error while find All Product By Category: " + e.getMessage());
		}
	}

	/**
	 * Method to find a product by its name
	 * 
	 */
	public static List<Product> findProductByName(String name)
			throws InvalidProductException, DAOException, SQLException {
		List<Product> productList = null;
		if (ProductValidator.productNameValidator(name)) {
			productList = ProductDAO.findProductByName(name);
		}
		return productList;

	}

	public static List<Product> getAllProducts() throws DAOException, SQLException, InvalidProductException {
		return ProductDAO.getAllProduct();

	}

	public static Product getProductById(int productId) throws SQLException, DAOException {
		return ProductDAO.getProductById(productId); // Get the artist's posts from the database
	}

	public List<String> getProductSideImageUrls(int id) throws DAOException, SQLException {
		return ProductDAO.getProductSideImageUrls(id);
	}

//	public List<Product> findAllProductByCategory(ProductCategory categoryName) {
//		
//		return ProductDAO.findProductByCategory(categoryName);
//	}

}
