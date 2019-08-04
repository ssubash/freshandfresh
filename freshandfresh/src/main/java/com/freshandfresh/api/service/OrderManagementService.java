package com.freshandfresh.api.service;

import java.util.List;

import com.freshandfresh.api.model.ActionForm;
import com.freshandfresh.api.model.OrderBookForm;
import com.freshandfresh.api.model.SettlementStatus;

public interface OrderManagementService {

	void saveOrder(OrderBookForm orderBookForm) throws Exception;

	List<OrderBookForm> getAllOrder() throws Exception;

	List<ActionForm> getActions(int roleId) throws Exception;

	List<OrderBookForm> getCurrentOrderByDeliveryUserId(long deliveryUserId, String actionName) throws Exception;

	List<OrderBookForm> getCurrentOrders(String[] strings) throws Exception;

	List<OrderBookForm> getCurrentOrderByUserId(long customerId, String[] actionNames) throws Exception;

	List<OrderBookForm> getOrderHistoryByDeliveryUserId(long deliveryUserId, String[] actionNames) throws Exception;

	List<OrderBookForm> getOrderHistory(String[] actionNames) throws Exception;

	List<OrderBookForm> getOrderHistoryByUserId(long customerId, String[] actionNames) throws Exception;

	SettlementStatus getSettlementPendingOrders(long findLoginUserId, String actionName) throws Exception;

	List<SettlementStatus> getAllSettlementPendingOrders(String actionName) throws Exception;

}
