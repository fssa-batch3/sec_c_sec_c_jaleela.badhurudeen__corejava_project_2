package com.fssa.specsee.enums;

public enum ProductCatagory {

	COMPUTER_GLASSES("computer glass"), SUN_GLASSES("sun glass"), POWER_GLASSES("power glass"),
	CONTACT_LENS("contact lens"), METALICS("metalics"), RECTANGLE("rectangle"), PILOT("pilot");

	private final String category;

	ProductCatagory(String cat) {
		this.category = cat;
	}
	

	public String getCat() {

		return category;
	}

}
