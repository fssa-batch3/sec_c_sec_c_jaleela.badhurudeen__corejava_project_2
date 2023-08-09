package com.fssa.specsee.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.specsee.enums.ProductCatagory;
import com.fssa.specsee.exceptions.InvalidProductException;
import com.fssa.specsee.modelObjects.Product;

public class TestProductValidator {

	// test for validate product object
	@Test
	public void testProductValidator() throws InvalidProductException {

		try {

			ProductValidator.validate(null); 
			Assertions.fail(ProductValidateErrors.INVALID_PRODUCT_NULL);
		} catch (InvalidProductException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCT_NULL, e.getMessage());
		}
	}

// test for valid product name 
	@Test
	public void testValidProductName() throws InvalidProductException {
		Assertions.assertTrue(ProductValidator.productNameValidator(ProductValidateConstants.PRODUCT_VALID_NAME));
	}

// for invalid product name when the product name is null
	@Test
	public void testInvalidProductName() throws InvalidProductException {
		try {
			ProductValidator.productNameValidator(null);
			Assertions.fail(ProductValidateErrors.PRODUCTNAMEFAILMSG);

		} catch (InvalidProductException e) {

			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCTNAME_NULL, e.getMessage());
		}
		// for invalid product name when the product name is under 2 letters or above 15
		// letters
		try {
			ProductValidator.productNameValidator(ProductValidateConstants.PRODUCT_INVALID_NAME);
			Assertions.fail(ProductValidateErrors.PRODUCTNAMEFAILMSG);

		} catch (InvalidProductException e) {

			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCTNAME, e.getMessage());
		}

	}

	// test for valid product description

	@Test

	public void testValidProductDescription() throws InvalidProductException {

		Assertions.assertTrue(
				ProductValidator.productDescriptionValidator(ProductValidateConstants.PRODUCT_VALID_DESCRIPTION));
	}

	// test for invalid product description when it is null.
	@Test
	public void testInvalidProductDescription() throws InvalidProductException {
		try {
			ProductValidator.productDescriptionValidator(null);
//    		Assertions.fail(ProductValidateErrors.PRODUCT_DESCRIPTION_FAIL_MSG);

		} catch (InvalidProductException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCTDESCRIPTION_NULL, e.getMessage());
		}
		// test for invalid product description when it is minimum 15 and maximum 100
		// letters

		try {
			ProductValidator.productDescriptionValidator(ProductValidateConstants.PRODUCT_INVALID_DESCRIPTION);
			Assertions.fail(ProductValidateErrors.INVALID_PRODUCTDESCRIPTION);

		} catch (InvalidProductException e) {

			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCTDESCRIPTION, e.getMessage());
		}
	}

	// test validation for valid product image url
	@Test
	public void testValidProductImageURL() throws InvalidProductException {
		Assertions.assertTrue(ProductValidator.productImageURLValidator(ProductValidateConstants.PRODUCT_VALIDIMAGE_URL));

	}

	// test for invalid product image url when it is null
	@Test
	public void testInvalidProductImageURL() throws InvalidProductException {
		try {
			ProductValidator.productImageURLValidator(null);
			Assertions.fail(ProductValidateErrors.PRODUCT_IMAGEURL_FAIL_MSG);

		} catch (InvalidProductException ex) {

			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCT_IMAGE_URL_NULL, ex.getMessage());
		}

		// test for invalid product image url when it is not match the pattern
		try {
			ProductValidator.productImageURLValidator(ProductValidateConstants.PRODUCT_INVALIDIMAGE_URL);
			Assertions.fail(ProductValidateErrors.PRODUCT_IMAGEURL_FAIL_MSG);

		} catch (InvalidProductException ex) {

			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCT_IMAGE_URL, ex.getMessage());
		}

	}

	// test validation for valid product price
	@Test
	public void testValidProductPrice() throws InvalidProductException {
		Assertions.assertTrue(ProductValidator.productPriceValidator(ProductValidateConstants.PRODUCT_VALID_PRICE));

	}

	// test for invalid product price when it is zero
	@Test
	public void testInvalidProductPrice() throws InvalidProductException {
		try {
			ProductValidator.productPriceValidator(0);
			Assertions.fail(ProductValidateErrors.PRODUCT_PRICE_FAIL_MSG);

		} catch (InvalidProductException ex) {

			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCTPRICE, ex.getMessage());
		}
		

		// test for invalid product price when it is less than 500
		try {
			ProductValidator.productPriceValidator(ProductValidateConstants.PRODUCT_INVALID_PRICE);
			Assertions.fail(ProductValidateErrors.PRODUCT_PRICE_FAIL_MSG);

		} catch (InvalidProductException ex) {

			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCTPRICE, ex.getMessage());
		}

	}

	// test for

	@Test
	void testValidList() throws InvalidProductException {
		// Test with a list of valid strings
		List<String> validList = Arrays.asList("http://example.com/image.jpg", "https://www.example.com/image.jpeg",
				"ftp://sub.example.com/image.png", "http://sub.domain.com/image.gif");

		assertTrue(ProductValidator.isValidList(validList));
	}

	@Test
	void testInvalidList() throws InvalidProductException {
		// Test with a list containing invalid strings
		List<String> invalidList = Arrays.asList("invalid_url.jpg", "https://invalid", "ftp://invalid.",
				"http://invalid/image.gifx", "http://invalid.com/image.pdf");

		assertFalse(ProductValidator.isValidList(invalidList));
	}

	@Test
	void testEmptyList() throws InvalidProductException {
		try {
			// Test with an empty list
			List<String> emptyList = Arrays.asList();
			Assertions.assertTrue(ProductValidator.isValidList(emptyList));
		} catch (InvalidProductException e) {

			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCT_IMAGE_URL_NULL, e.getMessage());

		}
	}

	// test invalid product category
	@Test

	public void testInvalidProductCategory() {
//      	product category
		try {
			ProductCategoryEnumValidate.validProductCategory(null);
			Assertions.fail("Test case failed");
		} catch (InvalidProductException e) {
			Assertions.assertEquals(ProductValidateErrors.ENUM_ERROR, e.getMessage());
		}
//      	invalid  product category
		try {
			ProductCategoryEnumValidate.validProductCategory("sxcvb");
			Assertions.fail("Test case failed");
		} catch (InvalidProductException e) {
			Assertions.assertEquals(ProductValidateErrors.ENUM_ERROR, e.getMessage());
		}

	}

	// validate for getter and setters 

	@Test
	public void testValidationForGetterSetter() {
		String array[] = { "http://example.com/image.jpg" };
		List<String> sideImgURL = new ArrayList<String>();
		sideImgURL.add("https://iili.io/HrkUVp4.jpg");
		sideImgURL.add("https://iili.io/HrkUVp4.jpg");
		sideImgURL.add("https://iili.io/HrkUVp4.jpg");
		sideImgURL.add("https://iili.io/HrkUVp4.jpg");
		Product product = new Product();
		product.setProductId(1);
		product.setProductName("Andale");
		product.setProductDescription(" Navy Blue color computer glass");
		product.setProductPrice(1110);
		product.setProductCatagory(ProductCatagory.METALICS);
		product.setProductMainImageUrl("https://iili.io/HrkUVp4.jpg");
		product.setProductSideImageURLs(sideImgURL);
	}

}

