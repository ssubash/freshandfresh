package com.freshandfresh.api.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.freshandfresh.api.entity.Item;

@Repository
@Transactional
public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	List<Item> findAllByOrderByNameAsc();

	@Modifying
	@Query("UPDATE Item SET isActive = :isActive WHERE id = :itemId")
	void updateItem(@Param("itemId") int itemId, @Param("isActive") boolean isActive);

	List<Item> findAllByIsActiveOrderByNameAsc(boolean isActive);

}
