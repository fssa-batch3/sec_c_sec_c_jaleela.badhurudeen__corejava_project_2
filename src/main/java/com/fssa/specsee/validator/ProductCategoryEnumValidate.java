package com.fssa.specsee.validator;

import com.fssa.specsee.enums.ProductCategory;
import com.fssa.specsee.exceptions.InvalidProductException;

public class ProductCategoryEnumValidate {
	/**
	 * create a validation for product category
	 */
	private ProductCategoryEnumValidate() {
		/**
		 * default constructor
		 */
	}

	public static boolean validProductCategory(String category) throws InvalidProductException {
		if (category == null) {
			throw new InvalidProductException(ProductValidateErrors.ENUM_ERROR);
		}

		return true;
	}

}
