package com.ibs.hibernate.bean.place;

import java.io.Serializable;


public class City implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -326334822516840406L;

	/**
	 * 
	 */
	private Integer  id;
	
	private Integer province;
	
	private String name;
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
	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
}
