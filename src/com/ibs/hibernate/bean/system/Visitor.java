package com.ibs.hibernate.bean.system;

import java.io.Serializable;

public class Visitor implements Serializable{
	
	/**
	 * create by @robin 2015-07-17 
	 *  记录系统在线访问人数和历史访问人数
	 */
	private static final long serialVersionUID = -5751913667742114085L;

	private Integer id;
	
	private Integer activeOnline;
	
	private Integer historyOnline;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getActiveOnline() {
		return activeOnline;
	}

	public void setActiveOnline(Integer activeOnline) {
		this.activeOnline = activeOnline;
	}

	public Integer getHistoryOnline() {
		return historyOnline;
	}

	public void setHistoryOnline(Integer historyOnline) {
		this.historyOnline = historyOnline;
	}
}
