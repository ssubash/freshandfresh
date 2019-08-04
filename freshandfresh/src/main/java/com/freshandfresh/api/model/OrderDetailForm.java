package com.freshandfresh.api.model;

public class OrderDetailForm {

	private long id;
	private ItemForm item;
	private double price;
	private int noOfUnit;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ItemForm getItem() {
		return item;
	}

	public void setItem(ItemForm item) {
		this.item = item;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNoOfUnit() {
		return noOfUnit;
	}

	public void setNoOfUnit(int noOfUnit) {
		this.noOfUnit = noOfUnit;
	}

}
