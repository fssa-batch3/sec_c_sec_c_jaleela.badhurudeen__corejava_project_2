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
import com.fssa.specsee.modelobjects.Product;
import com.fssa.specsee.servicelayer.ProductService;

/*
 * Define the test class for ProductService
 */
class TestProductService { 
	/*
	 * Method to create a valid product for testing
	 */
	public static Product validProduct() {
		String arrr[] = { "http://example.com/image.jpg" };
		List<String> sideImgURL = new ArrayList<String>(); 
		sideImgURL.add("https://iili.io/HrkUVp4.jpg");
		sideImgURL.add("https://iili.io/HrkUVp4.jpg");
		sideImgURL.add("https://iili.io/HrkUVp4.jpg");
		sideImgURL.add("https://iili.io/HrkUVp4.jpg");
		Product product = new Product(1, "Rayan", "Light Yellow colored  Metalic glasses", ProductCategory.METALICS,
				1150.0, "http://example.com/image.jpg", sideImgURL);
		return product;
	}

	/*
	 * Test method for adding a valid product
	 */
	@Test
	void testAddProduct() throws InvalidProductException, MalformedURLException {

		Assertions.assertTrue(ProductService.addProduct(validProduct()));

	}

	/*
	 * Test method for reading products
	 */
	@Test
	void testReadProduct() throws SQLException, InvalidProductException, DAOException {
		Assertions.assertTrue(ProductService.readProduct());
	}

	/*
	 * Test method for updating a product
	 */
	@Test
	void testUpdateProduct() throws InvalidProductException, DAOException, MalformedURLException {

		Assertions.assertTrue(ProductService.updateProduct(validProduct(), 0));
	}

	/*
	 * Test method for deleting a product
	 */
	@Test
	void testDeleteProduct() throws DAOException, InvalidProductException {
		Assertions.assertTrue(ProductService.deleteProduct(1));
	}

	/*
	 * Test method for finding a product by its name
	 */
	@Test
	void testFindProductByName() throws InvalidProductException, DAOException {
		try {
			Assertions.assertTrue(ProductService.findProductByName("Nerdlane"));
		} catch (InvalidProductException | SQLException e) {
			Assertions.fail("Unexpected exception thrown: " + e.getMessage());
		}
	}
}
