package com.freshandfresh.api.handler;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.freshandfresh.api.entity.Action;
import com.freshandfresh.api.entity.Address;
import com.freshandfresh.api.entity.Category;
import com.freshandfresh.api.entity.Item;
import com.freshandfresh.api.entity.OrderBook;
import com.freshandfresh.api.entity.OrderDetail;
import com.freshandfresh.api.entity.Role;
import com.freshandfresh.api.entity.User;
import com.freshandfresh.api.enums.ActionName;
import com.freshandfresh.api.model.ActionForm;
import com.freshandfresh.api.model.AddressForm;
import com.freshandfresh.api.model.CategoryForm;
import com.freshandfresh.api.model.ItemForm;
import com.freshandfresh.api.model.OrderBookForm;
import com.freshandfresh.api.model.OrderDetailForm;
import com.freshandfresh.api.model.RoleForm;
import com.freshandfresh.api.model.SettlementStatus;
import com.freshandfresh.api.model.SignupForm;
import com.freshandfresh.api.model.UserForm;
import com.freshandfresh.api.model.UserPrinciple;
import com.freshandfresh.api.service.OrderManagementService;
import com.freshandfresh.api.service.ProductManagementService;
import com.freshandfresh.api.util.Constant;

@Component
public class ObjectHandler {

	@Autowired
	OrderManagementService orderManagementService;

	@Autowired
	ProductManagementService productManagementService;

	@Autowired
	MessageSource messageSource;

	public long findLoginUserId() throws Exception {
		UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return userPrinciple.getId();
	}

	public String findLoginUserRoleName() throws Exception {
		UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return userPrinciple.getRoleName();
	}

	public List<ActionForm> findActionByLoginRole() throws Exception {
		UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		int roleId = userPrinciple.getRoleId();
		return orderManagementService.getActions(roleId);
	}

	public List<ActionForm> findPossibleActions(String currentAction) throws Exception {
		UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		int roleId = userPrinciple.getRoleId();
		String roleName = userPrinciple.getRoleName();
		List<ActionForm> actions = orderManagementService.getActions(roleId);
		if (null != currentAction && !currentAction.isEmpty()) {
			switch (roleName) {
			case Constant.ROLE_USER:
				if (!ActionName.PENDING.getName().equals(currentAction)) {
					actions.clear();
				}
				break;
			case Constant.ROLE_DELIVERY:
				if (!ActionName.ASSIGN.getName().equals(currentAction)
						|| ActionName.CLOSE.getName().equals(currentAction)) {
					actions.clear();
				}
				break;
			case Constant.ROLE_ADMIN:
				if (ActionName.REJECT.getName().equals(currentAction)) {
					actions.clear();
				}
				break;
			default:
				break;
			}
		}

		return actions;
	}

	public User convertSignupFormToUserEntity(SignupForm signupForm) throws Exception {
		User user = new User();
		user.setFullName(signupForm.getFullName());
		user.setMobileNumber(signupForm.getMobileNumber());
		user.setUsername(signupForm.getUsername());
		user.setPassword(signupForm.getPassword());
		return user;
	}

	public User convertUserFormToUserEntity(UserForm userForm) throws Exception {
		User user = new User();
		user.setId(userForm.getId());
		user.setFullName(userForm.getFullName());
		user.setMobileNumber(userForm.getMobileNumber());
		user.setUsername(userForm.getUsername());
		user.setPassword(userForm.getPassword());
		if (null != userForm.getRole()) {
			user.setRole(convertRoleFormToEntity(userForm.getRole()));
		}
		user.setActive(userForm.isActive());
		user.setProfilePic(userForm.getProfilePic());
		return user;
	}

	public UserForm convertUserEntityToUserForm(User user) throws Exception {
		UserForm userForm = new UserForm();
		userForm.setId(user.getId());
		userForm.setFullName(user.getFullName());
		userForm.setMobileNumber(user.getMobileNumber());
		userForm.setUsername(user.getUsername());
		userForm.setActive(user.isActive());
		userForm.setProfilePic(user.getProfilePic());
		userForm.setRole(convertRoleEntityToForm(user.getRole()));
		return userForm;
	}

