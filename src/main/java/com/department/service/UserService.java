package com.department.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.department.entity.RoleMaster;
import com.department.entity.UserMaster;
import com.department.exception.BusinessException;
import com.department.exception.NotFoundException;
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

			throw new BusinessException("Email ID ", "Entered Email Id already exsists in DataBase");
		}
		if (!(repositroy.findByUserName(datas.getUserName()) == null))
		{
			throw new BusinessException("User Name"," User Name is already present in database");
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
			throw new BusinessException("User Id",
					" User ID Not found in DataBase, Please enter valid ID");
		}
		UserMaster s1= repositroy.findById(id).get();
		repositroy.delete(s1);
	}

	@Override
	public List<UserMaster> getAllUserdata()  {
		List<UserMaster> userList = repositroy.findAll();
		if(CollectionUtils.isEmpty(userList)) {
			throw new BusinessException("Database", "Database no records found");
		}
		else
			return userList;
	}

	@Override
	public UserMaster updateUser(String userName, UserMaster userMaster) {
		UserMaster datas=userMaster;
		
		UserMaster mail = repositroy.findByEmail(userMaster.getEmail());
		Date date=new Date();
//		String mail1 = userMaster.getEmail();
//		String mail2 = mail.getEmail();
//		int count = 0;
//		if (mail1.equals(mail2)) {
//			count++;
//		}
//		if (count>1) {
//			throw new BusinessException("Duplicate mail id", "Email id already exsist");
//		}
		List< UserMaster> list = repositroy.findAllByEmail(userName);
		if (list.size()>1) {
			throw new BusinessException("Email id alreadt Exsist", "Duplicte email id");
		}
		
		UserMaster modifySaved = repositroy.findByUserName(userName);
		RoleMaster role=	rmRepo.findById(userMaster.getRoleMaster().get(0).getId()).get();

		modifySaved.setEmail(userMaster.getEmail());
		userMaster.setCreatedDate(modifySaved.getCreatedDate());
		modifySaved.setFullName(userMaster.getFullName());
		modifySaved.setStatus(userMaster.getStatus());
		modifySaved.setUpdatedDate(date.toString());
		modifySaved.setRoleMaster(userMaster.getRoleMaster());

		return repositroy.save(modifySaved);

	}

	@Override
	public List<UserMaster> getAllUserdataStatus(String status) {

	List<UserMaster>	userMasterList=repositroy.findAll();
	List<UserMaster> ls=userMasterList.stream().filter(column->column.getStatus().equalsIgnoreCase(status)).collect(Collectors.toList());
	if(CollectionUtils.isEmpty(ls)) {
		throw new NotFoundException("Email ID ");
	}
	else
		return ls;
	}




}
