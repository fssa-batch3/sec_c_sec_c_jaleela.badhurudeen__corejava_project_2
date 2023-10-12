package com.fssa.specsee.servicelayer;

import java.sql.SQLException;

import com.fssa.specsee.daolayer.UserDAO;
import com.fssa.specsee.exceptions.DAOException;
import com.fssa.specsee.exceptions.InvalidInputException;
import com.fssa.specsee.exceptions.ServiceException;
import com.fssa.specsee.modelobjects.User;
import com.fssa.specsee.validator.UserValidator;

public class UserService {

	static UserDAO userDAO = new UserDAO();

	public static boolean userSignUp(User user) throws ServiceException {

		try {
			if (UserValidator.validateUser(user))
				return userDAO.userRegistration(user);
		} catch (InvalidInputException | DAOException e) {
			throw new ServiceException(e.getMessage());
		}

		return false;

	}

	public static boolean deleteUser(String emailId) throws ServiceException {

		try {
			if (UserValidator.emailValidator(emailId)) {
				if (userDAO.emailExists(emailId)) {
					return userDAO.deleteUser(emailId);
				} else {
					throw new ServiceException("Email Not Found");
				}
			}

		} catch (InvalidInputException | DAOException | SQLException e) {
			throw new ServiceException(e.getMessage());
		}

		return false;

	}

	public static boolean userLogin(String emailId, String password) throws ServiceException {
		try {
			if (UserValidator.emailValidator(emailId) && UserValidator.passwordValidator(password)) {
				if (userDAO.emailExists(emailId)) {
					return userDAO.userLogin(emailId, password);
				} else {
					throw new DAOException("Email not found: " + emailId);
				}
			}
		} catch (InvalidInputException | DAOException | SQLException e) {
			throw new ServiceException(e.getMessage());
		}
		return false;
	}

	public User getUserByEmail(String emailId) throws ServiceException {
		try {
			if (UserValidator.emailValidator(emailId)) {
				if (userDAO.emailExists(emailId)) {
					return userDAO.getUserByEmail(emailId);
				} else {
					throw new DAOException("User not found for email: " + emailId);
				}
			}
		} catch (InvalidInputException | DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return null; // Invalid email format
	}

	public boolean forgotPassword(String emailId, String newPassword) throws ServiceException {

		try {
			if (UserValidator.emailValidator(emailId) && UserValidator.passwordValidator(newPassword)) {

				if (userDAO.emailExists(emailId)) {
					return userDAO.forgotPasswordInDB(emailId, newPassword);
				} else {
					throw new DAOException("Email not found: " + emailId);
				}
			}
		} catch (InvalidInputException | DAOException | SQLException e) {
			throw new ServiceException(e.getMessage());
		}

		return false;
	}

	public User login(String emailId) throws ServiceException {

		UserDAO userDao = new UserDAO();
		User user = new User();
		try {

			if (userDao.emailExists(emailId)) {
				user = userDao.login(emailId);
				return user;
			}
			else {
				
				throw new DAOException("Email already exists: " + user.getEmailId());
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	public static boolean updateUserProfile(User user) throws ServiceException {
		try {
			if (UserValidator.validateUserUpdate(user)) {
				if (userDAO.emailExists(user.getEmailId())) {
					return userDAO.updateUserProfile(user);
				} else {
					throw new DAOException("Email not found: " + user.getEmailId());
				}
			}
		} catch (DAOException | InvalidInputException e) {
			throw new ServiceException("Error updating user profile: " + e.getMessage());
		}
		return false;
	}

	public static void main(String[] args) throws ServiceException, DAOException {
		try {
			UserService userservice = new UserService();

			if(userservice.login("arul@gmail.com")!=null) {
				
				UserDAO.getUserByEmail("arul@gmail.com");
				System.out.println("email id is there");
			};

		} catch (ServiceException e) {
			System.out.println("email id is not there");

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