	public UserForm convertUserPrincipleToUserForm(UserPrinciple userPrinciple, String jwt) throws Exception {
		UserForm userForm = new UserForm();
		userForm.setId(userPrinciple.getId());
		userForm.setFullName(userPrinciple.getFullName());
		userForm.setMobileNumber(userPrinciple.getMobileNumber());
		userForm.setUsername(userPrinciple.getUsername());
		userForm.setActive(userPrinciple.isActive());
		userForm.setProfilePic(userPrinciple.getProfilePic());
		RoleForm roleForm = new RoleForm();
		roleForm.setId(userPrinciple.getRoleId());
		roleForm.setName(userPrinciple.getRoleName());
		userForm.setRole(roleForm);
		userForm.setToken(jwt);
		return userForm;
	}

	public RoleForm convertRoleEntityToForm(Role role) throws Exception {
		RoleForm roleForm = new RoleForm();
		roleForm.setId(role.getId());
		roleForm.setName(role.getName());
		return roleForm;
	}

	public Role convertRoleFormToEntity(RoleForm roleForm) throws Exception {
		Role role = new Role();
		role.setId(roleForm.getId());
		role.setName(roleForm.getName());
		return role;
	}

	public CategoryForm convertCategoryEntityToForm(Category category) {
		CategoryForm categoryForm = new CategoryForm();
		categoryForm.setId(category.getId());
		categoryForm.setName(category.getName());
		categoryForm.setActive(category.isActive());
		Set<Item> itemList = category.getItems();
		List<ItemForm> items = new ArrayList<ItemForm>();
		if (null != itemList && !itemList.isEmpty()) {
			itemList.forEach(item -> {
				items.add(convertItemEntityToForm(item));
			});
		}
		categoryForm.setItems(items);
		return categoryForm;
	}

	public ItemForm convertItemEntityToForm(Item item) {
		ItemForm itemForm = new ItemForm();
		itemForm.setId(item.getId());
		itemForm.setName(item.getName());
		itemForm.setImage(item.getImage());
		itemForm.setAvailable(item.isAvailable());
		itemForm.setActive(item.isActive());
		itemForm.setAvailableFrom(item.getAvailableFrom());
		itemForm.setAvilableTo(item.getAvilableTo());
		itemForm.setUnitRange(item.getUnitRange());
		itemForm.setPrice(item.getPrice());
		return itemForm;
	}

	public Item convertItemFormToEntity(ItemForm itemForm) {
		Item item = new Item();
		item.setId(itemForm.getId());
		item.setName(itemForm.getName());
		item.setImage(itemForm.getImage());
		item.setAvailable(itemForm.isAvailable());
		item.setActive(itemForm.isActive());
		item.setAvailableFrom(itemForm.getAvailableFrom());
		item.setAvilableTo(itemForm.getAvilableTo());
		item.setUnitRange(itemForm.getUnitRange());
		item.setPrice(itemForm.getPrice());
		return item;
	}

	public AddressForm convertAddressEntityToForm(Address address) {
		AddressForm addressForm = new AddressForm();
		addressForm.setId(address.getId());
		addressForm.setApartmentName(address.getApartmentName());
		addressForm.setBlockName(address.getBlockName());
		addressForm.setDoorNumber(address.getDoorNumber());
		addressForm.setUserId(address.getUser().getId());
		addressForm.setActive(address.isActive());
		return addressForm;
	}

	public Address convertAddressFormToEntity(AddressForm addressForm) {
		Address address = new Address();
		address.setId(addressForm.getId());
		address.setApartmentName(addressForm.getApartmentName());
		address.setBlockName(addressForm.getBlockName());
		address.setDoorNumber(addressForm.getDoorNumber());
		User user = new User();
		user.setId(addressForm.getUserId());
		address.setUser(user);
		address.setActive(address.isActive());
		return address;
	}

	public ActionForm convertActionEntityToForm(Action action) {
		ActionForm actionForm = new ActionForm();
		actionForm.setId(action.getId());
		actionForm.setName(action.getName());
		actionForm.setStatus(action.getStatus());
		return actionForm;
	}

	public Action convertActionFormToEntity(ActionForm actionForm) {
		Action action = new Action();
		action.setId(actionForm.getId());
		action.setName(actionForm.getName());
		action.setStatus(actionForm.getStatus());
		return action;
	}

