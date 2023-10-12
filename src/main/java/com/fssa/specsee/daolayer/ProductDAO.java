package com.fssa.specsee.daolayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fssa.specsee.connections.ConnectionUtil;
import com.fssa.specsee.enums.ProductCategory;
import com.fssa.specsee.exceptions.DAOException;
import com.fssa.specsee.exceptions.InvalidProductException;
import com.fssa.specsee.logger.Logger;
import com.fssa.specsee.modelobjects.Product;
import com.fssa.specsee.validator.ProductValidateConstants;
import com.fssa.specsee.validator.ProductValidateErrors;

/**
 * Define the ProductDAO class
 */
public class ProductDAO {

	/**
	 * main function
	 */

	// public static void main(String[] args) {
	/**
	 * adding a side image url
	 * 
	 * 
	 * List<String> sideImgURL = new ArrayList<>();
	 * sideImgURL.add(ProductValidateConstants.PRODUCT_VALIDIMAGE_URL);
	 * sideImgURL.add(ProductValidateConstants.PRODUCT_VALIDIMAGE_URL);
	 * sideImgURL.add(ProductValidateConstants.PRODUCT_VALIDIMAGE_URL);
	 * sideImgURL.add(ProductValidateConstants.PRODUCT_VALIDIMAGE_URL); /* adding
	 * new product
	 * 
	 * Product product1 = new Product();
	 * product1.setProductName(ProductValidateConstants.PRODUCT_VALID_NAME);
	 * product1.setProductDescription(ProductValidateConstants.PRODUCT_VALID_DESCRIPTION);
	 * product1.setProductPrice(ProductValidateConstants.PRODUCT_VALID_PRICE);
	 * product1.setProductMainImageUrl(ProductValidateConstants.PRODUCT_VALIDIMAGE_URL);
	 * product1.setProductCatagory(ProductCategory.COMPUTER_GLASSES);
	 * 
	 * product1.setProductSideImageURLs(sideImgURL);
	 * 
	 * }
	 * 
	 */
	/**
	 * Define a method to add a product to the database 
	 */
	public static boolean addProduct(Product product) throws InvalidProductException {  

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "INSERT INTO products(productName,productDescription,productPrice, productMainImageUrl,productCatagory) VALUES (?,?,?,?,?)";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, product.getProductName());
				preparedStatement.setString(2, product.getProductDescription());
				preparedStatement.setDouble(3, product.getProductPrice());
				preparedStatement.setString(4, product.getProductMainImageUrl());
				preparedStatement.setString(5, product.getProductCatagory().toString().toLowerCase());

				int rows = preparedStatement.executeUpdate();

				addImageUrl(product);

