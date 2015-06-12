package com.ibs.hibernate.bean.ord;

import java.io.Serializable;


public class OrderProd implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 7910287412343224758L;

	/**
	 * 订单包含产品POJO类
	 */
	
	private Integer id; 
	
	private String orderNo;
	
	private Integer prodNo;
	
	private Integer count;
	
	private Double price;
	
	private String name;

	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getProdNo() {
		return prodNo;
	}
	public void setProdNo(Integer prodNo) {
		this.prodNo = prodNo;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
