package com.cg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@Column(name = "user_name")
	@NotNull
	private String userName;
	
	@NotNull
	@Column(name = "password")
	private String password;
	
	@Positive
	@Column(name = "phone_number")
	private long phoneNumber;
	
	@NotNull
	@Column(name = "role")
	private String role;
	
	@Email
	@Column(name = "email_id")
	private String emailId;
	
	@Transient
	private boolean active;
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public void setRoles(boolean active) {
		this.active = active;
	}	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public User() {
		super();
	}
	
	public User(@NotNull String userName, @NotNull String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	public User(int userId, String userName, String password, long phoneNumber, String role, String emailId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.emailId = emailId;
	}
	

	public User(@NotNull String userName, String password, long phoneNumber, @NotNull String role,
			@Email String emailId) {
		super();
		this.userName = userName;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.emailId = emailId;
	}
		
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName  + ", phoneNumber="
				+ phoneNumber + ", role=" + role + ", emailId=" + emailId + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(!(obj instanceof User))
			return false;
		User usr = (User)obj;
		return this.userName == usr.getUserName() && this.password == usr.getPassword() &&	this.phoneNumber == usr.getPhoneNumber() 
				&& this.role == usr.getRole() && this.emailId == usr.getEmailId() && this.active == usr.isActive();
	
	}
	
	
}
