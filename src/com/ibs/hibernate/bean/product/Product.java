package com.ibs.hibernate.bean.product;

import java.io.Serializable;


public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2850797091371732481L;

	private Integer id;
	
	private String name;
	
	private Double price;
	
	private Integer type;
	
	private String picture;
	
	private String miaoshu;
	
	//第一分类类别属性
	private Integer type1;
	
	//第一分类类别属性
	private Integer type2;
	
	//第一分类类别属性
	private Integer type3;
	
	//产品缩略图
	private String firstPicture;
	
	public String getFirstPicture() {
		return firstPicture;
	}

	public void setFirstPicture(String firstPicture) {
		this.firstPicture = firstPicture;
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


	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getMiaoshu() {
		return miaoshu;
	}

	public void setMiaoshu(String miaoshu) {
		this.miaoshu = miaoshu;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getType1() {
		return type1;
	}

	public void setType1(Integer type1) {
		this.type1 = type1;
	}

	public Integer getType2() {
		return type2;
	}

	public void setType2(Integer type2) {
		this.type2 = type2;
	}

	public Integer getType3() {
		return type3;
	}

	public void setType3(Integer type3) {
		this.type3 = type3;
	}
}
