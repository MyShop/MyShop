package com.ibs.hibernate.bean.user;

import java.util.Set;


public class User {

	private Integer id;
	
	private String email;
	
	private String password;
	
	private Userinfo userInfo;
	
	private Set<UserRole> roles;
	
	public User()
	{
		
	}
	
	public User(int id,String userName,String password)
	{
		
	}
	public Userinfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(Userinfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}
}
