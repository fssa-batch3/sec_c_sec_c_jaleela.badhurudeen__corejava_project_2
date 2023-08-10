package com.fssa.specsee.servicelayer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.specsee.enums.ProductCatagory;
import com.fssa.specsee.exceptions.DAOException;
import com.fssa.specsee.exceptions.InvalidProductException;
import com.fssa.specsee.modelobjects.Product;
import com.fssa.specsee.servicelayer.ProductService;

 class TestProductService { 
// test for a valid product
	public static Product validProduct() {
		String arrr[] = { "http://example.com/image.jpg" };
		List<String> sideImgURL = new ArrayList<String>();
		sideImgURL.add("https://iili.io/HrkUVp4.jpg");
		sideImgURL.add("https://iili.io/HrkUVp4.jpg");
		sideImgURL.add("https://iili.io/HrkUVp4.jpg");
		sideImgURL.add("https://iili.io/HrkUVp4.jpg");
		Product product = new Product(1, "Rayan", "Light Yellow colored  Metalic glasses", ProductCatagory.METALICS,
				1150.0, "http://example.com/image.jpg", sideImgURL);
		return product; 
	}

// test for add a valid product
	@Test
	 void testAddProduct() throws InvalidProductException {

		Assertions.assertTrue(ProductService.addProduct(validProduct()));

	}

// test for read product
	@Test
	 void testReadProduct() throws SQLException, InvalidProductException, DAOException {
		Assertions.assertTrue(ProductService.readProduct());
	}

// test for update a product
	@Test
	 void testUpdateProduct() throws InvalidProductException, DAOException {

		Assertions.assertTrue(ProductService.updateProduct(validProduct(), 0));
	}

// test for delete a product
	@Test
	 void testDeleteProduct() throws InvalidProductException, DAOException {
		try {
			Assertions.assertTrue(ProductService.deleteProduct(1));
		} catch (InvalidProductException e) {
			Assertions.fail("Unexpected exception thrown: " + e.getMessage());
		}
	}

// test for find as product by their name
	@Test
	 void testFindProductByName() throws InvalidProductException, DAOException {
		try {
			Assertions.assertTrue(ProductService.findProductByName("Nerdlane"));
		} catch (InvalidProductException | SQLException e) {
			Assertions.fail("Unexpected exception thrown: " + e.getMessage());
		}
	}
}
