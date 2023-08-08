package com.fssa.specsee.validator;

import com.fssa.specsee.enums.ProductCatagory;
import com.fssa.specsee.exceptions.InvalidProductException;

public class ProductCategoryEnumValidate {
	// create a validation for product category

	public static boolean validProductCategory(String category) throws InvalidProductException {
		if (category == null) {
			throw new InvalidProductException(ProductValidateErrors.ENUM_ERROR);
		}

		for (ProductCatagory cat : ProductCatagory.values()) {
			if (!(cat.getCat().equalsIgnoreCase(category))) {

				throw new InvalidProductException(ProductValidateErrors.ENUM_ERROR);
			}
		}
		return true;
	}

}
