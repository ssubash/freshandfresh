package com.freshandfresh.api.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freshandfresh.api.dao.OrderManagementDao;
import com.freshandfresh.api.entity.Action;
import com.freshandfresh.api.entity.OrderBook;
import com.freshandfresh.api.repository.ActionRepository;
import com.freshandfresh.api.repository.OrderBookRepository;

@Service
public class OrderManagementDaoImpl implements OrderManagementDao {

	@Autowired
	OrderBookRepository orderBookRepository;

	@Autowired
	ActionRepository actionRepository;

	@Override
	public void saveOrder(OrderBook orderBook) throws Exception {
		orderBookRepository.save(orderBook);
	}

	@Override
	public List<OrderBook> getAllOrder() throws Exception {
		return orderBookRepository.findAllByOrderByIdDesc();
	}

	@Override
	public List<Action> getActions(int roleId) throws Exception {
		return actionRepository.findAllByRoleId(roleId);
	}

	@Override
	public List<OrderBook> getCurrentOrderByDeliveryUserId(long deliveryUserId, String actionName) throws Exception {
		return orderBookRepository.findAllByDeliveryUserIdAndActionNameOrderByIdAsc(deliveryUserId, actionName);
	}

	@Override
	public List<OrderBook> getCurrentOrders(String[] actionNames) throws Exception {
		return orderBookRepository.findAllByActionNameNotInOrderByIdAsc(actionNames);
	}

	@Override
	public List<OrderBook> getCurrentOrderByUserId(long customerId, String[] actionNames) throws Exception {
		return orderBookRepository.findAllByOrderedUserIdAndActionNameNotInOrderByIdAsc(customerId, actionNames);
	}

	@Override
	public List<OrderBook> getOrderHistoryByDeliveryUserId(long deliveryUserId, String[] actionNames) throws Exception {
		return orderBookRepository.findAllByDeliveryUserIdAndActionNameInOrderByIdAsc(deliveryUserId, actionNames);
	}

	@Override
	public List<OrderBook> getOrderHistory(String[] actionNames) throws Exception {
		return orderBookRepository.findAllByActionNameInOrderByIdAsc(actionNames);
	}

	@Override
	public List<OrderBook> getOrderHistoryByUserId(long customerId, String[] actionNames) throws Exception {
		return orderBookRepository.findAllByOrderedUserIdAndActionNameInOrderByIdAsc(customerId, actionNames);
	}

	@Override
	public List<OrderBook> getSettlementPendingOrders(long customerId, String actionName) throws Exception {
		return orderBookRepository.findSettlementPendingOrders(customerId, actionName);
	}

	@Override
	public List<OrderBook> getAllSettlementPendingOrders(String actionName) throws Exception {
		return orderBookRepository.findAllSettlementPendingOrders(actionName);
	}

}
