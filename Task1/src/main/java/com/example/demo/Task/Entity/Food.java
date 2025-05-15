package com.example.demo.Task.Entity;

import java.awt.Image;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Food {

	@Id
	private String foodId;
	@Lob
    private byte[] image; // Use byte[] for storing image data

	private String productName;
	private int productPrice;
	private String description;
	public String getFoodId() {
		return foodId;
	}
	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Food(String foodId, byte[] image, String productName, int productPrice, String description) {
		super();
		this.foodId = foodId;
		this.image = image;
		this.productName = productName;
		this.productPrice = productPrice;
		this.description = description;
	}
	public Food() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
