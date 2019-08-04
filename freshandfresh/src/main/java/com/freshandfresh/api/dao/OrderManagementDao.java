package com.freshandfresh.api.dao;

import java.util.List;

import com.freshandfresh.api.entity.Action;
import com.freshandfresh.api.entity.OrderBook;

public interface OrderManagementDao {

	void saveOrder(OrderBook orderBook) throws Exception;

	List<OrderBook> getAllOrder() throws Exception;

	List<Action> getActions(int roleId) throws Exception;

	List<OrderBook> getCurrentOrderByDeliveryUserId(long deliveryUserId, String actionName) throws Exception;

	List<OrderBook> getCurrentOrders(String[] strings) throws Exception;

	List<OrderBook> getCurrentOrderByUserId(long customerId, String[] actionNames) throws Exception;

	List<OrderBook> getOrderHistoryByDeliveryUserId(long deliveryUserId, String[] actionNames) throws Exception;

	List<OrderBook> getOrderHistory(String[] actionNames) throws Exception;

	List<OrderBook> getOrderHistoryByUserId(long customerId, String[] actionNames) throws Exception;

	List<OrderBook> getSettlementPendingOrders(long customerId, String actionName) throws Exception;
	
	List<OrderBook> getAllSettlementPendingOrders(String actionName) throws Exception;

}
