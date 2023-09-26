package com.fssa.specsee.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fssa.specsee.exceptions.InvalidInputException;
import com.fssa.specsee.exceptions.InvalidProductException;
import com.fssa.specsee.modelobjects.User;

public class UserValidator {

	public static boolean validateUser(User user) throws InvalidInputException {

		if (user == null) {
			throw new InvalidInputException(UserValidateErrors.INVALID_USER_NULL);
		}

		emailValidator(user.getEmailId());
		validateUserName(user.getUserName());
		passwordValidator(user.getPassword());

		return true;
	}
	public static boolean validateUserUpdate(User user) throws InvalidInputException {

		if (user == null) {
			throw new InvalidInputException(UserValidateErrors.INVALID_USER_NULL);
		}

		emailValidator(user.getEmailId());
		validateUserName(user.getUserName());
		

		return true;
	}

	/**
	 * Useremail Validator
	 */
	public static boolean emailValidator(String email) throws InvalidInputException {
		/**
		 * firstName null and empty string check
		 */
		if (email == null || "".equals(email.trim())) {
			throw new InvalidInputException(UserValidateErrors.INVALID_EMAIL_NULL);
		}
		/**
		 * email regex pattern
		 */
		//
		String emailregex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
		Pattern pattern = Pattern.compile(emailregex);
		Matcher matcher = pattern.matcher(email);
		Boolean isMatch = matcher.matches();

		if (Boolean.FALSE.equals(isMatch)) {
			throw new InvalidInputException(UserValidateErrors.INVALID_EMAIL_FORMAT);

		}

		return true;

	}
	public static boolean validateUserName(String userName) throws InvalidInputException {
		
		if (userName == null || "".equals(userName.trim())) {
			throw new InvalidInputException(UserValidateErrors.INVALID_USERNAME_NULL);
		}
		/**
		 * regex pattern for product name
		 */
		String nameregex = "^[a-zA-Z]{2,15}$";
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(userName);
		boolean isMatch = matcher.matches();

		if (!isMatch) {
			throw new InvalidInputException(UserValidateErrors.INVALID_USERNAME);
		}
		

		return true;

	}

	/**
	 * Method to validate user address
	 */
	public static boolean AddressValidator(String userAddress) throws InvalidInputException {
		if (userAddress == null || "".equals(userAddress.trim())) {
			throw new InvalidInputException(UserValidateErrors.INVALID_USERADDRESS_NULL);
		}

		/**
		 * regex pattern for user address
		 */
		String descregex = "^[^ .]+( [^ .]+)*$";
		Pattern pattern = Pattern.compile(descregex);
		Matcher matcher = pattern.matcher(userAddress);
		boolean isMatch = matcher.matches();

		if (isMatch) {
			return true;
		} else {
			throw new InvalidInputException(UserValidateErrors.INVALID_USERADDRESS);
		}
	}
		
	public static boolean passwordValidator(String password) throws InvalidInputException {
		/**
		 * firstName null and empty string check
		 */
		if (password == null || "".equals(password.trim())) {
			throw new InvalidInputException(UserValidateErrors.INVALID_USERPASSWORD_NULL);
		}
		/**
		 * email regex pattern
		 */
		//
		String regex = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		Boolean isMatch = matcher.matches();

		if (Boolean.FALSE.equals(isMatch)) {
			throw new InvalidInputException(UserValidateErrors.INVALID_USERPASSWORD_PATTERN);

		}

		return true;

	}

		
	public static boolean phoneNumberValidator(String phoneNumber) throws InvalidInputException {
		/**
		 * firstName null and empty string check
		 */
		if (phoneNumber == null || "".equals(phoneNumber.trim())) {
			throw new InvalidInputException(UserValidateErrors.INVALID_PHONENUMBER_NULL);
		}
	
		/**
		 * email regex pattern
		 */
		//
		String regex = "(0/91)?[7-9][0-9]{9}";
		Pattern pattern = Pattern.compile(regex);
		String numberStr = String.valueOf(phoneNumber);
		Matcher matcher = pattern.matcher(numberStr);
		Boolean isMatch = matcher.matches();

		if (Boolean.FALSE.equals(isMatch)) {
			throw new InvalidInputException(UserValidateErrors.INVALID_PHONENUMBER);

		}

		return true;

	}
	
}

