package com.freshandfresh.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignupForm {

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
}
