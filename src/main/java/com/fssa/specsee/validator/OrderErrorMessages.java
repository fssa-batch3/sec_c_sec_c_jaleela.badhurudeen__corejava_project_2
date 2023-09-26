package com.fssa.specsee.validator;

public class OrderErrorMessages {
	public static final String INVALID_ORDER_NULL = "Order object can't be null";
	public static final String INVALID_PRODUCT_LIST = "Product list can't be null";
	public static final String INVALID_STATUS = "Invalid order status";
	public static final String INVALID_COMMENTSTATEMENT = "Comments must be at least 10 characters long";
	public static final String ORDER_DATE_IN_FUTURE = "Ordered date cannot be in the future";
	public static final String INVALID_TOTAL_AMOUNT = "Invalid total amount. Total amount must be a positive number";
	public static final String INVALID_ORDERED_DATE = "Invalid ordered date. Please provide a valid date";
	public static final String ORDER_ALREADY_EXISTS = "Order with ID already exists in the database";
	public static final String ORDER_CREATION_FAILED = "Failed to create the order in the database";
	public static final String ORDER_RETRIEVAL_FAILED = "Failed to retrieve order data from the database";

}
