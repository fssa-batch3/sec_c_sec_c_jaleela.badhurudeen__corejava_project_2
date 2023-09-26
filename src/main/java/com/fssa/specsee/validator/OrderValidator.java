package com.fssa.specsee.validator;

import java.time.LocalDate;
import java.util.List;

import com.fssa.specsee.enums.OrderStatus;
import com.fssa.specsee.exceptions.CustomException;
import com.fssa.specsee.modelobjects.Order;
import com.fssa.specsee.modelobjects.OrderProduct;

public class OrderValidator {

	
	Order order = new Order();

	// Set the properties of the order
	public static boolean validateOrder(Order order) throws CustomException {
		if (order == null) {
			throw new CustomException(OrderErrorMessages.INVALID_ORDER_NULL);
		}

		validateTotalAmount(order.getTotalAmount());
		validateProductsList(order.getProductsList());
		validateOrderedDate(order.getOrderedDate());
		validateStatus(order.getStatus());
		validatePhoneNumber(order.getPhoneNumber());
		return true;
	}

	public static boolean validateTotalAmount(double totalAmount) throws CustomException {
		// Check if the price is non-positive
		if (totalAmount <= 0) {
			throw new CustomException(OrderErrorMessages.INVALID_TOTAL_AMOUNT);
		}
		return true;
	}

	public static boolean validateProductsList(List<OrderProduct> productsList) throws CustomException {
		if (productsList == null || productsList.isEmpty()) {
			throw new CustomException(OrderErrorMessages.INVALID_PRODUCT_LIST);
		}
		return true;
	}

	public static boolean validateOrderedDate(LocalDate orderedDate) throws CustomException {
		if (orderedDate == null) {
			throw new CustomException(OrderErrorMessages.INVALID_ORDERED_DATE);
		}

		LocalDate currentDate = LocalDate.now();

		if (orderedDate.isAfter(currentDate)) {
			throw new CustomException(OrderErrorMessages.ORDER_DATE_IN_FUTURE);
		}
		return true;
	}

	public static boolean validateStatus(OrderStatus status) throws CustomException {

		if (status == null) {
			throw new CustomException(OrderErrorMessages.INVALID_STATUS);
		}
		return false;
	}

	public static boolean validatePhoneNumber(String comments) throws CustomException {

		if (comments == null || comments.isEmpty() || comments.length() < 10) {
			throw new CustomException(OrderErrorMessages.INVALID_COMMENTSTATEMENT);
		}

		return true;

	}

	public static boolean validateAddress(String address) throws CustomException {

		if (address != null && !address.isEmpty() && address.length() > 5) {
			throw new CustomException(OrderErrorMessages.INVALID_COMMENTSTATEMENT);
		}

		return true;

	}
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

