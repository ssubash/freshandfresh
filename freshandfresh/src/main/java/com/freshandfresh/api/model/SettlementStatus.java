package com.freshandfresh.api.model;

import java.util.List;

public class SettlementStatus {

	private long customerId;
	private String customerName;
	private String mobileNumber;
	private String address;
	private double totalAmountToBePaid;
	private double totalAmountReceived;
	private double totalAmountToBeReceived;
	private int totalNoOfContainerDelivered;
	private int totalNoOfContainerReceived;
	private int totalNoOfContainerToBeReceived;
	private List<OrderBookForm> orderList;

	public double getTotalAmountToBePaid() {
		return totalAmountToBePaid;
	}

	public void setTotalAmountToBePaid(double totalAmountToBePaid) {
		this.totalAmountToBePaid = totalAmountToBePaid;
	}

	public double getTotalAmountReceived() {
		return totalAmountReceived;
	}

	public void setTotalAmountReceived(double totalAmountReceived) {
		this.totalAmountReceived = totalAmountReceived;
	}

	public double getTotalAmountToBeReceived() {
		return totalAmountToBeReceived;
	}

	public void setTotalAmountToBeReceived(double totalAmountToBeReceived) {
		this.totalAmountToBeReceived = totalAmountToBeReceived;
	}

	public int getTotalNoOfContainerDelivered() {
		return totalNoOfContainerDelivered;
	}

	public void setTotalNoOfContainerDelivered(int totalNoOfContainerDelivered) {
		this.totalNoOfContainerDelivered = totalNoOfContainerDelivered;
	}

	public int getTotalNoOfContainerReceived() {
		return totalNoOfContainerReceived;
	}

	public void setTotalNoOfContainerReceived(int totalNoOfContainerReceived) {
		this.totalNoOfContainerReceived = totalNoOfContainerReceived;
	}

	public int getTotalNoOfContainerToBeReceived() {
		return totalNoOfContainerToBeReceived;
	}

	public void setTotalNoOfContainerToBeReceived(int totalNoOfContainerToBeReceived) {
		this.totalNoOfContainerToBeReceived = totalNoOfContainerToBeReceived;
	}

	public List<OrderBookForm> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderBookForm> orderList) {
		this.orderList = orderList;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
}
