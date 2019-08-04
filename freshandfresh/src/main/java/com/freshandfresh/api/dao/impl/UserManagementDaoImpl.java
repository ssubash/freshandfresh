package com.freshandfresh.api.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freshandfresh.api.dao.UserManagementDao;
import com.freshandfresh.api.entity.Address;
import com.freshandfresh.api.entity.Role;
import com.freshandfresh.api.entity.User;
import com.freshandfresh.api.enums.RoleName;
import com.freshandfresh.api.repository.AddressRepository;
import com.freshandfresh.api.repository.RoleRepository;
import com.freshandfresh.api.repository.UserRepository;

@Service
public class UserManagementDaoImpl implements UserManagementDao {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	AddressRepository addressRepository;

	@Override
	public User saveUser(User user) throws Exception {
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() throws Exception {
		return userRepository.findAllByOrderByFullNameAsc();
	}

	@Override
	public User getUserByMobileNumberOrUserName(User user) throws Exception {
		return userRepository.findByMobileNumberOrUsername(user.getMobileNumber(), user.getUsername());
	}

	@Override
	public List<Role> getAllRoles() throws Exception {
		return roleRepository.findAll();
	}

	@Override
	public Role getRoleByName(String roleName) throws Exception {
		return roleRepository.findByName(roleName);
	}

	@Override
	public void deleteUser(long userId, Boolean isActive) throws Exception {
		userRepository.updateUserInActive(userId, isActive);
	}

	@Override
	public void saveAddress(Address address) throws Exception {
		addressRepository.save(address);
	}

	@Override
	public List<Address> getActiveAddressList(long userId) throws Exception {
		return addressRepository.findAllByIsActiveAndUserId(true, userId);
	}

	@Override
	public void deleteAddress(int addressId) throws Exception {
		addressRepository.updateAddressInActive(false, addressId);
	}

	@Override
	public List<User> getActiveDeliveryUsers() throws Exception {
		return userRepository.findAllByIsActiveAndRoleName(true, RoleName.ROLE_DELIVERY.name());
	}

}
