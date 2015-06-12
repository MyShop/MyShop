package com.ibs.hibernate.bean.product;

import java.io.Serializable;
import java.util.ArrayList;


public class ProductType implements Serializable{

	/**
	 * 序列号 id
	 */
	private static final long serialVersionUID = 4514387411658433542L;

	/**
	 * 类型Id
	 */

	private Integer id;
	
	private String name;
	
	private Integer parentid;

	private Integer level;
	
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	private ArrayList<ProductType> child;
	
	public ArrayList<ProductType> getChild() {
		return child;
	}

	public void setChild(ArrayList<ProductType> child) {
		this.child = child;
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

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
}
