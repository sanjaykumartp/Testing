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
import org.springframework.web.bind.annotation.RestController;
import com.department.entity.UserMaster;
import com.department.exception.CustomException;
import com.department.service.UserServiceInterface;

@RestController
public class UserController {

	@Autowired
	private UserServiceInterface service;

	@PostMapping("/user")
	public ResponseEntity<?> creatingUser (@RequestBody UserMaster userMaster){
		try {
			UserMaster data = service.creatingUser(userMaster);
			return new ResponseEntity<UserMaster>(data, HttpStatus.CREATED);
		} catch (CustomException e) {
			CustomException ce = new CustomException(e.getMessage());
			return new ResponseEntity<String>(ce.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			CustomException ce = new CustomException("Something went wrong on Controller");
			return new ResponseEntity<String>(ce.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUserbyid(@PathVariable("id") Long id){
		try {
			service.deleteUserbyid(id);
			return new ResponseEntity<String>("User Deleted Successfully ",HttpStatus.OK);
		} catch (CustomException e) {
			CustomException ce = new CustomException(e.getMessage());
			return new ResponseEntity<String>(ce.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			CustomException ce = new CustomException("Something went wrong on Controller");
			return new ResponseEntity<String>(ce.getMessage(),HttpStatus.BAD_REQUEST);
		}

	}
	@GetMapping("/getallUser")
	public ResponseEntity<?> getAllUserdata() {
		try {
			List<UserMaster> listOfUser = service.getAllUserdata();
			return new ResponseEntity<List<UserMaster>>(listOfUser, HttpStatus.ACCEPTED);
		} catch (CustomException e) {
			CustomException ce = new CustomException(e.getMessage());
			return new ResponseEntity<String>(ce.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			CustomException ce = new CustomException("Something went wrong on Controller");
			return new ResponseEntity<String>(ce.getMessage(),HttpStatus.BAD_REQUEST);
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
		} catch (CustomException e) {
			CustomException ce = new CustomException(e.getMessage());
			return new ResponseEntity<String>(ce.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			CustomException ce = new CustomException("Something went wrong on Controller");
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

}