	public OrderBook convertOrderBookFormToEntity(OrderBookForm orderBookForm) throws Exception {

		OrderBook orderBook = new OrderBook();
		orderBook.setId(orderBookForm.getId());
		orderBook.setOrderedUser(convertUserFormToUserEntity(orderBookForm.getOrderedUser()));
		orderBook.setDeliveryAddress(convertAddressFormToEntity(orderBookForm.getDeliveryAddress()));
		orderBook.setOrderType(orderBookForm.getOrderType());
		orderBook.setExpectedDeliveryDate(orderBookForm.getExpectedDeliveryDate());
		orderBook.setExpectedDeliveryTime(orderBookForm.getExpectedDeliveryTime());
		orderBook.setCustomerRemarks(orderBookForm.getCustomerRemarks());
		orderBook.setCustomerSatisfactionMark(orderBookForm.getCustomerSatisfactionMark());
		orderBook.setNoOfContainerDispatched(orderBookForm.getNoOfContainerDispatched());
		orderBook.setNoOfContainerReceived(orderBookForm.getNoOfContainerReceived());
		orderBook.setAmountReceived(orderBookForm.getAmountReceived());
		orderBook.setPaymentMode(orderBookForm.getPaymentMode());
		if (orderBook.getId() == 0) {
			List<ActionForm> actions = findActionByLoginRole();
			Optional<ActionForm> actionForm = actions.stream()
					.filter(action -> action.getName().equals(ActionName.PENDING.getName())).findFirst();
			if (actionForm.isPresent()) {
				orderBook.setAction(convertActionFormToEntity(actionForm.get()));
			}
		} else {
			orderBook.setAction(convertActionFormToEntity(orderBookForm.getAction()));
		}
		orderBook.setRemarks(orderBookForm.getRemarks());
		if (null != orderBookForm.getDeliveryUser()) {
			orderBook.setDeliveryUser(convertUserFormToUserEntity(orderBookForm.getDeliveryUser()));
		}

		List<OrderDetailForm> orderDetails = orderBookForm.getOrderDetails();
		Set<OrderDetail> orderList = new LinkedHashSet<>();
		if (null != orderDetails && !orderDetails.isEmpty()) {
			double totalPrice = 0;
			for (OrderDetailForm orderDetailForm : orderDetails) {
				OrderDetail orderDetail = convertOrderDetailFormToEntity(orderDetailForm);
				if (null != orderDetail) {
					orderDetail.setOrderBook(orderBook);
					orderList.add(orderDetail);
					totalPrice += orderDetail.getPrice();
				} else {
					throw new Exception(messageSource.getMessage(Constant.ORDER_INVALID, null, Locale.ENGLISH));
				}
			}
			orderBook.setPrice(totalPrice);
		} else {
			throw new Exception(messageSource.getMessage(Constant.ORDER_INVALID, null, Locale.ENGLISH));
		}
		orderBook.setOrderDetails(orderList);
		return orderBook;
	}

	public OrderDetail convertOrderDetailFormToEntity(OrderDetailForm orderDetailForm) throws Exception {
		OrderDetail orderDetail = null;
		if (null != orderDetailForm.getItem()) {
			int itemId = orderDetailForm.getItem().getId();
			Item item = productManagementService.getItemById(itemId);
			if (null != item) {
				orderDetail = new OrderDetail();
				orderDetail.setId(orderDetailForm.getId());
				orderDetail.setItem(convertItemFormToEntity(orderDetailForm.getItem()));
				orderDetail.setNoOfUnit(orderDetailForm.getNoOfUnit());
				double totalPrice = orderDetailForm.getNoOfUnit() * orderDetailForm.getItem().getPrice();
				orderDetail.setPrice(totalPrice);
			} else {
				throw new Exception(messageSource.getMessage(Constant.ITEM_NOT_PRESENT, null, Locale.ENGLISH));
			}
		} else {
			throw new Exception(messageSource.getMessage(Constant.ITEM_NOT_PRESENT, null, Locale.ENGLISH));
		}

		return orderDetail;
	}

	public OrderDetailForm convertOrderDetailEntityToForm(OrderDetail orderDetail) {
		OrderDetailForm orderDetailForm = new OrderDetailForm();
		orderDetailForm.setId(orderDetail.getId());
		orderDetailForm.setItem(convertItemEntityToForm(orderDetail.getItem()));
		orderDetailForm.setNoOfUnit(orderDetail.getNoOfUnit());
		orderDetailForm.setPrice(orderDetail.getPrice());
		return orderDetailForm;
	}

