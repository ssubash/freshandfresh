package com.freshandfresh.api.util;

public class Constant {

	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_DELIVERY = "ROLE_DELIVERY";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";

	/*
	 * User management operation messages
	 */
	public static final String USER_REGISTER_SUCCESS = "user.register.success";
	public static final String USER_SAVE_SUCCESS = "user.save.success";
	public static final String USER_DEACTIVE_SUCCESS = "user.deactive.success";
	public static final String USER_ACTIVE_SUCCESS = "user.active.success";
	public static final String USER_ADDRESS_SAVE_SUCCESS = "user.address.save.success";
	public static final String USER_ADDRESS_DELETE_SUCCESS = "user.address.delete.success";

	/*
	 * Product management operation messages
	 */
	public static final String PRODUCT_CATEGORY_SAVE_SUCCESS = "product.category.save.success";
	public static final String PRODUCT_CATEGORY_DEACTIVE_SUCCESS = "product.category.deactive.success";
	public static final String PRODUCT_CATEGORY_ACTIVE_SUCCESS = "product.category.active.success";

	public static final String PRODUCT_ITEM_SAVE_SUCCESS = "product.item.save.success";
	public static final String PRODUCT_ITEM_DEACTIVE_SUCCESS = "product.item.deactive.success";
	public static final String PRODUCT_ITEM_ACTIVE_SUCCESS = "product.item.active.success";

	/*
	 * Order management operation messages
	 */
	public static final String ORDER_SAVE_SUCCESS = "order.save.success";
	
	public static final String ITEM_NOT_PRESENT = "item.not.select";
	public static final String ORDER_INVALID = "order.invalid";
}
