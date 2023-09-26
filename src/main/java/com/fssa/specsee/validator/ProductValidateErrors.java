package com.fssa.specsee.validator;

public class ProductValidateErrors {
	/**
	 * Define constants for invalid product errors
	 */
	public static final String INVALID_PRODUCT_NULL = "Product cannot be null";
	public static final String INVALID_PRODUCTNAME_NULL = "Product name cannot be null";
	public static final String INVALID_PRODUCTNAME = "Product name should be have minimum 2 ,maximum 15 letters";
	public static final String INVALID_PRODUCTDESCRIPTION_NULL = "Product description cannot be null";
	public static final String INVALID_PRODUCTDESCRIPTION = "Product description must have minimum 10 and maximum 100 letters";
	public static final String INVALID_PRODUCTPRICE_NULL = "Product price cannot be null";
	public static final String INVALID_PRODUCTPRICE = "Product price should be above 500 rupees";

	public static final String INVALID_PRODUCT_IMAGE_URL_NULL = "Product image URL cannot be null";
	public static final String INVALID_PRODUCT_IMAGE_URL = "Product image URL cannot be null";
	public static final String INVALID_PRODUCT_SIDEIMAGE_URL_NULL = "Product side image URL cannot be null";
	public static final String INVALID_PRODUCT_SIDEIMAGE_URL = "Product side image URL should be  match the pattern";
	public static final String INVALID_PRODUCT_EACHSIDEIMAGE_URL_NULL = "Product each side image URL cannot be null";
	public static final String ENUM_ERROR = "failed";

	/**
	 * Define test error messages
	 */
	public static final String PRODUCTNAMEFAILMSG = "Validate product name failed";
	public static final String PRODUCT_DESCRIPTION_FAIL_MSG = "Validate product Description failed";
	public static final String PRODUCT_PRICE_FAIL_MSG = "Validate product price failed";
	public static final String PRODUCT_IMAGEURL_FAIL_MSG = "Validate product image URL failed";

	public static final String CANNOT_ADD_PRODUCT = "you can't add product";

	/**
	 * Define test service layer error messages
	 */

	public static final String TEST_INVALID_ADDPRODUCT_FAIL = "Invalid Add PRODUCT failed";
	
	


}
