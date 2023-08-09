package com.fssa.specsee.validator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fssa.specsee.exceptions.InvalidProductException;
import com.fssa.specsee.modelObjects.Product;

// validation for Product object
public class ProductValidator {
	public static boolean validate(Product product) throws InvalidProductException {
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

	// product name validation
	public static boolean productNameValidator(String productName) throws InvalidProductException {

		if (productName == null || "".equals(productName.trim())) {
			throw new InvalidProductException(ProductValidateErrors.INVALID_PRODUCTNAME_NULL);
		}
// regex pattern for product name
		String nameregex = "^[a-zA-Z]{5,15}$";
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(productName);
		Boolean isMatch = matcher.matches();

		if (!isMatch) {
			throw new InvalidProductException(ProductValidateErrors.INVALID_PRODUCTNAME);

		}

		return true;

	}

	// product description validation

	public static boolean productDescriptionValidator(String productDescription) throws InvalidProductException {
		if (productDescription == null || "".equals(productDescription.trim())) {
			throw new InvalidProductException(ProductValidateErrors.INVALID_PRODUCTDESCRIPTION_NULL);
		}

		// regex pattern for product description
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

	// product price validation

	public static boolean productPriceValidator(double productPrice) throws InvalidProductException {

		if (productPrice < ProductValidateConstants.PRODUCT_PRICE) {
			throw new InvalidProductException(ProductValidateErrors.INVALID_PRODUCTPRICE);

		}
		return true;
	}

	// product main image url validation

	public static boolean productImageURLValidator(String productImageURL) throws InvalidProductException {
		if (productImageURL == null) {
			throw new InvalidProductException(ProductValidateErrors.INVALID_PRODUCT_IMAGE_URL_NULL);
		}

		// regex pattern to match url
		String imageurlregex = "(?i)\\b((https?|ftp)://)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?\\.(jpg|jpeg|gif|png|bmp)\\b";
		Pattern pattern = Pattern.compile(imageurlregex);
		Matcher matcher = pattern.matcher(productImageURL);
		boolean isMatch = matcher.matches();

		if (!isMatch) {
			throw new InvalidProductException(ProductValidateErrors.INVALID_PRODUCT_IMAGE_URL);
		}

		return true; // Return true if the URL is valid
	}

	// product side image url validation (list<String>) 

	// All strings in the list are valid

	// regex pattern to match url
	public static boolean isValidList(List<String> stringList) throws InvalidProductException {

		if (stringList == null || stringList.isEmpty()) {

			throw new InvalidProductException(ProductValidateErrors.INVALID_PRODUCT_IMAGE_URL_NULL);
		}
		// Regular expression pattern
		String pattern = "(?i)\\b((https?|ftp)://)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?\\.(jpg|jpeg|gif|png|bmp)\\b";
		Pattern regexPattern = Pattern.compile(pattern);

		// Validate each string in the list
		for (String str : stringList) {
			if (!regexPattern.matcher(str).matches()) {
				return false; // If any string doesn't match, return false
			}
		}

		return true; // All strings match the pattern
	}

}
