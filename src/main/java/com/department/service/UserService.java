package com.department.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.department.entity.RoleMaster;
import com.department.entity.UserMaster;
import com.department.exception.CustomException;
import com.department.repository.RoleMasterRepository;
import com.department.repository.UserRepository;

@Service
public class UserService implements UserServiceInterface {

	@Autowired
	private UserRepository repositroy;
	@Autowired
	RoleMasterRepository rmRepo;
	//	@Autowired
	//	ExUserMasterRepository exUserMasterRepository;
	//
	//	@Autowired
	//	ExRoleMasterRepository repoRole;

	@Override
	public UserMaster creatingUser(UserMaster userMaster) {
		Date date=new Date();
		userMaster.setCreatedDate(date.toString());
		UserMaster datas=userMaster;

		if (!(repositroy.findByEmail(datas.getEmail()) == null)) {

			throw new CustomException("Entered Email Id already exsists in DataBase");
		}
		if (!(repositroy.findByUserName(datas.getUserName()) == null))
		{
			throw new CustomException(" User Name is already present in database");
		}
		datas=repositroy.save(userMaster);

		return datas;
	}

	//	@Override
	//	public void deleteUserbyid(Long id) {
	//		if(!repositroy.existsById(id)) {
	//			throw new BusinessException("User Id",
	//					" User ID Not found in DataBase, Please enter valid ID");
	//		}
	//		UserMaster s1= repositroy.findById(id).get();
	//		RoleMaster r1=new RoleMaster();
	//		ExRoleMaster r2=new ExRoleMaster();
	//		r2.setId(r1.getId());
	//		r2.setRoleName(r1.getRoleName());
	//		ExUserMaster s2 = new ExUserMaster();
	//		s2.setCreatedDate(s1.getCreatedDate());
	//		s2.setEmail(s1.getEmail());
	//		s2.setFullName(s1.getFullName());
	//		s2.setId(s1.getId());
	//		s2.setRoleMaster(s1.getRoleMaster());
	//		s2.setStatus(s1.getStatus());
	//		s2.setUpdatedDate(s1.getUpdatedDate());
	//		s2.setUserName(s1.getUserName());
	//		repoRole.save(r2);
	//		exUserMasterRepository.save(s2);
	//		
	//				repositroy.delete(s1);
	//		
	//	
	//	}

	@Override
	public void deleteUserbyid(Long id) {
		if(!repositroy.existsById(id)) {
			throw new CustomException(" User ID Not found in DataBase, Please enter valid ID");
		}
		UserMaster s1= repositroy.findById(id).get();
		repositroy.delete(s1);
	}

	@Override
	public List<UserMaster> getAllUserdata()  {
		List<UserMaster> userList = repositroy.findAll();
		if(CollectionUtils.isEmpty(userList)) {
			throw new CustomException( "Database no records found");
		}
		else
			return userList;
	}

	@Override
	public UserMaster updateUser(String userName, UserMaster userMaster) {
		Date date=new Date();
		UserMaster modifySaved = repositroy.findByUserName(userName);
		UserMaster email = repositroy.findByEmail(userMaster.getEmail());
		List<UserMaster> listOfEmail = repositroy.findAllByEmail(modifySaved.getEmail());
		if (modifySaved.getEmail().equals(userMaster.getEmail())) {
			modifySaved.setEmail(userMaster.getEmail());
		}else if (email!= null) {
			throw new CustomException("Email id already Exsist in Database");
		}
		modifySaved.setEmail(userMaster.getEmail());
		modifySaved.setFullName(userMaster.getFullName());
		modifySaved.setStatus(userMaster.getStatus());
		modifySaved.setUpdatedDate(date.toString());
		modifySaved.setRoleMaster(userMaster.getRoleMaster());
		modifySaved = repositroy.save(modifySaved);
		
		return modifySaved;
	}

	@Override
	public List<UserMaster> getAllUserdataStatus(String status) {

		List<UserMaster>	userMasterList=repositroy.findAll();
		List<UserMaster> ls=userMasterList.stream().filter(column->column.getStatus().equalsIgnoreCase(status)).collect(Collectors.toList());
		if(CollectionUtils.isEmpty(ls)) {
			throw new CustomException("Email ID Not found");
		}
		else
			return ls;
	}




}
