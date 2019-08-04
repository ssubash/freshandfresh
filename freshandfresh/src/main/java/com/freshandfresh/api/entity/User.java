package com.freshandfresh.api.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }),
		@UniqueConstraint(columnNames = { "mobileNumber" }) })
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank
	@Size(min = 3, max = 50)
	private String fullName;

	@NotBlank
	@Size(min = 10, max = 20)
	private String mobileNumber;

	@NotBlank
	@Size(min = 3, max = 20)
	private String username;

	@NotBlank
	@Size(min = 6, max = 100)
	private String password;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	private Role role;

	@Column(columnDefinition = "tinyint(1) default 1")
	private Boolean isActive;

	@Lob
	private byte[] profilePic;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Address> listOfAddress;

	@CreationTimestamp
	private Date createdDate;

	@UpdateTimestamp
	private Date updatedDate;

	@OneToMany(mappedBy = "orderedUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<OrderBook> orderedUserBooks;

	@OneToMany(mappedBy = "deliveryUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<OrderBook> deliveryUserBooks;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Boolean isActive() {
		return isActive;
	}

	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public byte[] getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
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

	public Set<Address> getListOfAddress() {
		return listOfAddress;
	}

	public void setListOfAddress(Set<Address> listOfAddress) {
		this.listOfAddress = listOfAddress;
	}

	public Set<OrderBook> getOrderedUserBooks() {
		return orderedUserBooks;
	}

	public void setOrderedUserBooks(Set<OrderBook> orderedUserBooks) {
		this.orderedUserBooks = orderedUserBooks;
	}

	public Set<OrderBook> getDeliveryUserBooks() {
		return deliveryUserBooks;
	}

	public void setDeliveryUserBooks(Set<OrderBook> deliveryUserBooks) {
		this.deliveryUserBooks = deliveryUserBooks;
	}

	@PrePersist
	protected void onCreate() {
		this.createdDate = new Date();
		if (null == username || username.isEmpty()) {
			this.username = mobileNumber;
		}
		if (null == isActive) {
			isActive = true;
		}
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedDate = new Date();
	}
}