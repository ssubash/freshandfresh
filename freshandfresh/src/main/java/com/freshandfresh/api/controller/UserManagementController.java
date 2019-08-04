package com.freshandfresh.api.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.freshandfresh.api.entity.Role;
import com.freshandfresh.api.entity.User;
import com.freshandfresh.api.enums.RoleName;
import com.freshandfresh.api.model.AddressForm;
import com.freshandfresh.api.model.LoginForm;
import com.freshandfresh.api.model.ResponseMessage;
import com.freshandfresh.api.model.SignupForm;
import com.freshandfresh.api.model.UserForm;
import com.freshandfresh.api.model.UserPrinciple;
import com.freshandfresh.api.util.ApiRegistry;
import com.freshandfresh.api.util.Constant;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(ApiRegistry.UM_USER)
public class UserManagementController extends BaseController {

	@PostMapping(ApiRegistry.UM_SIGN_IN)
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) throws Exception {
		ResponseMessage response = new ResponseMessage();
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateJwtToken(authentication);
		response.setData(
				objectHandler.convertUserPrincipleToUserForm((UserPrinciple) authentication.getPrincipal(), jwt));
		response.setStatus(true);
		return sendResponseMessage(response);
	}

	@PostMapping(ApiRegistry.UM_SIGN_UP)
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupForm signupForm) throws Exception {
		ResponseMessage response = new ResponseMessage();
		User user = objectHandler.convertSignupFormToUserEntity(signupForm);
		user.setPassword(encoder.encode(signupForm.getPassword()));
		Role role = userManagementService.getRoleByName(RoleName.ROLE_USER.name());
		user.setRole(role);
		userManagementService.saveUser(user);
		response.setMessage(messageSource.getMessage(Constant.USER_REGISTER_SUCCESS, null, Locale.ENGLISH));
		response.setStatus(true);
		return sendResponseMessage(response);
	}

	@PostMapping(ApiRegistry.UM_REGISTER_USER)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserForm userForm) throws Exception {
		ResponseMessage response = new ResponseMessage();
		User user = objectHandler.convertUserFormToUserEntity(userForm);
		user.setPassword(encoder.encode(userForm.getPassword()));
		if (null == user.getRole()) {
			Role role = userManagementService.getRoleByName(RoleName.ROLE_USER.name());
			user.setRole(role);
		}
		userManagementService.saveUser(user);
		response.setMessage(messageSource.getMessage(Constant.USER_SAVE_SUCCESS, null, Locale.ENGLISH));
		response.setStatus(true);
		return sendResponseMessage(response);
	}

	@GetMapping(ApiRegistry.UM_READ_ALL)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllUsers() throws Exception {
		ResponseMessage response = new ResponseMessage();
		response.setData(userManagementService.getAllUsers());
		response.setStatus(true);
		return sendResponseMessage(response);
	}

	@PostMapping(ApiRegistry.UM_UPDATE)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateUser(@RequestBody UserForm userForm) throws Exception {
		ResponseMessage response = new ResponseMessage();
		userManagementService.deleteUser(userForm.getId(), userForm.isActive());
		if (userForm.isActive()) {
			response.setMessage(messageSource.getMessage(Constant.USER_ACTIVE_SUCCESS, null, Locale.ENGLISH));
		} else {
			response.setMessage(messageSource.getMessage(Constant.USER_DEACTIVE_SUCCESS, null, Locale.ENGLISH));
		}
		response.setStatus(true);
		return sendResponseMessage(response);
	}

	@PostMapping(ApiRegistry.UM_ADDRESS_SAVE)
	public ResponseEntity<?> saveAddress(@RequestBody AddressForm addressForm) throws Exception {
		ResponseMessage response = new ResponseMessage();
		userManagementService.saveAddress(addressForm);
		response.setMessage(messageSource.getMessage(Constant.USER_ADDRESS_SAVE_SUCCESS, null, Locale.ENGLISH));
		response.setStatus(true);
		return sendResponseMessage(response);
	}

	@GetMapping(ApiRegistry.UM_ADDRESS_READ)
	public ResponseEntity<?> getActiveAddressList(@RequestParam("userId") long userId) throws Exception {
		ResponseMessage response = new ResponseMessage();
		response.setData(userManagementService.getActiveAddressList(userId));
		response.setStatus(true);
		return sendResponseMessage(response);
	}

	@PostMapping(ApiRegistry.UM_ADDRESS_DELETE)
	public ResponseEntity<?> deleteAddress(@RequestBody String addressId) throws Exception {
		ResponseMessage response = new ResponseMessage();
		userManagementService.deleteAddress(Integer.parseInt(addressId));
		response.setData(messageSource.getMessage(Constant.USER_ADDRESS_DELETE_SUCCESS, null, Locale.ENGLISH));
		response.setStatus(true);
		return sendResponseMessage(response);
	}

	@GetMapping(ApiRegistry.UM_LOAD_DELIVERY_USER)
	public ResponseEntity<?> getDeliveryUsers() throws Exception {
		ResponseMessage response = new ResponseMessage();
		response.setData(userManagementService.getActiveDeliveryUsers());
		response.setStatus(true);
		return sendResponseMessage(response);
	}

	@GetMapping(ApiRegistry.UM_READ_ROLE)
	public ResponseEntity<?> getAllRoles() throws Exception {
		ResponseMessage response = new ResponseMessage();
		response.setData(userManagementService.getAllRoles());
		response.setStatus(true);
		return sendResponseMessage(response);
	}
}