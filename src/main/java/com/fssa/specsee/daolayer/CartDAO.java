package com.fssa.specsee.daolayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fssa.specsee.connections.ConnectionUtil;
import com.fssa.specsee.enums.OrderStatus;
import com.fssa.specsee.exceptions.DAOException;
import com.fssa.specsee.logger.Logger;
import com.fssa.specsee.modelobjects.Cart;
import com.fssa.specsee.validator.OrderErrorMessages;

public class CartDAO {

	public static void addToCart(Cart cart) throws DAOException, SQLException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			// SQL query to insert the order information into the 'orders' table
			String insertQuery = "INSERT INTO `cart` (quantity, total_amount, user_id) VALUES (?, ?, ?)";

			// Execute insert statement
			try (PreparedStatement pst = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

				pst.setString(1, cart.getQuantity() + ""); 
				pst.setDouble(2, cart.getTotalAmount());
				pst.setInt(3, cart.getUserId());
			
				int affectedRows = pst.executeUpdate();
				int cartId;
				if (affectedRows == 0) {
					throw new SQLException("Adding order failed, no rows affected.");
				}

				try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						cartId = generatedKeys.getInt(1);
						System.out.println("orderId : " + cartId);
					} else {
						throw new SQLException("Creating user failed, no ID obtained.");
					}
				}
				addCartItems(cart.getCartDetails(), cartId);
				Logger.info("row/rows affected: " + affectedRows);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(OrderErrorMessages.ORDER_CREATION_FAILED);
		}
	}

	public static void addCartItems(ArrayList<CartDetails> cartDetails, int cartId)
			throws DAOException, SQLException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			// SQL query to insert the order information into the 'orders' table
			String insertQuery = "INSERT INTO cart_details (cart_id, product_id, product_price, quantity,total_price) VALUES (?, ?, ?, ?,?)";

			// Execute insert statement 
			try (PreparedStatement pst = connection.prepareStatement(insertQuery)) {

				for (CartDetails cartDetail : cartDetails) {

					pst.setInt(1, cartId);
					pst.setInt(2, cartDetail.getProductId());
					pst.setDouble(3, cartDetail.getProductPrice());
					pst.setInt(4, cartDetail.getQuantity());
					pst.setDouble(5, cartDetail.getTotalAmount());
					pst.setString(6, OrderStatus.ORDERED + "");

					// Execute the insert statement and get the number of affected rows
					int rowsAffected = pst.executeUpdate();
				}
				Logger.info("Odered products addded to the ordered_products table successfully");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(OrderErrorMessages.ORDER_CREATION_FAILED);
		}
	}
	
	

	
}