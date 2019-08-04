package com.freshandfresh.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freshandfresh.api.dao.OrderManagementDao;
import com.freshandfresh.api.entity.Action;
import com.freshandfresh.api.entity.OrderBook;
import com.freshandfresh.api.handler.ObjectHandler;
import com.freshandfresh.api.model.ActionForm;
import com.freshandfresh.api.model.OrderBookForm;
import com.freshandfresh.api.model.SettlementStatus;
import com.freshandfresh.api.service.OrderManagementService;

@Service
public class OrderManagementServiceImpl implements OrderManagementService {

	@Autowired
	OrderManagementDao orderManagementDao;

	@Autowired
	ObjectHandler objectHandler;

	@Override
	public void saveOrder(OrderBookForm orderBookForm) throws Exception {
		orderManagementDao.saveOrder(objectHandler.convertOrderBookFormToEntity(orderBookForm));
	}

	@Override
	public List<OrderBookForm> getAllOrder() throws Exception {
		List<OrderBook> orderDetails = orderManagementDao.getAllOrder();
		List<OrderBookForm> orders = new ArrayList<OrderBookForm>();
		if (null != orderDetails && !orderDetails.isEmpty()) {
			for (OrderBook orderBook : orderDetails) {
				orders.add(objectHandler.convertOrderBookEntityToForm(orderBook));
			}
		}
		return orders;
	}

	@Override
	public List<ActionForm> getActions(int roleId) throws Exception {
		List<Action> actionList = orderManagementDao.getActions(roleId);
		List<ActionForm> actionFormList = new ArrayList<ActionForm>();
		if (null != actionList && !actionList.isEmpty()) {
			for (Action action : actionList) {
				ActionForm actionForm = objectHandler.convertActionEntityToForm(action);
				actionFormList.add(actionForm);
			}
		}
		return actionFormList;
	}

	@Override
	public List<OrderBookForm> getCurrentOrderByDeliveryUserId(long deliveryUserId, String actionName)
			throws Exception {
		List<OrderBook> orderBookList = orderManagementDao.getCurrentOrderByDeliveryUserId(deliveryUserId, actionName);
		return objectHandler.convertOrderBookEntityListToFormList(orderBookList);
	}

	@Override
	public List<OrderBookForm> getCurrentOrders(String[] actionNames) throws Exception {
		List<OrderBook> orderBookList = orderManagementDao.getCurrentOrders(actionNames);
		return objectHandler.convertOrderBookEntityListToFormList(orderBookList);
	}

	@Override
	public List<OrderBookForm> getCurrentOrderByUserId(long customerId, String[] actionNames) throws Exception {
		List<OrderBook> orderBookList = orderManagementDao.getCurrentOrderByUserId(customerId, actionNames);
		return objectHandler.convertOrderBookEntityListToFormList(orderBookList);
	}

	@Override
	public List<OrderBookForm> getOrderHistoryByDeliveryUserId(long deliveryUserId, String[] actionNames)
			throws Exception {
		List<OrderBook> orderBookList = orderManagementDao.getOrderHistoryByDeliveryUserId(deliveryUserId, actionNames);
		return objectHandler.convertOrderBookEntityListToFormList(orderBookList);
	}

	@Override
	public List<OrderBookForm> getOrderHistory(String[] actionNames) throws Exception {
		List<OrderBook> orderBookList = orderManagementDao.getOrderHistory(actionNames);
		return objectHandler.convertOrderBookEntityListToFormList(orderBookList);
	}

	@Override
	public List<OrderBookForm> getOrderHistoryByUserId(long customerId, String[] actionNames) throws Exception {
		List<OrderBook> orderBookList = orderManagementDao.getOrderHistoryByUserId(customerId, actionNames);
		return objectHandler.convertOrderBookEntityListToFormList(orderBookList);
	}

	@Override
	public SettlementStatus getSettlementPendingOrders(long customerId, String actionName) throws Exception {
		List<OrderBook> orderBookList = orderManagementDao.getSettlementPendingOrders(customerId, actionName);
		return objectHandler.getCustomerSettlementPendingReport(orderBookList);
	}

	@Override
	public List<SettlementStatus> getAllSettlementPendingOrders(String actionName) throws Exception {
		List<OrderBook> orderBookList = orderManagementDao.getAllSettlementPendingOrders(actionName);
		return objectHandler.getAllSettlementPendingReport(orderBookList);
	}

}
