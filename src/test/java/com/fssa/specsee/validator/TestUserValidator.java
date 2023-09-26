package com.fssa.specsee.validator;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.specsee.exceptions.InvalidInputException;
import com.fssa.specsee.exceptions.InvalidProductException;
import com.fssa.specsee.modelobjects.User;

public class TestUserValidator {



	@Test
	void testUserValidator() throws InvalidProductException, InvalidInputException {
		/**
		 * Validate that ProductValidator throws an exception when a null product is
		 * provided
		 */
		
		try {

			UserValidator.validateUser(null);

			Assertions.fail(UserValidateErrors.INVALID_USER_NULL);
		} catch (InvalidInputException e) {
			Assertions.assertEquals(UserValidateErrors.INVALID_USER_NULL, e.getMessage());
		}
	}

	/**
	 * Test method for adding a valid user name
	 */
	@Test
	void testValidUserName() throws InvalidInputException { 
		Assertions.assertTrue(UserValidator.validateUserName("jaleela"));
	}

	/**
	 * for invalid user name when the user name is null
	 */
	@Test
	void testInvalidUserName() throws InvalidInputException {
		try {
		  UserValidator.validateUserName(null);
			Assertions.fail(ProductValidateErrors.PRODUCTNAMEFAILMSG);

		} catch (InvalidInputException e) {

			Assertions.assertEquals(UserValidateErrors.INVALID_USERNAME_NULL, e.getMessage());
		}
		/**
		 * for invalid user name when the user name is under 2 letters or above 15
		 */
		try {
			UserValidator.validateUserName("j");
			Assertions.fail(ProductValidateErrors.PRODUCTNAMEFAILMSG);

		} catch (InvalidInputException e) {

			Assertions.assertEquals(UserValidateErrors.INVALID_USERNAME, e.getMessage());
		}

	}
	
	/**
	 * test for valid user address
	 */

	@Test

	void testValidUserAddress() throws InvalidInputException {

		Assertions.assertTrue(
				UserValidator.AddressValidator("48/A,Tiruneelakandar street,pattamadai"));
	}

	/**
	 * test for invalid user address when it is null.
	 */
	@Test
	void testInvalidUserAddress() throws InvalidInputException {
		try {
			UserValidator.AddressValidator(null);

		} catch (InvalidInputException e) {
			Assertions.assertEquals(UserValidateErrors.INVALID_USERADDRESS_NULL, e.getMessage());
		}
		/**
		 * test for invalid user address when it is minimum 15 and maximum 100
		 */

		try {
			UserValidator.AddressValidator("street                ");
			Assertions.fail(ProductValidateErrors.INVALID_PRODUCTDESCRIPTION);

		} catch (InvalidInputException e) {

			Assertions.assertEquals(UserValidateErrors.INVALID_USERADDRESS, e.getMessage());
		}
	}

	/**
	 * test for valid user email
	 */

	@Test

	void testValidUserEmail() throws InvalidInputException {

		Assertions.assertTrue(
				UserValidator.emailValidator("jaleela@gmail.com"));
	}

	/**
	 * test for invalid user email when it is null.
	 */
	@Test
	void testInvalidUserEmail() throws InvalidInputException {
		try {
			UserValidator.emailValidator(null);

		} catch (InvalidInputException e) {
			Assertions.assertEquals(UserValidateErrors.INVALID_EMAIL_NULL, e.getMessage());
		}
		/**
		 * test for invalid user email 
		 */

		try {
			UserValidator.emailValidator("h.co,");
			Assertions.fail(ProductValidateErrors.INVALID_PRODUCTDESCRIPTION);

		} catch (InvalidInputException e) {

			Assertions.assertEquals(UserValidateErrors.INVALID_EMAIL_FORMAT, e.getMessage());
		}
	}
	
	
	/**
	 * test for valid user password
	 */

	@Test

	void testValidUserPassword() throws InvalidInputException {

		Assertions.assertTrue(
				UserValidator.passwordValidator("Jaleela@123"));
	}

	/**
	 * test for invalid user password when it is null.
	 */
	@Test
	void testInvalidUserPassword() throws InvalidInputException {
		try {
			UserValidator.passwordValidator(null);

		} catch (InvalidInputException e) {
			Assertions.assertEquals(UserValidateErrors.INVALID_USERPASSWORD_NULL, e.getMessage());
		}
		/**
		 * test for invalid user password 
		 */

		try {
			UserValidator.passwordValidator("jal21");
			Assertions.fail(ProductValidateErrors.INVALID_PRODUCTDESCRIPTION);

		} catch (InvalidInputException e) {

			Assertions.assertEquals(UserValidateErrors.INVALID_USERPASSWORD_PATTERN, e.getMessage());
		}
	}
	


	/**
	 * test for valid user phone number
	 */

	@Test

	void testValidUserPhoneNumber() throws InvalidInputException {

		Assertions.assertTrue(
				UserValidator.phoneNumberValidator("9876543210"));
	}

	/**
	 * test for invalid user password when it is null.
	 */
	@Test
	void testInvalidUserPhoneNumber() throws InvalidInputException {
		try {
			UserValidator.phoneNumberValidator(null);

		} catch (InvalidInputException e) {
			Assertions.assertEquals(UserValidateErrors.INVALID_PHONENUMBER_NULL, e.getMessage());
		}
		/**
		 * test for invalid user password 
		 */

		try {
			UserValidator.phoneNumberValidator("jal21");
			Assertions.fail(ProductValidateErrors.INVALID_PRODUCTDESCRIPTION);

		} catch (InvalidInputException e) {

			Assertions.assertEquals(UserValidateErrors.INVALID_PHONENUMBER, e.getMessage());
		}
	}
}