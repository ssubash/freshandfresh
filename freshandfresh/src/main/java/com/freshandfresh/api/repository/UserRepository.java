package com.freshandfresh.api.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.freshandfresh.api.entity.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsernameAndIsActive(String username, boolean isActive);

	List<User> findAllByOrderByFullNameAsc();

	User findByMobileNumberOrUsername(String mobileNumber, String username);

	@Modifying
	@Query("UPDATE User SET isActive = :isActive WHERE id = :userId")
	void updateUserInActive(@Param("userId") long userId, @Param("isActive") Boolean isActive);

	List<User> findAllByIsActiveAndRoleName(boolean isActive, String roleName);

}