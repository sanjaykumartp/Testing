//package com.department.entity;
//
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
//import javax.validation.constraints.Email;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//@Data
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//public class ExUserMaster {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
//	
//	
//	private String userName;
////    @Emai
//	private String email;
//	
//	private String fullName;
//	
//	private String status;
//	
//	private String createdDate;
//	
//	private String updatedDate;
//	
//	@OneToMany(cascade=CascadeType.ALL)
//	@JoinColumn(name = "Exuser_masterId" ,referencedColumnName = "id")
//	private List<RoleMaster> roleMaster;
//
//}
