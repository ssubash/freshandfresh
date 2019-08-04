package com.freshandfresh.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserForm {

	private long id;

	@NotBlank
	@Size(min = 3, max = 50)
	private String fullName;

	@NotBlank
	@Size(min = 10, max = 50)
	private String mobileNumber;

	@NotBlank
	@Size(min = 3, max = 50)
	private String username;

	@NotBlank
	@Size(min = 6, max = 40)
	private String password;

	private RoleForm role;

	private Boolean isActive;

	private byte[] profilePic;

	private String token;

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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public RoleForm getRole() {
		return role;
	}

	public void setRole(RoleForm role) {
		this.role = role;
	}

}