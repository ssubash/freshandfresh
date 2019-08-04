package com.freshandfresh.api.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.freshandfresh.api.entity.Address;

@Repository
@Transactional
public interface AddressRepository extends JpaRepository<Address, Integer> {

	List<Address> findAllByIsActiveAndUserId(boolean isActive, long userId);

	@Modifying
	@Query("UPDATE Address SET isActive = :isActive WHERE id = :addressId")
	void updateAddressInActive(@Param("isActive") boolean isActive, @Param("addressId") int addressId);

}
