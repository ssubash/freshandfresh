package com.freshandfresh.api.service;

import java.util.List;

import com.freshandfresh.api.entity.Category;
import com.freshandfresh.api.entity.Item;
import com.freshandfresh.api.model.CategoryForm;

public interface ProductManagementService {

	void saveCategory(Category category) throws Exception;

	List<CategoryForm> getAllCategory() throws Exception;

	List<CategoryForm> getCategoryById(int categoryId) throws Exception;

	List<CategoryForm> getAllActiveCategory() throws Exception;

	void updateCategory(int categoryId, boolean active) throws Exception;

	void saveItem(Item item) throws Exception;

	void updateItem(int itemId, boolean isActive) throws Exception;

	Item getItemById(int itemId) throws Exception;

}
