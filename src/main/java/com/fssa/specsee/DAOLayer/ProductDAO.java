package com.fssa.specsee.DAOLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fssa.specsee.connections.ConnectionUtil;
import com.fssa.specsee.enums.ProductCatagory;
import com.fssa.specsee.exceptions.DAOException;
import com.fssa.specsee.exceptions.InvalidProductException;
import com.fssa.specsee.modelObjects.Product;
import com.fssa.specsee.validator.ProductValidateErrors;

public class ProductDAO {

// main function

	public static void main(String[] args) throws InvalidProductException, SQLException {
		// adding a side image url
		List<String> sideImgURL = new ArrayList<String>();
		sideImgURL.add("https://iili.io/HrkUVp4.jpg");
		sideImgURL.add("https://iili.io/HrkUVp4.jpg");
		sideImgURL.add("https://iili.io/HrkUVp4.jpg");
		sideImgURL.add("https://iili.io/HrkUVp4.jpg");
		// adding new product
		Product product1 = new Product();
		product1.setProductName("Raybond");
		product1.setProductDescription("Pink with Red color computer glass");
		product1.setProductPrice(1500.0);
		product1.setProductMainImageUrl("https://iili.io/HrkUhj2.jpg");
		product1.setProductCatagory(ProductCatagory.COMPUTER_GLASSES);

		product1.setProductSideImageURLs(sideImgURL);
		 addProduct(product1);
		// deleteProduct(7);

		// readProduct();
//		updateProduct(product, 3);
//		findProductByName("nerdlane");

		// findProductByCategory(ProductCatagory.COMPUTER_GLASSES);

	}

// for add product 
	public static boolean addProduct(Product product) throws InvalidProductException {
//	

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "INSERT INTO products(productName,productDescription,productPrice, productMainImageUrl,productCatagory) VALUES (?,?,?,?,?);";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, product.getProductName());
				preparedStatement.setString(2, product.getProductDescription());
				preparedStatement.setDouble(3, product.getProductPrice());
				preparedStatement.setString(4, product.getProductMainImageUrl());
				preparedStatement.setString(5, product.getProductCatagory().getCat());

				int rows = preparedStatement.executeUpdate();

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
			throw new InvalidProductException(ProductValidateErrors.CANNOT_ADD_PRODUCT);
		}

	}

	// get a product id by product name
	public static int getIdByProductName(String name) throws SQLException, InvalidProductException {

		Connection connection = null;
		try {
			// Create update statement using task id
			connection = ConnectionUtil.getConnection();
			String query = "SELECT productId FROM products WHERE productName = ? ";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			int id = 0;
			while (resultSet.next()) {
				id = resultSet.getInt("productId");
			}
			return id;
		} catch (SQLException e) {
			throw new InvalidProductException("Error getting id by name", e);
		} finally {
			if (connection != null) {
				connection.close();
			}
		}

	}

	public static boolean addImageUrl(Product product) throws InvalidProductException, SQLException {
		Connection connection = null;
		try {

			connection = ConnectionUtil.getConnection();

			int id = getIdByProductName(product.getProductName());
			for (String url : product.getProductSideImageURLs()) {
				String query = "INSERT INTO product_side_images(productId,imageURL) VALUES (?,?)";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, id);
				preparedStatement.setString(2, url);
				preparedStatement.executeUpdate();
				preparedStatement.close();
			}
			return true;
		} catch (SQLException e) {
			throw new InvalidProductException("Error in adding  image urls ", e);
		} finally {
			// close connection
			if (connection != null) {
				connection.close();
			}
		}

	}

// for update product
	public static boolean updateProduct(Product product, int productId) throws DAOException {
//		try {
//			ProductValidator.validate(product);
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//			throw new IllegalArgumentException("Invalid employee passed to DAO Layer", e);
//		}

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "UPDATE products SET productName = ? WHERE productId = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

				preparedStatement.setString(1, product.getProductName());

				preparedStatement.setInt(2, productId);

				int rows = preparedStatement.executeUpdate();
				System.out.println("Product Updated");

				if (rows > 0) {
					return true;
				} else {
					return false;
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}

	}

// for delete product
	public static boolean deleteProduct(int productId) throws DAOException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "DELETE FROM products WHERE productId = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

				preparedStatement.setInt(1, productId);

				int rows = preparedStatement.executeUpdate();
				System.out.println("Product Deleted");

				if (rows > 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}

	}
	// for read product

	public static boolean readProduct() throws DAOException, SQLException {

		try (Connection connection = ConnectionUtil.getConnection()) {
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
				throw new DAOException(e);
			}

		}

	}

	// for find by product name

	public static boolean findProductByName(String productName) throws DAOException, SQLException {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM products WHERE productName = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, productName);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
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
				throw new DAOException(e);
			}
		}
		return true; 
	}

	// for find by product category

	public static boolean findProductByCategory(ProductCatagory productCatagory)
			throws DAOException, SQLException {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "select * from specsee.products join specsee.product_side_images on  productCatagory = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, productCatagory.getCat());
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
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
				throw new DAOException(e);
			}
		}
	}

}
