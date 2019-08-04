package com.freshandfresh.api.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.freshandfresh.api.entity.User;

public class UserPrinciple implements UserDetails {
	private static final long serialVersionUID = 1L;

	private long id;

	private String fullName;

	private String mobileNumber;

	private String username;

	@JsonIgnore
	private String password;

	private boolean isActive;

	private byte[] profilePic;

	private int roleId;

	private String roleName;

	private Collection<? extends GrantedAuthority> authorities;

	public UserPrinciple(User user, Collection<? extends GrantedAuthority> authorities) {
		this.id = user.getId();
		this.fullName = user.getFullName();
		this.mobileNumber = user.getMobileNumber();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.authorities = authorities;
		this.isActive = user.isActive();
		this.profilePic = user.getProfilePic();
		this.roleId = user.getRole().getId();
		this.roleName = user.getRole().getName();
	}

	public static UserPrinciple build(User user) {
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole().getName());
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(grantedAuthority);

		return new UserPrinciple(user, authorities);
	}

	public Long getId() {
		return id;
	}

	public String getFullName() {
		return fullName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public boolean isActive() {
		return isActive;
	}

	public byte[] getProfilePic() {
		return profilePic;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		UserPrinciple user = (UserPrinciple) o;
		return Objects.equals(id, user.id);
	}

	public int getRoleId() {
		return roleId;
	}

	public String getRoleName() {
		return roleName;
	}

}