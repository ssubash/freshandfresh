package com.freshandfresh.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freshandfresh.api.dao.UserManagementDao;
import com.freshandfresh.api.entity.Address;
import com.freshandfresh.api.entity.Role;
import com.freshandfresh.api.entity.User;
import com.freshandfresh.api.handler.ObjectHandler;
import com.freshandfresh.api.model.AddressForm;
import com.freshandfresh.api.model.RoleForm;
import com.freshandfresh.api.model.UserForm;
import com.freshandfresh.api.service.UserManagementService;

@Service
public class UserManagementServiceImpl implements UserManagementService {

	@Autowired
	UserManagementDao userManagementDao;

	@Autowired
	ObjectHandler objectHandler;

	@Override
	public User saveUser(User user) throws Exception {

		User existUser = userManagementDao.getUserByMobileNumberOrUserName(user);
		if (null != existUser) {
			if (user.getId() != existUser.getId()) {
				throw new Exception("Fail! -> Cause: User already registered with this Mobile Number/Username.");
			}
		}

		return userManagementDao.saveUser(user);
	}

	@Override
	public List<UserForm> getAllUsers() throws Exception {
		List<UserForm> userFormList = new ArrayList<UserForm>();
		List<User> userList = userManagementDao.getAllUsers();
		if (null != userList && !userList.isEmpty()) {
			for (User user : userList) {
				UserForm userForm = objectHandler.convertUserEntityToUserForm(user);
				userFormList.add(userForm);
			}
		}
		return userFormList;
	}

	@Override
	public List<RoleForm> getAllRoles() throws Exception {
		List<RoleForm> roleFormList = new ArrayList<RoleForm>();
		List<Role> roleList = userManagementDao.getAllRoles();
		if (null != roleList && !roleList.isEmpty()) {
			for (Role role : roleList) {
				RoleForm roleForm = objectHandler.convertRoleEntityToForm(role);
				roleFormList.add(roleForm);
			}
		}
		return roleFormList;
	}

	@Override
	public Role getRoleByName(String roleName) throws Exception {
		return userManagementDao.getRoleByName(roleName);
	}

	@Override
	public void deleteUser(long userId, Boolean isActive) throws Exception {
		userManagementDao.deleteUser(userId, isActive);
	}

	@Override
	public void saveAddress(AddressForm addressForm) throws Exception {
		Address address = objectHandler.convertAddressFormToEntity(addressForm);
		userManagementDao.saveAddress(address);
	}

	@Override
	public List<AddressForm> getActiveAddressList(long userId) throws Exception {
		List<Address> addressList = userManagementDao.getActiveAddressList(userId);
		List<AddressForm> activeAddressList = new ArrayList<AddressForm>();
		if (null != addressList && !addressList.isEmpty()) {
			for (Address address : addressList) {
				AddressForm addressForm = objectHandler.convertAddressEntityToForm(address);
				activeAddressList.add(addressForm);
			}
		}
		return activeAddressList;
	}

	@Override
	public void deleteAddress(int addressId) throws Exception {
		userManagementDao.deleteAddress(addressId);
	}

	@Override
	public List<UserForm> getActiveDeliveryUsers() throws Exception {
		List<UserForm> userFormList = new ArrayList<UserForm>();
		List<User> userList = userManagementDao.getActiveDeliveryUsers();
		if (null != userList && !userList.isEmpty()) {
			for (User user : userList) {
				UserForm userForm = objectHandler.convertUserEntityToUserForm(user);
				userFormList.add(userForm);
			}
		}
		return userFormList;
	}
}