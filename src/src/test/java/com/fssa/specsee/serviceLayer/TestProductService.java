package com.fssa.specsee.serviceLayer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.specsee.enums.ProductCatagory;
import com.fssa.specsee.modelObjects.Product;


public class TestProductService {
	
	public static Product validProduct() {
		String arrr[] = {"http://example.com/image.jpg"};
		List<String> sideImgURL = new ArrayList<String>();
		sideImgURL.add("https://iili.io/HrkUVp4.jpg");
		sideImgURL.add("https://iili.io/HrkUVp4.jpg");
		sideImgURL.add("https://iili.io/HrkUVp4.jpg");
		sideImgURL.add("https://iili.io/HrkUVp4.jpg");
		Product product = new Product(1, "Rayan", "Light Yellow colored  Metalic glasses", ProductCatagory.METALICS, 1150.0, "http://example.com/image.jpg" ,sideImgURL);
		return product;
	}
	

    @Test
    public void testAddProduct() {
    	
        Assertions.assertTrue(ProductService.addProduct(validProduct()));
    
    }

    @Test
    public void testReadProduct() throws IllegalArgumentException, SQLException {
        Assertions.assertTrue(ProductService.readProduct());
    }

    @Test
    public void testUpdateProduct() {
        
       	Assertions.assertTrue(ProductService.updateProduct(validProduct(), 0));    
    }
    
    @Test
    public void testDeleteProduct() {
        try {
        	Assertions.assertTrue(ProductService.deleteProduct(0));
        } catch (IllegalArgumentException e) {
        	Assertions.fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testFindProductByName() {
        try {
        	Assertions.assertTrue(ProductService.findProductByName("Nerdlane"));
        } catch (IllegalArgumentException | SQLException e) {
        	Assertions.fail("Unexpected exception thrown: " + e.getMessage());
        }
    }
}
