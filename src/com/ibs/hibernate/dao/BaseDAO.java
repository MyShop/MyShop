package com.ibs.hibernate.dao;

import java.util.List;

import org.hibernate.Query;

import com.ibs.hibernate.bean.product.Product;

public interface BaseDAO<T>{

	public void create(T baseBean);

	public void save(T baseBean);
	
	public void update(T baseBean);

	public void delete(T baseBean);
	
	public List<T> list(String hql);

//	public List<T> list(Class<T> T,int firstResult, int maxSize,
//			Object... params);
	
	public int getTotalCount(String hql, Object... params);

	public List<T> list(String hql, int firstResult, int maxSize,
			Object... params);


	public T find(Class<T> clazz, int parseInt);
}
