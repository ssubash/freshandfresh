package com.freshandfresh.api.dao;

import java.util.List;

import com.freshandfresh.api.entity.Address;
import com.freshandfresh.api.entity.Role;
import com.freshandfresh.api.entity.User;

public interface UserManagementDao {

	public User saveUser(User user) throws Exception;

	public List<User> getAllUsers() throws Exception;

	public User getUserByMobileNumberOrUserName(User user) throws Exception;

	public List<Role> getAllRoles() throws Exception;

	public Role getRoleByName(String roleName) throws Exception;

	public void deleteUser(long userId, Boolean isActive) throws Exception;

	public void saveAddress(Address address) throws Exception;

	public List<Address> getActiveAddressList(long userId) throws Exception;

	public void deleteAddress(int addressId) throws Exception;

	public List<User> getActiveDeliveryUsers() throws Exception;

}
