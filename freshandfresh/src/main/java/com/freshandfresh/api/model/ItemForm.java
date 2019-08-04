package com.freshandfresh.api.model;

public class ItemForm {

	private int id;
	private String name;
	private byte[] image;
	private boolean isAvailable;
	private boolean isActive;
	private String availableFrom;
	private String avilableTo;
	private String unitRange;
	private double price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getAvailableFrom() {
		return availableFrom;
	}

	public void setAvailableFrom(String availableFrom) {
		this.availableFrom = availableFrom;
	}

	public String getAvilableTo() {
		return avilableTo;
	}

	public void setAvilableTo(String avilableTo) {
		this.avilableTo = avilableTo;
	}

	public String getUnitRange() {
		return unitRange;
	}

	public void setUnitRange(String unitRange) {
		this.unitRange = unitRange;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
