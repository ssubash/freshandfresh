package com.freshandfresh.api.controller;

import java.util.Locale;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.freshandfresh.api.entity.Category;
import com.freshandfresh.api.entity.Item;
import com.freshandfresh.api.model.ItemForm;
import com.freshandfresh.api.model.ResponseMessage;
import com.freshandfresh.api.util.ApiRegistry;
import com.freshandfresh.api.util.Constant;

@RestController
@RequestMapping(ApiRegistry.PM_PRODUCT)
public class ProductManagementController extends BaseController {

	@PostMapping(ApiRegistry.PM_CATEGORY_SAVE)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> saveCategory(@RequestBody Category category) throws Exception {
		ResponseMessage response = new ResponseMessage();
		productManagementService.saveCategory(category);
		response.setMessage(messageSource.getMessage(Constant.PRODUCT_CATEGORY_SAVE_SUCCESS, null, Locale.ENGLISH));
		response.setStatus(true);
		return sendResponseMessage(response);
	}

	@GetMapping(ApiRegistry.PM_CATEGORY_READ_ALL)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllCategory() throws Exception {
		ResponseMessage response = new ResponseMessage();
		response.setData(productManagementService.getAllCategory());
		response.setStatus(true);
		return sendResponseMessage(response);
	}

	@GetMapping(ApiRegistry.PM_CATEGORY_READ)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getCategory(@RequestParam("categoryId") int categoryId) throws Exception {
		ResponseMessage response = new ResponseMessage();
		response.setData(productManagementService.getCategoryById(categoryId));
		response.setStatus(true);
		return sendResponseMessage(response);
	}

	@GetMapping(ApiRegistry.PM_CATEGORY_READ_ACTIVE)
	public ResponseEntity<?> getAllActiveCategory() throws Exception {
		ResponseMessage response = new ResponseMessage();
		response.setData(productManagementService.getAllActiveCategory());
		response.setStatus(true);
		return sendResponseMessage(response);
	}

	@PostMapping(ApiRegistry.PM_CATEGORY_UPDATE)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteCategory(@RequestBody Category category) throws Exception {
		ResponseMessage response = new ResponseMessage();
		productManagementService.updateCategory(category.getId(), category.isActive());
		if (category.isActive()) {
			response.setMessage(
					messageSource.getMessage(Constant.PRODUCT_CATEGORY_ACTIVE_SUCCESS, null, Locale.ENGLISH));
		} else {
			response.setMessage(
					messageSource.getMessage(Constant.PRODUCT_CATEGORY_DEACTIVE_SUCCESS, null, Locale.ENGLISH));
		}
		response.setStatus(true);
		return sendResponseMessage(response);
	}

	@PostMapping(ApiRegistry.PM_ITEM_SAVE)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> saveItem(@RequestBody Item item) throws Exception {
		ResponseMessage response = new ResponseMessage();
		productManagementService.saveItem(item);
		response.setMessage(messageSource.getMessage(Constant.PRODUCT_ITEM_SAVE_SUCCESS, null, Locale.ENGLISH));
		response.setStatus(true);
		return sendResponseMessage(response);
	}

	@PostMapping(ApiRegistry.PM_ITEM_UPDATE)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateItem(@RequestBody ItemForm itemForm) throws Exception {
		ResponseMessage response = new ResponseMessage();
		productManagementService.updateItem(itemForm.getId(), itemForm.isActive());
		if (itemForm.isActive()) {
			response.setMessage(
					messageSource.getMessage(Constant.PRODUCT_ITEM_ACTIVE_SUCCESS, null, Locale.ENGLISH));
		} else {
			response.setMessage(
					messageSource.getMessage(Constant.PRODUCT_ITEM_DEACTIVE_SUCCESS, null, Locale.ENGLISH));
		}
		response.setStatus(true);
		return sendResponseMessage(response);
	}
}
