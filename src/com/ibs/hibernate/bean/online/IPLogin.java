package com.ibs.hibernate.bean.online;

import java.io.Serializable;
import java.sql.Date;

public class IPLogin  implements Serializable{

	/**
	 *   Robin Create
	 *   
	 */
	private static final long serialVersionUID = 1842553499038212568L;

	private Integer id;
	
	private Integer userId;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	private boolean isLogined;
	
	private Integer loginCount;
	
	private Date lastLoginDate;
	
	private String lastLoginIP;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public boolean getisLogined() {
		return isLogined;
	}

	public void setisLogined(boolean isLogined) {
		this.isLogined = isLogined;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getLastLoginIP() {
		return lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}
	
	
	
	
	
}
