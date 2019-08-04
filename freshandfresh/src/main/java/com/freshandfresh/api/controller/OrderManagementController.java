package com.freshandfresh.api.controller;

import java.util.Locale;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freshandfresh.api.enums.ActionName;
import com.freshandfresh.api.model.OrderBookForm;
import com.freshandfresh.api.model.ResponseMessage;
import com.freshandfresh.api.util.ApiRegistry;
import com.freshandfresh.api.util.Constant;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(ApiRegistry.OM_ORDER)
public class OrderManagementController extends BaseController {

	@PostMapping(ApiRegistry.OM_ORDER_SAVE)
	public ResponseEntity<?> putOrder(@RequestBody OrderBookForm orderBookForm) throws Exception {
		ResponseMessage response = new ResponseMessage();
		orderManagementService.saveOrder(orderBookForm);
		response.setMessage(messageSource.getMessage(Constant.ORDER_SAVE_SUCCESS, null, Locale.ENGLISH));
		response.setStatus(true);
		return sendResponseMessage(response);
	}

	@GetMapping(ApiRegistry.OM_ORDER_CURRENT_VIEW)
	public ResponseEntity<?> viewCurrentOrder() throws Exception {
		ResponseMessage response = new ResponseMessage();
		String[] actionNames = null;
		String roleName = objectHandler.findLoginUserRoleName();
		if (Constant.ROLE_DELIVERY.equals(roleName)) {
			response.setData(orderManagementService.getCurrentOrderByDeliveryUserId(objectHandler.findLoginUserId(),
					ActionName.ASSIGN.getName()));
		} else if (Constant.ROLE_ADMIN.equals(roleName)) {
			actionNames = new String[] { ActionName.CANCEL.getName(), ActionName.REJECT.getName(),
					ActionName.DISPATCH.getName(), ActionName.CLOSE.getName() };
			response.setData(orderManagementService.getCurrentOrders(actionNames));
		} else {
			actionNames = new String[] { ActionName.CANCEL.getName(), ActionName.REJECT.getName(),
					ActionName.DISPATCH.getName(), ActionName.CLOSE.getName() };
			response.setData(
					orderManagementService.getCurrentOrderByUserId(objectHandler.findLoginUserId(), actionNames));
		}
		response.setStatus(true);

		return sendResponseMessage(response);
	}

	@GetMapping(ApiRegistry.OM_ORDER_HISTORY_VIEW)
	public ResponseEntity<?> viewOrderHistory() throws Exception {
		ResponseMessage response = new ResponseMessage();
		String roleName = objectHandler.findLoginUserRoleName();
		String[] actionNames = null;
		if (Constant.ROLE_DELIVERY.equals(roleName)) {
			actionNames = new String[] { ActionName.DISPATCH.getName(), ActionName.CLOSE.getName() };
			response.setData(orderManagementService.getOrderHistoryByDeliveryUserId(objectHandler.findLoginUserId(),
					actionNames));
		} else if (Constant.ROLE_ADMIN.equals(roleName)) {
			actionNames = new String[] { ActionName.CANCEL.getName(), ActionName.REJECT.getName(),
					ActionName.DISPATCH.getName(), ActionName.CLOSE.getName() };
			response.setData(orderManagementService.getOrderHistory(actionNames));
		} else {
			actionNames = new String[] { ActionName.CANCEL.getName(), ActionName.REJECT.getName(),
					ActionName.DISPATCH.getName(), ActionName.CLOSE.getName() };
			response.setData(
					orderManagementService.getOrderHistoryByUserId(objectHandler.findLoginUserId(), actionNames));
		}
		response.setStatus(true);

		return sendResponseMessage(response);
	}

	@GetMapping(ApiRegistry.OM_ORDER_SETTLEMENT_PENDING_VIEW)
	public ResponseEntity<?> viewSettlementPendingOrders() throws Exception {
		ResponseMessage response = new ResponseMessage();
		String roleName = objectHandler.findLoginUserRoleName();
		if (Constant.ROLE_ADMIN.equals(roleName)) {
			response.setData(orderManagementService.getAllSettlementPendingOrders(ActionName.DISPATCH.getName()));
		} else {
			response.setData(orderManagementService.getSettlementPendingOrders(objectHandler.findLoginUserId(),
					ActionName.DISPATCH.getName()));
		}
		response.setStatus(true);
		return sendResponseMessage(response);
	}

	@GetMapping(ApiRegistry.OM_ORDER_VIEW_ALL)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> viewAllOrder() throws Exception {
		ResponseMessage response = new ResponseMessage();
		response.setData(orderManagementService.getAllOrder());
		response.setStatus(true);
		return sendResponseMessage(response);
	}

}
