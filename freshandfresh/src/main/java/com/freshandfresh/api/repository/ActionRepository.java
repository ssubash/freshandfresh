package com.freshandfresh.api.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freshandfresh.api.entity.Action;

@Repository
@Transactional
public interface ActionRepository extends JpaRepository<Action, Integer> {

	List<Action> findAllByRoleId(int roleId);

}
