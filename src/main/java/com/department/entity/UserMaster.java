package com.department.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserMaster {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	private String userName;
    @Email
	private String email;
	
	private String fullName;
	
	private String status;
	
	private String createdDate;
	
	private String updatedDate;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "user_masterId" ,referencedColumnName = "id")
	private List<RoleMaster> roleMaster;

	
	
}
