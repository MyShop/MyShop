package com.ibs.hibernate.bean.ord;


public class OrderReceive{


	/**
	 * 订单收货信息POJO类
	 */
	
	private String orderNo;
	
	private Integer province;
	
	private Integer city;
	
	private Integer xian;
	
	private Integer zhen;
	
	private String phone;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	private String area;
	
	private Integer payType;
	

	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
}
