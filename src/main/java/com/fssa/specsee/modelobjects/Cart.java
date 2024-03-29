package com.fssa.specsee.modelobjects;

import java.util.ArrayList;

import com.fssa.specsee.daolayer.CartDetails;

public class Cart {

	private int cartId;
	private int userId;
	private double totalAmount;
	private int quantity;
	private ArrayList<CartDetails> cartDetails;

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ArrayList<CartDetails> getCartDetails() {
		return cartDetails;
	}

	public void setCartDetails(ArrayList<CartDetails> cartDetails) {
		this.cartDetails = cartDetails;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", userId=" + userId + ", totalAmount=" + totalAmount + ", quantity="
				+ quantity + ", cartDetails=" + cartDetails + "]";
	}



}