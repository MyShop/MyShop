package com.ibs.action.product;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;

import Hibernate.HibernateSessionFactory;

import com.ibs.hibernate.bean.product.Product;
import com.ibs.hibernate.dao.BaseDAO;
import com.opensymphony.xwork2.ActionSupport;

public class ShowProductAction<T> extends ActionSupport implements ServletRequestAware{
	
	private static final long serialVersionUID = -4915833268799958654L;
	
	//商品的种类
	private String type;
	
	//商品列表
	private List<Product> productList;
	
	private BaseDAO<T> dao;
	
	private HttpServletRequest request;
	public BaseDAO<T> getDao() {
		return dao;
	}

	public void setDao(BaseDAO<T> dao) {
		this.dao = dao;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Product> getProduct() {
		return productList;
	}

	public void setProduct(List<Product> productList) {
		this.productList = productList;
	}

	
	/**
	 * 
	 */
	
	
	public String show()
	{
		String hql =" from Product where type="+Integer.parseInt(type);
		productList =(List<Product>) dao.list(hql);
		request.setAttribute("productList", productList);
		return "view";
	}


	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
