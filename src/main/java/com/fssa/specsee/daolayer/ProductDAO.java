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

/*
 * Define the ProductDAO class
 */
public class ProductDAO {

	/*
	 * main function
	 */

	public static void main(String[] args) {
		/*
		 * adding a side image url
		 * 
		 */
		List<String> sideImgURL = new ArrayList<>();
		sideImgURL.add(ProductValidateConstants.PRODUCT_VALIDIMAGE_URL);
		sideImgURL.add(ProductValidateConstants.PRODUCT_VALIDIMAGE_URL);
		sideImgURL.add(ProductValidateConstants.PRODUCT_VALIDIMAGE_URL);
		sideImgURL.add(ProductValidateConstants.PRODUCT_VALIDIMAGE_URL);
		/*
		 * adding new product
		 */
		Product product1 = new Product();
		product1.setProductName(ProductValidateConstants.PRODUCT_VALID_NAME);
		product1.setProductDescription(ProductValidateConstants.PRODUCT_VALID_DESCRIPTION);
		product1.setProductPrice(ProductValidateConstants.PRODUCT_VALID_PRICE);
		product1.setProductMainImageUrl(ProductValidateConstants.PRODUCT_VALIDIMAGE_URL);
		product1.setProductCatagory(ProductCategory.COMPUTER_GLASSES);

		product1.setProductSideImageURLs(sideImgURL);

	}

	/*
	 * Define a method to add a product to the database
	 */
	public static boolean addProduct(Product product) throws InvalidProductException {

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
				Logger.info("Product Added");
				return (rows > 0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InvalidProductException(ProductValidateErrors.CANNOT_ADD_PRODUCT);
		}

	}

	/*
	 * Define a method to get a product's ID by its name
	 */
	public static int getIdByProductName(String name) throws SQLException, InvalidProductException {

		try (Connection connection = ConnectionUtil.getConnection()) {

			// Create update statement using task id
			String query = "SELECT productId FROM products WHERE productName = ? ";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, name);
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

	/*
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

	/*
	 * Define a method to update a product in the database
	 */
	public static boolean updateProduct(Product product, int productId) throws DAOException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "UPDATE products SET productName = ? WHERE productId = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

				preparedStatement.setString(1, product.getProductName());

				preparedStatement.setInt(2, productId);

				int rows = preparedStatement.executeUpdate();
				Logger.info("Product Updated");

				return (rows > 0);

			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}

	}

	/*
	 * Define a method to delete a product from the database
	 */
	public static boolean deleteProduct(int productId) throws DAOException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "DELETE FROM products WHERE productId = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

				preparedStatement.setInt(1, productId);

				int rows = preparedStatement.executeUpdate();
				Logger.info("Product Deleted");

				return (rows > 0);
			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}

	}
	/*
	 * Define a method to retrieve and display all products from the database
	 */

	public static boolean readProduct() throws DAOException, SQLException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM products";
			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(query)) { // this will run the query and return the
					// value
					while (resultSet.next()) { // printing columns until there is no values

						Logger.info(ProductValidateConstants.PRODUCT_ID_SYSOUT_MSG + resultSet.getInt(1));
						Logger.info(ProductValidateConstants.PRODUCT_NAME_SYSOUT_MSG + resultSet.getString(2));
						Logger.info(ProductValidateConstants.PRODUCT_DESCRIPTION_SYSOUT_MSG + resultSet.getString(3));
						Logger.info(ProductValidateConstants.PRODUCT_INVALID_PRICE + resultSet.getString(4));
						Logger.info(ProductValidateConstants.PRODUCT_IMAGEURL_SYSOUT_MSG + resultSet.getString(5));
						Logger.info(ProductValidateConstants.PRODUCT_CATEGORY_SYSOUT_MSG + resultSet.getString(6));

					}
					return true;
				}
			} catch (SQLException e) {

				e.printStackTrace();
				throw new DAOException(e);
			}

		}

	}

	/*
	 * Define a method to find a product by its name
	 */

	public static boolean findProductByName(String productName) throws DAOException, SQLException {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM products WHERE productName = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, productName);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {

						Logger.info(ProductValidateConstants.PRODUCT_ID_SYSOUT_MSG + resultSet.getInt(1));
						Logger.info(ProductValidateConstants.PRODUCT_NAME_SYSOUT_MSG + resultSet.getString(2));
						Logger.info(ProductValidateConstants.PRODUCT_DESCRIPTION_SYSOUT_MSG + resultSet.getString(3));
						Logger.info(ProductValidateConstants.PRODUCT_INVALID_PRICE + resultSet.getString(4));
						Logger.info(ProductValidateConstants.PRODUCT_IMAGEURL_SYSOUT_MSG + resultSet.getString(5));
						Logger.info(ProductValidateConstants.PRODUCT_CATEGORY_SYSOUT_MSG + resultSet.getString(6));

					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException(e);
			}
		}
		return true;
	}

	/*
	 * Define a method to find products by category
	 */

	public static boolean findProductByCategory(ProductCategory productCatagory) throws DAOException, SQLException {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "select * from specsee.products join specsee.product_side_images on  productCatagory = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, productCatagory.getCat());
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {

						Logger.info(ProductValidateConstants.PRODUCT_ID_SYSOUT_MSG + resultSet.getInt(1));
						Logger.info(ProductValidateConstants.PRODUCT_NAME_SYSOUT_MSG + resultSet.getString(2));
						Logger.info(ProductValidateConstants.PRODUCT_DESCRIPTION_SYSOUT_MSG + resultSet.getString(3));
						Logger.info(ProductValidateConstants.PRODUCT_INVALID_PRICE + resultSet.getString(4));
						Logger.info(ProductValidateConstants.PRODUCT_IMAGEURL_SYSOUT_MSG + resultSet.getString(5));
						Logger.info(ProductValidateConstants.PRODUCT_CATEGORY_SYSOUT_MSG + resultSet.getString(6));
						Logger.info(ProductValidateConstants.PRODUCT_SIDEIMAGEURL_SYSOUT_MSG + resultSet.getString(9));

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