	public OrderBookForm convertOrderBookEntityToForm(OrderBook orderBook) throws Exception {

		OrderBookForm orderBookForm = new OrderBookForm();
		orderBookForm.setId(orderBook.getId());
		orderBookForm.setOrderedUser(convertUserEntityToUserForm(orderBook.getOrderedUser()));
		orderBookForm.setDeliveryAddress(convertAddressEntityToForm(orderBook.getDeliveryAddress()));
		orderBookForm.setOrderType(orderBook.getOrderType());
		orderBookForm.setExpectedDeliveryDate(orderBook.getExpectedDeliveryDate());
		orderBookForm.setExpectedDeliveryTime(orderBook.getExpectedDeliveryTime());
		orderBookForm.setCustomerRemarks(orderBook.getCustomerRemarks());
		orderBookForm.setCustomerSatisfactionMark(orderBook.getCustomerSatisfactionMark());
		orderBookForm.setNoOfContainerDispatched(orderBook.getNoOfContainerDispatched());
		orderBookForm.setNoOfContainerReceived(orderBook.getNoOfContainerReceived());
		orderBookForm.setAmountReceived(orderBook.getAmountReceived());
		orderBookForm.setPaymentMode(orderBook.getPaymentMode());
		orderBookForm.setAction(convertActionEntityToForm(orderBook.getAction()));
		orderBookForm.setRemarks(orderBook.getRemarks());
		orderBookForm.setDeliveryUser(convertUserEntityToUserForm(orderBook.getDeliveryUser()));

		List<ActionForm> actions = findPossibleActions(orderBook.getAction().getName());
		orderBookForm.setActions(actions);

		Set<OrderDetail> orderList = orderBook.getOrderDetails();
		List<OrderDetailForm> orderDetails = new ArrayList<OrderDetailForm>();
		if (null != orderList && !orderList.isEmpty()) {
			for (OrderDetail orderDetail : orderList) {
				OrderDetailForm orderDetailForm = convertOrderDetailEntityToForm(orderDetail);
				orderDetails.add(orderDetailForm);
			}
		}
		orderBookForm.setOrderDetails(orderDetails);
		return orderBookForm;
	}

	public List<OrderBookForm> convertOrderBookEntityListToFormList(List<OrderBook> orderBookList) throws Exception {
		List<OrderBookForm> orders = new ArrayList<OrderBookForm>();
		if (null != orderBookList && !orderBookList.isEmpty()) {
			for (OrderBook orderBook : orderBookList) {
				orders.add(convertOrderBookEntityToForm(orderBook));
			}
		}
		return orders;
	}

	public SettlementStatus getCustomerSettlementPendingReport(List<OrderBook> orderBookList) throws Exception {

		SettlementStatus settlementStatus = null;
		List<OrderBookForm> orders = new ArrayList<OrderBookForm>();

		if (null != orderBookList && !orderBookList.isEmpty()) {
			settlementStatus = new SettlementStatus();
			double totalAmountToBePaid = 0;
			double totalAmountReceived = 0;
			double totalAmountToBeReceived = 0;
			int totalNoOfContainerDelivered = 0;
			int totalNoOfContainerReceived = 0;
			int totalNoOfContainerToBeReceived = 0;

			for (OrderBook orderBook : orderBookList) {
				orders.add(convertOrderBookEntityToForm(orderBook));
				totalAmountToBePaid += orderBook.getPrice();
				totalAmountReceived += orderBook.getAmountReceived();
				totalNoOfContainerDelivered += orderBook.getNoOfContainerDispatched();
				totalNoOfContainerReceived += orderBook.getNoOfContainerReceived();
			}

			totalAmountToBeReceived = totalAmountToBePaid - totalAmountReceived;
			totalNoOfContainerToBeReceived = totalNoOfContainerDelivered - totalNoOfContainerReceived;

			settlementStatus.setTotalAmountReceived(totalAmountReceived);
			settlementStatus.setTotalAmountToBePaid(totalAmountToBePaid);
			settlementStatus.setTotalAmountToBeReceived(totalAmountToBeReceived);
			settlementStatus.setTotalNoOfContainerDelivered(totalNoOfContainerDelivered);
			settlementStatus.setTotalNoOfContainerReceived(totalNoOfContainerReceived);
			settlementStatus.setTotalNoOfContainerToBeReceived(totalNoOfContainerToBeReceived);
		}
		return settlementStatus;
	}

