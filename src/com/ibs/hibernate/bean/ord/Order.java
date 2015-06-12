package com.ibs.hibernate.bean.ord;

import java.sql.Date;
import java.sql.Time;
import java.util.List;


public class Order{


	/**
	 *  查询订单pojo映射
	 */
	
	private String  orderNo;//流水号，java代码生成
	
	
	private Integer userNo;
	
	
	private Date orderDate;
	
	private Time orderTime;
	
	private String phone;
	
	private List<OrderProd>  ProdList;
	
	private String name;

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
	public Time getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Time orderTime) {
		this.orderTime = orderTime;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Integer getUserNo() {
		return userNo;
	}
	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}
	public List<OrderProd> getProdList() {
		return ProdList;
	}
	public void setProdList(List<OrderProd> prodList) {
		ProdList = prodList;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}
