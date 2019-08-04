package com.freshandfresh.api.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freshandfresh.api.entity.OrderDetail;

@Repository
@Transactional
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