	public List<SettlementStatus> getAllSettlementPendingReport(List<OrderBook> orderBookList) throws Exception {

		List<SettlementStatus> settlementStatusList = new ArrayList<SettlementStatus>();
		List<OrderBookForm> orders = null;
		double totalAmountToBePaid = 0;
		double totalAmountReceived = 0;
		double totalAmountToBeReceived = 0;
		int totalNoOfContainerDelivered = 0;
		int totalNoOfContainerReceived = 0;
		int totalNoOfContainerToBeReceived = 0;

		if (null != orderBookList && !orderBookList.isEmpty()) {

			long currentUserId = 0;
			long previousUserId = 0;
			SettlementStatus settlementStatus = null;

			for (int i = 0; i <= orderBookList.size(); i++) {
				OrderBook orderBook = orderBookList.get(i);
				currentUserId = orderBook.getOrderedUser().getId();
				if (i == 0) {
					orders = new ArrayList<OrderBookForm>();
					settlementStatus = new SettlementStatus();
					previousUserId = currentUserId;
					settlementStatus.setCustomerId(orderBook.getOrderedUser().getId());
					settlementStatus.setCustomerName(orderBook.getOrderedUser().getFullName());
					settlementStatus.setAddress(orderBook.getDeliveryAddress().toString());
					settlementStatus.setMobileNumber(orderBook.getOrderedUser().getMobileNumber());
				}

				if (previousUserId == currentUserId) {
					orders.add(convertOrderBookEntityToForm(orderBook));
					totalAmountToBePaid += orderBook.getPrice();
					totalAmountReceived += orderBook.getAmountReceived();
					totalNoOfContainerDelivered += orderBook.getNoOfContainerDispatched();
					totalNoOfContainerReceived += orderBook.getNoOfContainerReceived();
				} else {

					totalAmountToBeReceived = totalAmountToBePaid - totalAmountReceived;
					totalNoOfContainerToBeReceived = totalNoOfContainerDelivered - totalNoOfContainerReceived;

					settlementStatus.setTotalAmountReceived(totalAmountReceived);
					settlementStatus.setTotalAmountToBePaid(totalAmountToBePaid);
					settlementStatus.setTotalAmountToBeReceived(totalAmountToBeReceived);
					settlementStatus.setTotalNoOfContainerDelivered(totalNoOfContainerDelivered);
					settlementStatus.setTotalNoOfContainerReceived(totalNoOfContainerReceived);
					settlementStatus.setTotalNoOfContainerToBeReceived(totalNoOfContainerToBeReceived);
					settlementStatusList.add(settlementStatus);

					settlementStatus = new SettlementStatus();
					orders = new ArrayList<OrderBookForm>();
					previousUserId = currentUserId;
					orders.add(convertOrderBookEntityToForm(orderBook));
					totalAmountToBePaid = orderBook.getPrice();
					totalAmountReceived = orderBook.getAmountReceived();
					totalNoOfContainerDelivered = orderBook.getNoOfContainerDispatched();
					totalNoOfContainerReceived = orderBook.getNoOfContainerReceived();
				}
			}

			totalAmountToBeReceived = totalAmountToBePaid - totalAmountReceived;
			totalNoOfContainerToBeReceived = totalNoOfContainerDelivered - totalNoOfContainerReceived;

			settlementStatus.setTotalAmountReceived(totalAmountReceived);
			settlementStatus.setTotalAmountToBePaid(totalAmountToBePaid);
			settlementStatus.setTotalAmountToBeReceived(totalAmountToBeReceived);
			settlementStatus.setTotalNoOfContainerDelivered(totalNoOfContainerDelivered);
			settlementStatus.setTotalNoOfContainerReceived(totalNoOfContainerReceived);
			settlementStatus.setTotalNoOfContainerToBeReceived(totalNoOfContainerToBeReceived);
			settlementStatusList.add(settlementStatus);

		}
		return settlementStatusList;
	}
}
