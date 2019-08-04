package com.freshandfresh.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freshandfresh.api.dao.ProductManagementDao;
import com.freshandfresh.api.entity.Category;
import com.freshandfresh.api.entity.Item;
import com.freshandfresh.api.handler.ObjectHandler;
import com.freshandfresh.api.model.CategoryForm;
import com.freshandfresh.api.service.ProductManagementService;

@Service
public class ProductManagementServiceImpl implements ProductManagementService {

	@Autowired
	ProductManagementDao productManagementDao;

	@Autowired
	ObjectHandler objectHandler;

	@Override
	public void saveCategory(Category category) throws Exception {
		productManagementDao.saveCategory(category);
	}

	@Override
	public List<CategoryForm> getAllCategory() throws Exception {
		List<Category> categoryList = productManagementDao.getAllCategory();
		List<CategoryForm> categoryFormList = new ArrayList<CategoryForm>();
		if (null != categoryList && !categoryList.isEmpty()) {
			for (Category category : categoryList) {
				CategoryForm categoryForm = objectHandler.convertCategoryEntityToForm(category);
				categoryFormList.add(categoryForm);
			}
		}
		return categoryFormList;
	}

	@Override
	public List<CategoryForm> getCategoryById(int categoryId) throws Exception {
		List<Category> categoryList = productManagementDao.getCategoryById(categoryId);
		List<CategoryForm> categoryFormList = new ArrayList<CategoryForm>();
		if (null != categoryList && !categoryList.isEmpty()) {
			for (Category category : categoryList) {
				CategoryForm categoryForm = objectHandler.convertCategoryEntityToForm(category);
				categoryFormList.add(categoryForm);
			}
		}
		return categoryFormList;
	}

	@Override
	public List<CategoryForm> getAllActiveCategory() throws Exception {
		List<Category> categoryList = productManagementDao.getAllActiveCategory();
		List<CategoryForm> categoryFormList = new ArrayList<CategoryForm>();
		if (null != categoryList && !categoryList.isEmpty()) {
			for (Category category : categoryList) {
				CategoryForm categoryForm = objectHandler.convertCategoryEntityToForm(category);
				categoryFormList.add(categoryForm);
			}
		}
		return categoryFormList;
	}

	@Override
	public void updateCategory(int categoryId, boolean isActive) throws Exception {
		productManagementDao.updateCategory(categoryId, isActive);
	}

	@Override
	public void saveItem(Item item) throws Exception {
		productManagementDao.saveItem(item);
	}

	@Override
	public void updateItem(int itemId, boolean isActive) throws Exception {
		productManagementDao.updateItem(itemId, isActive);
	}

	@Override
	public Item getItemById(int itemId) throws Exception {
		return productManagementDao.getItemById(itemId);
	}

}
