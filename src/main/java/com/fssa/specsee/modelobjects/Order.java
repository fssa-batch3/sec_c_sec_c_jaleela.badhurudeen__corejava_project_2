	package com.fssa.specsee.modelobjects;
import java.time.LocalDate;
import java.util.List;

import com.fssa.specsee.enums.OrderStatus;

public class Order {

	private int userID;
	private int OrderId;
	private double totalAmount;
	List<OrderProduct> productsList;
	private LocalDate orderDate;
	private OrderStatus status;
	private String PhoneNumber;
	private String address;

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getOrderId() {
		return OrderId;
	}

	public void setOrderId(int orderId) {
		OrderId = orderId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<OrderProduct> getProductsList() {
		return productsList;
	}

	public void setProductsList(List<OrderProduct> productsList) {
		this.productsList = productsList;
	}

	public LocalDate getOrderedDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String PhoneNumber) {
		this.PhoneNumber = PhoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Order [userID=" + userID + ", OrderId=" + OrderId + ", totalAmount=" + totalAmount + ", productsList="
				+ productsList + ", orderDate=" + orderDate + ", status=" + status + ", PhoneNumber=" + PhoneNumber
				+ ", address=" + address + "]";
	}

	
}