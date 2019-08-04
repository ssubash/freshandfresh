package com.freshandfresh.api.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class OrderBook {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ordered_user_id")
	private User orderedUser;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delivery_address_id")
	private Address deliveryAddress;

	private double price;

	private String orderType;

	@CreationTimestamp
	private Date receivedDate;

	@Temporal(TemporalType.DATE)
	private Date expectedDeliveryDate;

	private String expectedDeliveryTime;

	private String customerRemarks;

	private int customerSatisfactionMark;

	private int noOfContainerDispatched;

	private int noOfContainerReceived;

	private double amountReceived;

	private String paymentMode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "action_id")
	private Action action;

	private String remarks;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delivery_user_id")
	private User deliveryUser;

	@OneToMany(mappedBy = "orderBook", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<OrderDetail> orderDetails;

	@UpdateTimestamp
	private Date updatedDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getOrderedUser() {
		return orderedUser;
	}

	public void setOrderedUser(User orderedUser) {
		this.orderedUser = orderedUser;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
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

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public User getDeliveryUser() {
		return deliveryUser;
	}

	public void setDeliveryUser(User deliveryUser) {
		this.deliveryUser = deliveryUser;
	}

	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@PrePersist
	protected void onCreate() {
		this.receivedDate = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedDate = new Date();
	}
}
