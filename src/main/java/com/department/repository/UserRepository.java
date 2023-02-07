package com.department.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.department.entity.RoleMaster;
import com.department.entity.UserMaster;

@Repository
public interface UserRepository extends JpaRepository<UserMaster, Long> {
	UserMaster  findByUserName(String userName);

	UserMaster findByEmail(String email);
	List<UserMaster> findAllByEmail(String email);
	UserMaster  findByStatus(String status);
	
}
