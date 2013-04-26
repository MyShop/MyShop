package com.ibs.hibernate.bean.system;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Menu implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4588127457130616038L;
	
	private Integer id;
	
	private String name;
	
	private  Integer parent;
	
	private String url;
	
	private String role;
	
	private ArrayList<Menu> child;
	
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

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ArrayList<Menu> getChild() {
		return child;
	}

	public void setChild(ArrayList<Menu> child) {
		this.child = child;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
