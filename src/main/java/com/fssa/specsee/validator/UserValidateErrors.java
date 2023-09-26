package com.fssa.specsee.validator;

public class UserValidateErrors {
	/**
	 * Define test user model object error messages
	 */
	
public static final String INVALID_USER_NULL = "User can not be null";
	
	// For Invalid Email
	public static final String INVALID_EMAIL_NULL = "Email cannot be null or empty.";
	public static final String INVALID_EMAIL_FORMAT = "Invalid Email Format: Please provide a valid email address.";

	// For Invalid Username
	public static final String INVALID_USERNAME_NULL = "Username cannot be null or empty.";
	public static final String INVALID_USERNAME = "Username should have minimum 2 maximum 15 letters";

	// For Invalid user password
	public static final String INVALID_USERPASSWORD_NULL = "password cannot be empty or null";
	public static final String INVALID_USERPASSWORD_PATTERN = "A valid password should meet the following criteria Have at least 8 characters Contain at least one lowercase letter Contain at least one uppercase letter Contain at least one digit Contain at least one special character";
	
	// For Invalid user address
		public static final String INVALID_USERADDRESS_NULL = "Address cannot be empty or null";
		public static final String INVALID_USERADDRESS = "Address should match the pattern. Address should have minimum 10 and maximum 100 letters";
		
		// for invalid user phone number
		
		public static final String INVALID_PHONENUMBER_NULL = "phone number cannot be empty or null";
		public static final String INVALID_PHONENUMBER = "phone number should match the pattern. Address should have 10 numbers";
		
}
