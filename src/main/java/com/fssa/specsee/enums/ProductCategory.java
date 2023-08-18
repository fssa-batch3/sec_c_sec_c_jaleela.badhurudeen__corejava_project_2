<<<<<<< Updated upstream
package com.fssa.specsee.enums;

/*
 *  Define the enum for product categories
 */
public enum ProductCategory {
	/*
	 * Define enum constants for various product categories
	 */
	COMPUTER_GLASSES("computer glass"), SUN_GLASSES("sun glass"), POWER_GLASSES("power glass"),
	CONTACT_LENS("contact lens"), METALICS("metalics"), RECTANGLE("rectangle"), PILOT("pilot");

	private final String category;

	ProductCategory(String cat) {
		this.category = cat;
	}
	/*
	 * Define a method to get the category name
	 */

	public String getCat() {

		return category;
	}

}
=======
package com.fssa.specsee.enums;

/*
 *  Define the enum for product categories
 */
public enum ProductCategory {
	/*
	 * Define enum constants for various product categories
	 */
	COMPUTER_GLASSES("computer glass"), SUN_GLASSES("sun glass"), POWER_GLASSES("power glass"),
	CONTACT_LENS("contact lens"), METALICS("metalics"), RECTANGLE("rectangle"), PILOT("pilot");

	private final String category;

	ProductCategory(String cat) {
		this.category = cat;
	}
	/*
	 * Define a method to get the category name
	 */

	public String getCat() {

		return category;
	}

}
>>>>>>> Stashed changes
