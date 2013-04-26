package com.ibs.hibernate.bean.role;

public class RoleType {
	
	/**
	 * 角色id
	 */
	private Integer id;
	
	/**
	 * 角色name
	 */
	private String name;
	
	/**
	 * 角色备注
	 */
	private String comment;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
