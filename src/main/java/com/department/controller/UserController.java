package com.department.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.department.entity.RoleMaster;
import com.department.entity.UserMaster;
import com.department.exception.BusinessException;
import com.department.exception.ControllerException;
import com.department.service.UserServiceInterface;






@RestController
public class UserController {

	@Autowired
	private UserServiceInterface service;

	@PostMapping("/user")
	public ResponseEntity<?> creatingUser (@RequestBody UserMaster userMaster){
		try {
		UserMaster data=service.creatingUser(userMaster);
		return new ResponseEntity<UserMaster>(data, HttpStatus.CREATED);
		} catch (BusinessException e) {
			
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<String>(ce.getErrorMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("user table",
					"Something went wrong on Controller");
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}

	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUserbyid(@PathVariable("id") Long id){
		try {
			service.deleteUserbyid(id);
			return new ResponseEntity<String>("User Deleted Successfully ",HttpStatus.OK);
	}catch (BusinessException e) {
		ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
		return new ResponseEntity<String>(ce.getErrorMessage(), HttpStatus.BAD_REQUEST);
	} catch (Exception e) {
		ControllerException ce = new ControllerException("Failed to Delete Group","Something went wrong on Controller");
		return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
	}

	}
	@GetMapping("/getallUser")
	public ResponseEntity<?> getAllUserdata() {
		try {
		List<UserMaster> listOfUser = service.getAllUserdata();
		return new ResponseEntity<List<UserMaster>>(listOfUser, HttpStatus.ACCEPTED);
		}catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<String>(ce.getErrorMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/getallUserStatus/{status}")
	public ResponseEntity<List<UserMaster>> getAllUserdataStatus(@PathVariable("status") String status) {

		List<UserMaster> listOfUser = service.getAllUserdataStatus(status);
		return new ResponseEntity<List<UserMaster>>(listOfUser, HttpStatus.ACCEPTED);

	}
	
	@PutMapping("/update/{userName}")
	public ResponseEntity<?> updateUser( @RequestBody UserMaster userMaster,@PathVariable("userName") String userName) {
		try {
			UserMaster savedUser = service.updateUser(userName, userMaster);
			return new ResponseEntity<UserMaster>(savedUser, HttpStatus.CREATED);
		} catch (BusinessException e) {

			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<String>(ce.getErrorMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("user table",
					"Something went wrong on Controller");
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

}
