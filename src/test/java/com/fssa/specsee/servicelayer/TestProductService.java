package com.fssa.specsee.servicelayer;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.specsee.enums.ProductCategory;
import com.fssa.specsee.exceptions.DAOException;
import com.fssa.specsee.exceptions.InvalidProductException;
import com.fssa.specsee.exceptions.ServiceException;
import com.fssa.specsee.logger.Logger;
import com.fssa.specsee.modelobjects.Product;

/**
 * Define the test class for ProductService
 */
class TestProductService { 
	/**
	 * Method to create a valid product for testing
	 */
	public static Product validProduct() {
		String arrr[] = { "https://iili.io/H8P5lII.webp" };
		List<String> sideImgURL = new ArrayList<String>();
		sideImgURL.add("https://iili.io/H8P5M1s.webp");
		sideImgURL.add("https://iili.io/H8P58Be.webp");
		sideImgURL.add("https://iili.io/H8P54Yx.webp");
		sideImgURL.add("https://iili.io/H8P5lII.webp");
		Product product = new Product(1, "Lenzkart", "Blue with Grey color computer glass",
				ProductCategory.contact_lens, 2000.0, "https://iili.io/H8P5lII.webp", sideImgURL);
		return product;
	}

	/**
	 * Test method for adding a valid product
	 */
	@Test
	void testAddProduct() throws InvalidProductException, MalformedURLException {

		Assertions.assertTrue(ProductService.addProduct(validProduct()));

	}

	/**
	 * Test method for reading products
	 */
	@Test
	void testReadProducts() throws DAOException, SQLException, InvalidProductException {
		try {
			List<Product> productList = ProductService.getAllProducts();
			for (Product url : productList) {
				Logger.info(url);

			}
		} catch (SQLException e) {
			Assertions.fail("Error for Get all product by store Method is Failed" + e.getMessage());
		}
	}

	/**
	 * Test method for updating a product
	 */
	@Test
	void testUpdateProduct() throws InvalidProductException, DAOException, MalformedURLException {

		Assertions.assertTrue(ProductService.updateProduct(validProduct(), 23));
	}

	/**
	 * Test method for deleting a product
	 */
	@Test
	void testDeleteProduct() throws DAOException, InvalidProductException {
		Assertions.assertTrue(ProductService.deleteProduct(24));
	} 

	/**
	 * Test method for finding a product by its name
	 */

	//
	@Test
	void testFindProductByName() throws InvalidProductException, DAOException {
		try {
			List<Product> productList = ProductService.findProductByName("Nerdlane");
			for (Product ele : productList) {
				Logger.info(ele);
			}
		} catch (InvalidProductException | SQLException e) {
			Logger.info(e.getMessage());
		}
	}
	/*
	 * test find products by category method
	 */

	@Test
	void testFindProductByCategory() throws ServiceException, DAOException, SQLException, InvalidProductException {
		try {
			List<Product> productList = ProductService.findAllProductByCategory(ProductCategory.computer_glasses);
			for (Product url : productList) {
				Logger.info(url);

			}
			Assertions.assertNotNull(productList);
		} catch (ServiceException e) {
			Logger.info(e.getMessage());


		}
	}

	@Test
	void testGetAllProducts() throws DAOException, SQLException, InvalidProductException {
		try {
			List<Product> productList = ProductService.getAllProducts();
			for (Product url : productList) {
				Logger.info(url);

			}
		} catch (SQLException e) {
			Assertions.fail("Error for Get all product by store Method is Failed" + e.getMessage());
		}
	}

}
