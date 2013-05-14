package com.ibs.hibernate.bean.page;

import java.util.List;

/**
 * 分页信息Bean
 * @author Liuxr
 *
 */
public class PageBean 
{
	private List list;//当前页列表数据
	private int pageSize =5;//每页显示多少条数据
	private int rowCount;
	private int totalpages;
	private int pageNo = 1;
	private int nextPageNo;
	private int prePageNo;
	private int firstPageNo;
	private int lastPageNo;
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getTotalpages() {
		return totalpages;
	}
	public void setTotalpages(int totalpages) {
		this.totalpages = totalpages;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getNextPageNo() {
		return nextPageNo;
	}
	public void setNextPageNo(int nextPageNo) {
		this.nextPageNo = nextPageNo;
	}
	public int getPrePageNo() {
		return prePageNo;
	}
	public void setPrePageNo(int prePageNo) {
		this.prePageNo = prePageNo;
	}
	public int getFirstPageNo() {
		return firstPageNo;
	}
	public void setFirstPageNo(int firstPageNo) {
		this.firstPageNo = firstPageNo;
	}
	public int getLastPageNo() {
		return lastPageNo;
	}
	public void setLastPageNo(int lastPageNo) {
		this.lastPageNo = lastPageNo;
	}
}
