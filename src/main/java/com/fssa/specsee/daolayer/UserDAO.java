package com.fssa.specsee.daolayer;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fssa.specsee.connections.ConnectionUtil;
import com.fssa.specsee.exceptions.DAOException;
import com.fssa.specsee.logger.Logger;
import com.fssa.specsee.modelobjects.User;

public class UserDAO {

	public static String hashPassword(String password) throws DAOException {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

			// Convert the byte array to a hexadecimal string
			StringBuilder sb = new StringBuilder();
			for (byte b : hashedBytes) {
				sb.append(String.format("%02x", b));
			}

			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new DAOException(e.getMessage());
		}
	}

	public boolean userRegistration(User user) throws DAOException {
		try (Connection connection = ConnectionUtil.getConnection()) {

			String insertQuery = "INSERT INTO users (email_id, user_name, password) VALUES (?, ?, ?)";
			try (PreparedStatement psmt = connection.prepareStatement(insertQuery)) {

				psmt.setString(1, user.getEmailId());
				psmt.setString(2, user.getUserName());
				psmt.setString(3, hashPassword(user.getPassword()));

				int rowAffected = psmt.executeUpdate();
				return rowAffected > 0;
			}
		} catch (SQLException e) {
			throw new DAOException("Error while registering user: " + e.getMessage());
		}
	}

	public boolean deleteUser(String emailId) throws DAOException, SQLException {
		try (Connection connection = ConnectionUtil.getConnection()) {

			String deleteQuery = "DELETE FROM users WHERE email_Id = ?";
			try (PreparedStatement psmt = connection.prepareStatement(deleteQuery)) {

				psmt.setString(1, emailId);
				int rowAffected = psmt.executeUpdate();
				return rowAffected > 0;
			}
		}
	}

	public boolean emailExists(String emailId) throws DAOException {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String selectQuery = "SELECT COUNT(*) FROM users WHERE email_Id = ?";
			try (PreparedStatement psmt = connection.prepareStatement(selectQuery)) {
				psmt.setString(1, emailId);
				try (ResultSet rs = psmt.executeQuery()) {
					if (rs.next()) {
						return rs.getInt(1) > 0;
					}
				}
			} catch (SQLException e) {
				throw new DAOException("Error while checking email existence: " + e.getMessage());
			}
		} catch (SQLException e) {
			// Handle any exceptions here, e.g., log or rethrow
			throw new DAOException("Error while getting a database connection: " + e.getMessage());
		}
		return false;
	}

	public boolean userLogin(String emailId, String password) throws DAOException, SQLException {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String selectQuery = "SELECT COUNT(*) FROM users WHERE email_Id = ? AND password = ?";
			try (PreparedStatement psmt = connection.prepareStatement(selectQuery)) {

				psmt.setString(1, emailId);
				psmt.setString(2, password);

				try (ResultSet rs = psmt.executeQuery()) {
					if (rs.next()) {
						int count = rs.getInt(1);
						return count > 0;
					}
				}
			}
		}
		return false;
	}

	public static User getUserByEmail(String emailId) throws DAOException {

		try (Connection connection = ConnectionUtil.getConnection()) {

			String selectQuery = "SELECT user_id, email_Id, user_name, password,phoneNumber,userAddress FROM users WHERE email_Id = ?";
			try (PreparedStatement psmt = connection.prepareStatement(selectQuery)) {
				psmt.setString(1, emailId);

				try (ResultSet rs = psmt.executeQuery()) {
					if (rs.next()) {
						User user = new User();
						user.setUserId(rs.getInt("user_id"));
						user.setEmailId(rs.getString("email_id"));
						user.setUserName(rs.getString("user_name"));
						user.setPassword(rs.getString("password"));
						user.setPhoneNumber(rs.getString("phoneNumber"));
						user.setUserAddress(rs.getString("userAddress"));

						Logger.info(user);
						return user;
					}
				}
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return null; // User not found
	}

	public boolean forgotPasswordInDB(String emailId, String newPassword) throws DAOException, SQLException {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String updateQuery = "UPDATE users SET password = ? WHERE email_Id = ?";
			try (PreparedStatement psmt = connection.prepareStatement(updateQuery)) {
				psmt.setString(1, newPassword);
				psmt.setString(2, emailId);

				int rowAffected = psmt.executeUpdate();
				return rowAffected > 0;
			}
		}
	}

	public static int getUserIdByEmail(String emailId) throws DAOException {
		int userId = -1; // Default value if the email is not found or an error occurs.
		try (Connection con = ConnectionUtil.getConnection()) {
			// SQL query to retrieve the user ID by email.
			String query = "SELECT user_id FROM users WHERE email_Id=?";
			try (PreparedStatement psmt = con.prepareStatement(query)) {

				// Set the email parameter in the PreparedStatement.
				psmt.setString(1, emailId);
				try (ResultSet rs = psmt.executeQuery()) {
					if (rs.next()) {
						userId = rs.getInt("user_id");
					}
				}
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return userId;
	}

	public User login(String emailId) throws DAOException {
		ConnectionUtil connectionUtil = new ConnectionUtil();
		try (Connection connection = connectionUtil.getConnection()) {
			String selectQuery = "SELECT user_id, email_id,user_name, password, phoneNumber,userAddress FROM users WHERE email_id = ?";
			try (PreparedStatement psmt = connection.prepareStatement(selectQuery)) {
				psmt.setString(1, emailId);
				try (ResultSet rs = psmt.executeQuery()) {
					if (rs.next()) {
						User user = new User();
						user.setUserId(rs.getInt("user_id"));
						user.setEmailId(rs.getString("email_id"));
						user.setUserName(rs.getString("user_name"));
						user.setPassword(rs.getString("password"));
						user.setPhoneNumber(rs.getString("phoneNumber"));
						user.setUserAddress(rs.getString("userAddress"));
						return user;
					} else {
						throw new DAOException("User not found");
					}
				}
			}
		} catch (SQLException e) {
			throw new DAOException("Database Connection Error: " + e.getMessage());
		}
	}

	public boolean updateUserProfile(User user) throws DAOException {
		String updateQuery = "UPDATE users SET user_name = ?, phoneNumber = ? , userAddress = ? WHERE email_Id = ?";
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement psmt = connection.prepareStatement(updateQuery)) {

			psmt.setString(1, user.getUserName());
			psmt.setString(2, user.getPhoneNumber());
			psmt.setString(3, user.getUserAddress());
			psmt.setString(4, user.getEmailId());

			int rowsUpdated = psmt.executeUpdate();

			return rowsUpdated > 0;

		} catch (SQLException e) {
			throw new DAOException("Error updating user profile: " + e.getMessage());
		}
	}
	
	public static void main(String[] args) throws DAOException {
		UserDAO userDao = new UserDAO();

		userDao.emailExists("bala@gmail.com");
		
		System.out.println(	userDao.emailExists("l@gmail.com"));
		
	}

}