				return (rows > 0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InvalidProductException(ProductValidateErrors.CANNOT_ADD_PRODUCT);
		}

	}

	/**
	 * Define a method to get a product's ID by its name
	 */
	public static int getIdByProductName(String productname) throws SQLException, InvalidProductException {

		try (Connection connection = ConnectionUtil.getConnection()) {

			// Create update statement using task id
			String query = "SELECT productId FROM products WHERE productName = ? ";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, productname);
				Logger.info(preparedStatement);
				ResultSet resultSet = preparedStatement.executeQuery();

				int id = 0;
				while (resultSet.next()) {
					id = resultSet.getInt("productId");
				}
				return id;
			}
		} catch (SQLException e) {
			throw new InvalidProductException("Error getting id by name", e);
		}

	}

	/**
	 * Define a method to add image URLs for a product
	 */

	public static boolean addImageUrl(Product product) throws InvalidProductException, SQLException {

		try (Connection connection = ConnectionUtil.getConnection()) {

			int id = getIdByProductName(product.getProductName());
			for (String url : product.getProductSideImageURLs()) {
				String query = "INSERT INTO product_side_images(productId,imageURL) VALUES (?,?)";
				try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
					preparedStatement.setInt(1, id);
					preparedStatement.setString(2, url);
					preparedStatement.executeUpdate();
				}
			}
			
			
			return true;
		} catch (SQLException e) {
			throw new InvalidProductException("Error in adding  image urls ", e);
		}

	}

	
	
	//
	public static List<String> getProductSideImageUrls(int productId) throws DAOException, SQLException {
		try (Connection connection = ConnectionUtil.getConnection()) {
			List<String> urlList = new ArrayList<>();
			String query = "SELECT imageURL FROM product_side_images WHERE productID = ?";
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setInt(1, productId);
				try (ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						System.out.println(rs.getString("imageURL"));
						urlList.add(rs.getString("imageURL"));
					}
				}
			}
			
			return urlList;
		} catch (SQLException e) {
			throw new DAOException("Error getting mirror image URLs", e);
		}
	}

	/**
	 * Define a method to update a product in the database
	 */
	public static boolean updateProduct(Product product, int productId) throws DAOException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "UPDATE products SET productName = ?, productDescription =?, productPrice = ?,productMainImageUrl = ?,productCatagory = ? WHERE productId = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

				preparedStatement.setString(1, product.getProductName());
				preparedStatement.setString(2, product.getProductDescription());
				preparedStatement.setDouble(3, product.getProductPrice());
				preparedStatement.setString(4, product.getProductMainImageUrl());
				preparedStatement.setString(5, product.getProductCatagory().toString().toLowerCase());
				preparedStatement.setInt(6, productId);

				int rows = preparedStatement.executeUpdate();

				return (rows > 0);

			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}

	}

	/**
	 * Define a method to delete a product from the database
	 */
	public static boolean deleteProduct(int productId) throws DAOException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "DELETE FROM products WHERE productId = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

				preparedStatement.setInt(1, productId);

				int rows = preparedStatement.executeUpdate();

				return (rows > 0);
			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}

	}

	/**
	 * Define a method to retrieve and display all products from the database
	 */

	public static List<Product> readProduct() throws DAOException, SQLException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM products";
			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(query)) { // this will run the query and return the
					// value
					List<Product> productList = new ArrayList<>();

					while (resultSet.next()) { // printing columns until there is no values

						Product product = new Product();
						product.setProductId(resultSet.getInt("productId"));
						product.setProductName(resultSet.getString("productName"));
						product.setProductDescription(resultSet.getString("productDescription"));
						product.setProductPrice(resultSet.getDouble("productPrice"));
						product.setProductMainImageUrl(resultSet.getString("productMainImageUrl"));
						product.setProductCatagory(ProductCategory.valueOf(resultSet.getString("productCatagory")));

						productList.add(product);
					}
					return productList;
				}
			} catch (SQLException e) {

				e.printStackTrace();
				throw new DAOException(e);
			}

		}

	}

	/**
	 * Define a method to find a product by its name
	 */
	/**
	 * public List<Product> findProductByName() throws SQLException {
	 * 
	 * List<Product> productList = new ArrayList<>();
	 * 
	 * try (Connection con = ConnectionUtil.getConnection()) { final String query =
	 * "SELECT * FROM products(productName,productDescription,productPrice,
	 * productMainImageUrl,productCatagory) VALUES (?,?,?,?,?)"; try (Statement st =
	 * con.createStatement()) { try (ResultSet rs = st.executeQuery(query)) { while
	 * (rs.next()) { Product product = new Product(); rs.updateString(1,
	 * product.getProductName()); rs.updateString(2,
	 * product.getProductDescription()); rs.updateDouble(3,
	 * product.getProductPrice()); rs.updateString(4,
	 * product.getProductMainImageUrl()); rs.updateString(5,
	 * product.getProductCatagory().getCat());
	 * 
	 * }
	 * 
	 * } } } catch (Exception ex) { Logger.info(ex.getMessage()); throw new
	 * SQLException("Get All Product Details Method Is Failed"); } return
	 * productList; }
	 */

	public static List<Product> findProductByName(String productName) throws DAOException, SQLException {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM products WHERE productName = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, productName);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					List<Product> productList = new ArrayList<>();

					if (resultSet.next()) {

						Product product = new Product();
						product.setProductId(resultSet.getInt("productId"));
						product.setProductName(resultSet.getString("productName"));
						product.setProductDescription(resultSet.getString("productDescription"));
						product.setProductPrice(resultSet.getDouble("productPrice"));
						product.setProductMainImageUrl(resultSet.getString("productMainImageUrl"));
						product.setProductCatagory(ProductCategory.valueOf(resultSet.getString(6)));

						productList.add(product);
					}
					return productList;

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	/**
	 * Define a method to find products by category
	 */

	public static List<Product> findProductByCategory(ProductCategory productCatagory) throws DAOException {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "select productId,productName,productDescription,productPrice,productMainImageUrl,productCatagory from products where productCatagory=?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, productCatagory.toString().toLowerCase());
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					List<Product> productList = new ArrayList<>();
					while (resultSet.next()) {
						Product product = new Product();
						product.setProductId(resultSet.getInt("productId"));
						product.setProductName(resultSet.getString("productName"));
						product.setProductDescription(resultSet.getString("productDescription"));
						product.setProductPrice(resultSet.getDouble("productPrice"));
						product.setProductMainImageUrl(resultSet.getString("productMainImageUrl"));
						product.setProductCatagory(productCatagory.valueOf(resultSet.getString("productCatagory")));
						productList.add(product);
					}
					return productList;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	//

	public static List<Product> getAllProduct() throws DAOException, SQLException, InvalidProductException {
		// Created a List Object
		List<Product> productList = new ArrayList<>();

		try (Connection connection = ConnectionUtil.getConnection()) {
			final String query = "select productId,productName,productDescription,productPrice,productMainImageUrl,productCatagory from products";

			try (Statement st = connection.createStatement()) {
				try (ResultSet rs = st.executeQuery(query)) {

					while (rs.next()) {
						Product product = createProductFromResultSet(rs);
						productList.add(product);
					}
				}
			} catch (SQLException e) {
				throw new DAOException("Error for Get all product by store Method is Failed", e);
			}
			// Returning a product list (ArrayList).
			return productList;
		}
	}

	public static Product createProductFromResultSet(ResultSet rs)
			throws SQLException, DAOException, InvalidProductException {
		Product product = new Product();
		int productId = getIdByProductName(rs.getString("productName"));
		product.setProductId(productId);
		product.setProductName(rs.getString("productName"));
		product.setProductDescription(rs.getString("productDescription"));
		product.setProductPrice(rs.getDouble("productPrice"));
		product.setProductMainImageUrl(rs.getString("productMainImageUrl"));
		product.setProductSideImageURLs(getProductSideImageUrls(productId));
		product.setProductCatagory(ProductCategory.valueOf(rs.getString("productCatagory").toLowerCase()));

		// Returning the product object.
		return product;
	}

	// get product by id

	public static Product getProductById(int productId) throws DAOException, SQLException {
		Product product = null;
		try (Connection connection = ConnectionUtil.getConnection()) {
			try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM products WHERE productId = ?")) {
				stmt.setInt(1, productId);
				try (ResultSet rs = stmt.executeQuery()) {
					if (rs.next()) {
						product = new Product();
						product.setProductId(productId);
						product.setProductName(rs.getString("productName"));
						product.setProductDescription(rs.getString("productDescription"));
						product.setProductPrice(rs.getDouble("productPrice"));
						product.setProductMainImageUrl(rs.getString("productMainImageUrl"));
						product.setProductSideImageURLs(getProductSideImageUrls(productId));
						product.setProductCatagory(
								ProductCategory.valueOf(rs.getString("productCatagory").toLowerCase()));

					}

				}

			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return product;
	}
}
