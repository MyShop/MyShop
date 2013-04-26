package com.ibs.hibernate.bean.user;

import java.util.Set;

import com.ibs.hibernate.bean.role.RoleType;

public class Userinfo{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private Integer province;
	private Integer shi;
	private Integer xian;
	private Integer zhen;
	private Set<RoleType> roles;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
	public Integer getShi() {
		return shi;
	}
	public void setShi(Integer shi) {
		this.shi = shi;
	}
	public Integer getXian() {
		return xian;
	}
	public void setXian(Integer xian) {
		this.xian = xian;
	}
	public Integer getZhen() {
		return zhen;
	}
	public void setZhen(Integer zhen) {
		this.zhen = zhen;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Set<RoleType> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleType> roles) {
		this.roles = roles;
	}
}
