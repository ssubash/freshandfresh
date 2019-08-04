package com.freshandfresh.api.model;

import java.util.List;

public class CategoryForm {

	private int id;
	private String name;
	private boolean isActive;
	private List<ItemForm> items;

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

	public List<ItemForm> getItems() {
		return items;
	}

	public void setItems(List<ItemForm> items) {
		this.items = items;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
