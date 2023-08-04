package com.fssa.specsee.validator;

import com.fssa.specsee.enums.ProductCatagory;

public class ProductCategoryEnumValidate {
	// create a validation for  product category

	public static boolean ValidProductCategory(String category) throws IllegalArgumentException {
		if (category == null) {
			throw new IllegalArgumentException(ProductValidateErrors.ENUM_ERROR);
		}

		for (ProductCatagory cat : ProductCatagory.values()) {
			if (!(cat.getCat().equalsIgnoreCase(category))) {

				throw new IllegalArgumentException(ProductValidateErrors.ENUM_ERROR);
			}
		}
		return true;
	}

}
