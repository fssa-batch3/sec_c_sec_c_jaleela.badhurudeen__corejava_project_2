package com.fssa.specsee.DAOLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fssa.specsee.connections.conectionUtil;
import com.fssa.specsee.enums.ProductCatagory;
import com.fssa.specsee.modelObjects.Product;
import com.fssa.specsee.validator.ProductValidateErrors;

public class ProductDAO {

// main function

	public static void main(String[] args) throws IllegalArgumentException, SQLException {
		// adding a side image url
		List<String> sideImgURL = new ArrayList<String>();
		sideImgURL.add("https://iili.io/HrkUVp4.jpg");
		sideImgURL.add("https://iili.io/HrkUVp4.jpg");
		sideImgURL.add("https://iili.io/HrkUVp4.jpg");
		sideImgURL.add("https://iili.io/HrkUVp4.jpg");
 //adding new product
		Product product1 = new Product();
		product1.setProductName("Raybond");
		product1.setProductDescription("Pink with Red color computer glass");
		product1.setProductPrice(1500.0);
		product1.setProductMainImageUrl("https://iili.io/HrkUhj2.jpg");
		product1.setProductCatagory(ProductCatagory.COMPUTER_GLASSES);
		
		product1.setProductSideImageURLs(sideImgURL);
		//addProduct(product1);
		// deleteProduct(7);
		
		//readProduct();
//		updateProduct(product, 3);
//		findProductByName("nerdlane");
		
		//findProductByCategory(ProductCatagory.COMPUTER_GLASSES);
		
	}
	
	

// for add product 
	public static boolean addProduct(Product product) throws IllegalArgumentException {
//	

		try (Connection connection = conectionUtil.getConnection()) {
			String query = "INSERT INTO products(productName,productDescription,productPrice, productMainImageUrl,productCatagory) VALUES (?,?,?,?,?);";
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setString(1, product.getProductName());
				pst.setString(2, product.getProductDescription());
				pst.setDouble(3, product.getProductPrice());
				pst.setString(4, product.getProductMainImageUrl());
				pst.setString(5, product.getProductCatagory().getCat());

				int rows = pst.executeUpdate();
				
				addImageUrl(product);
				System.out.println("Product Added");
				if (rows > 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(ProductValidateErrors.CANNOT_ADD_PRODUCT);
		}

	}
	// get a product id by product name
	public static int getIdByProductName(String name) throws SQLException{
		
		Connection connection = null;
		try {
			// Create update statement using task id
			connection = conectionUtil.getConnection();
			String query = "SELECT productId FROM products WHERE productName = ? ";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, name);
			System.out.println(pst);
			ResultSet rs = pst.executeQuery();
			
			int id = 0;
			while (rs.next()) {
				id = rs.getInt("productId");
			}
			return id;
		} catch (SQLException e) {
			throw new IllegalArgumentException("Error getting id by name", e);
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		
	}
	

	public static boolean addImageUrl(Product product) throws IllegalArgumentException, SQLException {
		Connection connection = null;
		try {
			
			connection = conectionUtil.getConnection();
			
			int id=getIdByProductName(product.getProductName());
			for (String url : product.getProductSideImageURLs()) {
				String query = "INSERT INTO product_side_images(productId,imageURL) VALUES (?,?)";
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setInt(1,id );
				pst.setString(2, url);
				pst.executeUpdate();
				pst.close();
			}
			return true;
		} catch (SQLException e) {
			throw new IllegalArgumentException("Error in adding  image urls ", e);
		} finally {
			// close connection
			if (connection != null) {
				connection.close();
			}
		}
		
		}

	
// for update product
	public static boolean updateProduct(Product product, int productId) throws IllegalArgumentException {
//		try {
//			ProductValidator.validate(product);
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//			throw new IllegalArgumentException("Invalid employee passed to DAO Layer", e);
//		}

		try (Connection connection = conectionUtil.getConnection()) {
			String query = "UPDATE products SET productName = ? WHERE productId = ?";
			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setString(1, product.getProductName());

				pst.setInt(2, productId);

				int rows = pst.executeUpdate();
				System.out.println("Product Updated");

				if (rows > 0) {
					return true;
				} 
				else {
					return false;
				}
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		}

	}

// for delete product
	public static boolean deleteProduct(int productId) throws IllegalArgumentException {

		try (Connection connection = conectionUtil.getConnection()) {
			String query = "DELETE FROM products WHERE productId = ?";
			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setInt(1, productId);

				int rows = pst.executeUpdate();
				System.out.println("Product Deleted");

				if (rows > 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		}

	}
	// for read product

	public static boolean readProduct() throws IllegalArgumentException, SQLException {

		try (Connection connection = conectionUtil.getConnection()) {
			String query = "SELECT * FROM products";
			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(query)) { // this will run the query and return the
					// value
					while (resultSet.next()) { // printing columns until there is no values
						
						System.out.println("id: " + resultSet.getInt(1));
						System.out.println("product_name: " + resultSet.getString(2));
						System.out.println("product_description: " + resultSet.getString(3));
						System.out.println("product_price: " + resultSet.getString(4));
						System.out.println("product_main_image_url: " + resultSet.getString(5));
						System.out.println("product category: " + resultSet.getString(6));


					}
					return true;
				}
			} catch (SQLException e) {

				e.printStackTrace();
				throw new IllegalArgumentException(e);
			}

		}

		
	}

	// for find by product name

	public static boolean findProductByName(String productName) throws IllegalArgumentException, SQLException {
		try (Connection connection = conectionUtil.getConnection()) {
			String query = "SELECT * FROM products WHERE productName = ?";
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setString(1, productName);
				try (ResultSet resultSet = pst.executeQuery()) {
					if (resultSet.next()) {

						System.out.println("id: " + resultSet.getInt(1));
						System.out.println("product_name: " + resultSet.getString(2));
						System.out.println("product_description: " + resultSet.getString(3));
						System.out.println("product_price: " + resultSet.getString(4));
						System.out.println("product_main_image_url: " + resultSet.getString(5));
						System.out.println("product category: " + resultSet.getString(6));

					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IllegalArgumentException(e);
			}
		}
		return true;
	}

	
	// for find by product category

		public static boolean findProductByCategory(ProductCatagory productCatagory) throws IllegalArgumentException, SQLException {
			try (Connection connection = conectionUtil.getConnection()) {
				String query = "select * from specsee.products join specsee.product_side_images on  productCatagory = ?";
				try (PreparedStatement pst = connection.prepareStatement(query)) {
					pst.setString(1, productCatagory.getCat());
					try (ResultSet resultSet = pst.executeQuery()) {
						while (resultSet.next()) {

							System.out.println("id: " + resultSet.getInt(1));
							System.out.println("product_name: " + resultSet.getString(2));
							System.out.println("product_description: " + resultSet.getString(3));
							System.out.println("product_price: " + resultSet.getString(4));
							System.out.println("product_main_image_url: " + resultSet.getString(5));
							System.out.println("product category: " + resultSet.getString(6));
							System.out.println("product sideimage1: " + resultSet.getString(9));

						}
						return true;

					}
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IllegalArgumentException(e);
				}
			}
		}

	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

