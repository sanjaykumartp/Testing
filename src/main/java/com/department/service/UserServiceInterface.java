package com.department.service;

import java.util.List;

import com.department.entity.RoleMaster;
import com.department.entity.UserMaster;

public interface UserServiceInterface {

	UserMaster creatingUser(UserMaster userMaster);

	void deleteUserbyid(Long id);

	List<UserMaster> getAllUserdata();

	UserMaster updateUser(String userName, UserMaster userMaster);

	List<UserMaster> getAllUserdataStatus(String status);

}
