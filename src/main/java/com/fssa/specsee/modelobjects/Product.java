package com.fssa.specsee.modelobjects;

import java.util.List;

import com.fssa.specsee.enums.ProductCatagory;

public class Product {

	private int productId;
	private String productName;
	private String productDescription;
	private ProductCatagory ProductCatagory;
	private double productPrice;
	private String productMainImageUrl;
	private List<String> productSideImageURLs;

	public Product(int productId, String productName, String productDescription, ProductCatagory productCatagory,
			double productPrice, String productMainImageUrl, List<String> productSideImageURLs) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.ProductCatagory = productCatagory;
		this.productPrice = productPrice;
		this.productMainImageUrl = productMainImageUrl;
		this.productSideImageURLs = productSideImageURLs;
	}

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public ProductCatagory getProductCatagory() {
		return ProductCatagory;
	}

	public void setProductCatagory(ProductCatagory productCatagory) {
		this.ProductCatagory = productCatagory;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductMainImageUrl() {
		return productMainImageUrl;
	}

	public void setProductMainImageUrl(String productMainImageUrl) {
		this.productMainImageUrl = productMainImageUrl;
	}

	public List<String> getProductSideImageURLs() {
		return productSideImageURLs;
	}

	public void setProductSideImageURLs(List<String> productSideImageURLs) {
		this.productSideImageURLs = productSideImageURLs;
	}

}
