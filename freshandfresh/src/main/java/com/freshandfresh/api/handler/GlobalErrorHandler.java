package com.freshandfresh.api.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.freshandfresh.api.controller.BaseController;
import com.freshandfresh.api.model.ResponseMessage;

@RestControllerAdvice
public class GlobalErrorHandler extends BaseController {

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<?> handleException(Exception e) {
		ResponseMessage response = new ResponseMessage();
		response.setMessage(e.getMessage());
		response.setStatus(false);
		e.printStackTrace();
		return sendResponseMessage(response);
	}

	@ExceptionHandler({ UsernameNotFoundException.class })
	public ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException e) {
		ResponseMessage response = new ResponseMessage();
		response.setMessage(e.getMessage());
		response.setStatus(false);
		e.printStackTrace();
		return sendResponseMessage(response);
	}

}
