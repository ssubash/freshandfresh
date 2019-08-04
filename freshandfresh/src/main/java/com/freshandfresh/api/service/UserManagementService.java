package com.freshandfresh.api.service;

import java.util.List;

import com.freshandfresh.api.entity.Role;
import com.freshandfresh.api.entity.User;
import com.freshandfresh.api.model.AddressForm;
import com.freshandfresh.api.model.RoleForm;
import com.freshandfresh.api.model.UserForm;

public interface UserManagementService {

	public User saveUser(User user) throws Exception;

	public List<UserForm> getAllUsers() throws Exception;

	public List<RoleForm> getAllRoles() throws Exception;

	public Role getRoleByName(String roleName) throws Exception;

	public void deleteUser(long userId, Boolean isActive) throws Exception;

	public void saveAddress(AddressForm addressForm) throws Exception;

	public List<AddressForm> getActiveAddressList(long userId) throws Exception;

	public void deleteAddress(int addressId) throws Exception;

	public List<UserForm> getActiveDeliveryUsers() throws Exception;

}
