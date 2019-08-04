package com.freshandfresh.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.freshandfresh.api.handler.ObjectHandler;
import com.freshandfresh.api.model.ResponseMessage;
import com.freshandfresh.api.security.jwt.JwtProvider;
import com.freshandfresh.api.service.OrderManagementService;
import com.freshandfresh.api.service.ProductManagementService;
import com.freshandfresh.api.service.UserManagementService;

@RestController
public class BaseController {

	@Autowired
	MessageSource messageSource;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserManagementService userManagementService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	ProductManagementService productManagementService;

	@Autowired
	OrderManagementService orderManagementService;
	
	@Autowired
	ObjectHandler objectHandler;

	protected ResponseEntity<?> sendResponseMessage(ResponseMessage responseMessage) {
		return ResponseEntity.ok().body(responseMessage);
	}
}
