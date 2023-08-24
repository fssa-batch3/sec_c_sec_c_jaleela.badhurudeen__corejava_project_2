package com.fssa.specsee.validator;

import java.net.MalformedURLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.UrlValidator;

import com.fssa.specsee.exceptions.InvalidProductException;
import com.fssa.specsee.modelobjects.Product;

/*
 * Define the ProductValidator class for validating product objects
 */
public class ProductValidator {
	private ProductValidator() {
		/*
		 * private constructor
		 */
	}

	/*
	 * Method to validate a Product object 
	 */
	public static boolean validate(Product product) throws InvalidProductException, MalformedURLException {
		if (product == null) {
			throw new InvalidProductException(ProductValidateErrors.INVALID_PRODUCT_NULL);

		}
		productDescriptionValidator(product.getProductDescription());
		productImageURLValidator(product.getProductMainImageUrl());
		productNameValidator(product.getProductName());
		productPriceValidator(product.getProductPrice());
		isValidList(product.getProductSideImageURLs());
		return true;

	}

	/*
	 * Method to validate product name
	 */
	public static boolean productNameValidator(String productName) throws InvalidProductException {

		if (productName == null || "".equals(productName.trim())) {
			throw new InvalidProductException(ProductValidateErrors.INVALID_PRODUCTNAME_NULL);
		}
		/*
		 * regex pattern for product name
		 */
		String nameregex = "^[a-zA-Z]{5,15}$";
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(productName);
		boolean isMatch = matcher.matches();

		if (!isMatch) {
			throw new InvalidProductException(ProductValidateErrors.INVALID_PRODUCTNAME);

		}

		return true;

	}

	/*
	 * Method to validate product description
	 */
	public static boolean productDescriptionValidator(String productDescription) throws InvalidProductException {
		if (productDescription == null || "".equals(productDescription.trim())) {
			throw new InvalidProductException(ProductValidateErrors.INVALID_PRODUCTDESCRIPTION_NULL);
		}

		/*
		 * regex pattern for product description
		 */
		String descregex = "^[a-zA-Z\\s]{10,100}$";
		Pattern pattern = Pattern.compile(descregex);
		Matcher matcher = pattern.matcher(productDescription);
		boolean isMatch = matcher.matches();

		if (isMatch) {
			return true;
		} else {
			throw new InvalidProductException(ProductValidateErrors.INVALID_PRODUCTDESCRIPTION);
		}
	}

	/*
	 * Method to validate product price
	 */
	public static boolean productPriceValidator(double productPrice) throws InvalidProductException {

		if (productPrice < ProductValidateConstants.PRODUCT_PRICE) {
			throw new InvalidProductException(ProductValidateErrors.INVALID_PRODUCTPRICE);

		}
		return true;
	}

	/*
	 * Method to validate product main image url
	 */
	public static boolean productImageURLValidator(String productImageURL)
			throws InvalidProductException, MalformedURLException {
		if (productImageURL == null) {
			throw new InvalidProductException(ProductValidateErrors.INVALID_PRODUCT_IMAGE_URL_NULL);
		}

		/*
		 * regex pattern to match url
		 */
		UrlValidator validator = new UrlValidator();
//		String imageurlregex = "(?i)\\b((https?|ftp)://)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?\\.(jpg|jpeg|gif|png|bmp)\\b";
//		Pattern pattern = Pattern.compile(imageurlregex);
//		Matcher matcher = pattern.matcher(productImageURL);
		boolean isMatch = validator.isValid(productImageURL);

		if (!isMatch) {
			throw new InvalidProductException(ProductValidateErrors.INVALID_PRODUCT_IMAGE_URL);
		}

		return true;
		/*
		 * Return true if the URL is valid
		 */
	}

	/*
	 * Method to validate a list of product side image URLs
	 */

	/*
	 * regex pattern to match url
	 */
	public static boolean isValidList(List<String> stringList) throws InvalidProductException,MalformedURLException {

		if (stringList == null || stringList.isEmpty()) {

			throw new InvalidProductException(ProductValidateErrors.INVALID_PRODUCT_IMAGE_URL_NULL);
		}
		/*
		 * Regular expression pattern
		 */
		
		String pattern = "(http)?s?:?(\\/\\/[^\"']*\\.(?:png|jpg|jpeg|gif|svg|webp))";
		
		/*
		 * Validate each string in the list
		 */
		for (String str : stringList) {
			if (!Pattern.matches(pattern, str)) {
				return false; // If any string doesn't match, return false
			}
		}

		return true;

		/*
		 * All strings match the pattern
		 */
	}

}
