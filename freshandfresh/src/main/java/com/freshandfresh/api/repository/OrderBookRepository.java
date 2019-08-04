package com.freshandfresh.api.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.freshandfresh.api.entity.OrderBook;

@Repository
@Transactional
public interface OrderBookRepository extends JpaRepository<OrderBook, Long> {

	List<OrderBook> findAllByOrderByIdDesc();

	List<OrderBook> findAllByDeliveryUserIdAndActionNameOrderByIdAsc(long deliveryUserId, String actionName);

	List<OrderBook> findAllByActionNameNotInOrderByIdAsc(String[] actionNames);

	List<OrderBook> findAllByOrderedUserIdAndActionNameNotInOrderByIdAsc(long customerId, String[] actionNames);

	List<OrderBook> findAllByDeliveryUserIdAndActionNameInOrderByIdAsc(long deliveryUserId, String[] actionNames);

	List<OrderBook> findAllByActionNameInOrderByIdAsc(String[] actionNames);

	List<OrderBook> findAllByOrderedUserIdAndActionNameInOrderByIdAsc(long customerId, String[] actionName);

	@Query("SELECT ob FROM OrderBook ob WHERE ob.orderedUser.id = :customerId AND ob.action.name =:actionName AND ob.noOfContainerDispatched != ob.noOfContainerReceived AND ob.price != ob.amountReceived ORDER BY id")
	List<OrderBook> findSettlementPendingOrders(@Param("customerId") long customerId,
			@Param("actionName") String actionName);

	@Query("SELECT ob FROM OrderBook ob WHERE ob.action.name =:actionName AND ob.noOfContainerDispatched != ob.noOfContainerReceived AND ob.price != ob.amountReceived ORDER BY ob.orderedUser.id, ob.id")
	List<OrderBook> findAllSettlementPendingOrders(@Param("actionName") String actionName);

}
