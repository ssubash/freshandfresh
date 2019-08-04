package com.freshandfresh.api.dao;

import java.util.List;

import com.freshandfresh.api.entity.Category;
import com.freshandfresh.api.entity.Item;

public interface ProductManagementDao {

	void saveCategory(Category category) throws Exception;

	List<Category> getAllCategory() throws Exception;

	List<Category> getCategoryById(int categoryId) throws Exception;

	List<Category> getAllActiveCategory() throws Exception;

	void updateCategory(int categoryId, boolean active) throws Exception;

	void saveItem(Item item) throws Exception;

	void updateItem(int itemId, boolean isActive) throws Exception;
	
	Item getItemById(int itemId) throws Exception;

}
