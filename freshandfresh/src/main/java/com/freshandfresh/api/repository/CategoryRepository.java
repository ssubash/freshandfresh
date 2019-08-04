package com.freshandfresh.api.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.freshandfresh.api.entity.Category;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	List<Category> findAllByOrderByNameAsc();

	@Modifying
	@Query("UPDATE Category SET isActive = :isActive WHERE id = :itemId")
	void updateCategory(@Param("itemId") int itemId, @Param("isActive") boolean isActive);

	List<Category> findByIdOrderByNameAsc(int categoryId);

	List<Category> findAllByIsActiveAndItemsIsActiveOrderByNameAsc(boolean isCategoryActive, boolean isItemActive);

}
