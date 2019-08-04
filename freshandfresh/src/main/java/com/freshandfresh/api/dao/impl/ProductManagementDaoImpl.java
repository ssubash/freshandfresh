package com.freshandfresh.api.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freshandfresh.api.dao.ProductManagementDao;
import com.freshandfresh.api.entity.Category;
import com.freshandfresh.api.entity.Item;
import com.freshandfresh.api.repository.CategoryRepository;
import com.freshandfresh.api.repository.ItemRepository;

@Service
public class ProductManagementDaoImpl implements ProductManagementDao {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ItemRepository itemRepository;

	@Override
	public void saveCategory(Category category) throws Exception {
		categoryRepository.save(category);
	}

	@Override
	public List<Category> getAllCategory() throws Exception {
		return categoryRepository.findAllByOrderByNameAsc();
	}

	@Override
	public List<Category> getCategoryById(int categoryId) throws Exception {
		return categoryRepository.findByIdOrderByNameAsc(categoryId);
	}

	@Override
	public List<Category> getAllActiveCategory() throws Exception {
		return categoryRepository.findAllByIsActiveAndItemsIsActiveOrderByNameAsc(true, true);
	}

	@Override
	public void updateCategory(int categoryId, boolean isActive) throws Exception {
		categoryRepository.updateCategory(categoryId, isActive);
	}

	@Override
	public void saveItem(Item item) throws Exception {
		itemRepository.save(item);
	}

	@Override
	public void updateItem(int itemId, boolean isActive) throws Exception {
		itemRepository.updateItem(itemId, isActive);
	}

	@Override
	public Item getItemById(int itemId) throws Exception {
		Optional<Item> item = itemRepository.findById(itemId);
		return item.isPresent() ? item.get() : null;
	}

}
