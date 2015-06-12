package com.ibs.hibernate.bean.merch;

import org.apache.derby.client.am.DateTime;

/**
 * 招商信息JavaBean
 * @author Robin
 * created by Robin 2015-04-21
 * 
 */
public class Merchant{

	private Integer id;
	
	private String name;
	
	private String phone;
	
	private String addr;
	
	private String project;
	
	private String  date;
	
	private Integer votes;
	
	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

}
