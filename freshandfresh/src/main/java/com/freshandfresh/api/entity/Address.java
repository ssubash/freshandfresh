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
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank
	private String apartmentName;

	@NotBlank
	private String blockName;

	@NotBlank
	private String doorNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	private Boolean isActive;

	@CreationTimestamp
	private Date createdDate;

	@UpdateTimestamp
	private Date updatedDate;

	@OneToMany(mappedBy = "deliveryAddress", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<OrderBook> deliveredOrderBooks;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApartmentName() {
		return apartmentName;
	}

	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getDoorNumber() {
		return doorNumber;
	}

	public void setDoorNumber(String doorNumber) {
		this.doorNumber = doorNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean isActive() {
		return isActive;
	}

	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@PrePersist
	protected void onCreate() {
		this.createdDate = new Date();
		if (null == isActive) {
			isActive = true;
		}
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedDate = new Date();
	}

	public Set<OrderBook> getDeliveredOrderBooks() {
		return deliveredOrderBooks;
	}

	public void setDeliveredOrderBooks(Set<OrderBook> deliveredOrderBooks) {
		this.deliveredOrderBooks = deliveredOrderBooks;
	}

	@Override
	public String toString() {
		return this.apartmentName + ", " + this.blockName + ", " + this.doorNumber;
	}
}
