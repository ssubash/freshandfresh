package com.freshandfresh.api.model;

import java.util.Date;
import java.util.List;

public class OrderBookForm {

	private long id;
	private UserForm orderedUser;
	private AddressForm deliveryAddress;
	private double price;
	private String orderType;
	private Date receivedDate;
	private Date expectedDeliveryDate;
	private String expectedDeliveryTime;
	private String customerRemarks;
	private int customerSatisfactionMark;
	private int noOfContainerDispatched;
	private int noOfContainerReceived;
	private double amountReceived;
	private String paymentMode;
	private ActionForm action;
	private String remarks;
	private UserForm deliveryUser;
	private List<OrderDetailForm> orderDetails;
	private Date updatedDate;
	private List<ActionForm> actions;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public Date getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}

	public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}

	public String getExpectedDeliveryTime() {
		return expectedDeliveryTime;
	}

	public void setExpectedDeliveryTime(String expectedDeliveryTime) {
		this.expectedDeliveryTime = expectedDeliveryTime;
	}

	public String getCustomerRemarks() {
		return customerRemarks;
	}

	public void setCustomerRemarks(String customerRemarks) {
		this.customerRemarks = customerRemarks;
	}

	public int getCustomerSatisfactionMark() {
		return customerSatisfactionMark;
	}

	public void setCustomerSatisfactionMark(int customerSatisfactionMark) {
		this.customerSatisfactionMark = customerSatisfactionMark;
	}

	public int getNoOfContainerDispatched() {
		return noOfContainerDispatched;
	}

	public void setNoOfContainerDispatched(int noOfContainerDispatched) {
		this.noOfContainerDispatched = noOfContainerDispatched;
	}

	public int getNoOfContainerReceived() {
		return noOfContainerReceived;
	}

	public void setNoOfContainerReceived(int noOfContainerReceived) {
		this.noOfContainerReceived = noOfContainerReceived;
	}

	public double getAmountReceived() {
		return amountReceived;
	}

	public void setAmountReceived(double amountReceived) {
		this.amountReceived = amountReceived;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public ActionForm getAction() {
		return action;
	}

	public void setAction(ActionForm action) {
		this.action = action;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<ActionForm> getActions() {
		return actions;
	}

	public void setActions(List<ActionForm> actions) {
		this.actions = actions;
	}

	public UserForm getOrderedUser() {
		return orderedUser;
	}

	public void setOrderedUser(UserForm orderedUser) {
		this.orderedUser = orderedUser;
	}

	public AddressForm getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(AddressForm deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public UserForm getDeliveryUser() {
		return deliveryUser;
	}

	public void setDeliveryUser(UserForm deliveryUser) {
		this.deliveryUser = deliveryUser;
	}

	public List<OrderDetailForm> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetailForm> orderDetails) {
		this.orderDetails = orderDetails;
	}
}
