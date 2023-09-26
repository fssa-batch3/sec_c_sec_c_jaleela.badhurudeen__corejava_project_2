package com.fssa.specsee.modelobjects;

import java.util.List;

import com.fssa.specsee.enums.ProductCategory;

/**
 *  Define the Product class to model product objects
 */
public class Product {

	private int productId;
	private String productName;
	private String productDescription;
	private ProductCategory productCategory;
	private double productPrice;
	private String productMainImageUrl;
	private List<String> productSideImageURLs;
	/**
	 * Define a parameterized constructor to initialize product details
	 */

	public Product(int productId, String productName, String productDescription, ProductCategory productCatagory,
			double productPrice, String productMainImageUrl, List<String> productSideImageURLs) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productCategory = productCatagory;
		this.productPrice = productPrice;
		this.productMainImageUrl = productMainImageUrl;
		this.productSideImageURLs = productSideImageURLs;
	}

	public Product() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Define getter and setter methods for each field
	 */
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

	public ProductCategory getProductCatagory() {
		return productCategory;
	}

	public void setProductCatagory(ProductCategory productCatagory) {
		this.productCategory = productCatagory;
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

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productDescription="
				+ productDescription + ", productCatagory=" + productCategory + ", productPrice=" + productPrice
				+ ", productMainImageUrl=" + productMainImageUrl + ", productSideImageURLs=" + productSideImageURLs
				+ "]";
	}
	
	

}
