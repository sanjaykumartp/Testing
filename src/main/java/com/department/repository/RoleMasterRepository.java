package com.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.department.entity.RoleMaster;
@Repository
public interface RoleMasterRepository extends JpaRepository<RoleMaster, Long>{

}
