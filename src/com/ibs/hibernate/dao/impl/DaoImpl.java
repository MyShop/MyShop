package com.ibs.hibernate.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ibs.hibernate.dao.BaseDAO;

public class DaoImpl<T> extends HibernateDaoSupport implements BaseDAO<T> {
	
	private Session session;

	@SuppressWarnings("unchecked")
	public T find(Class<T> clazz, int id) {
		return (T) getHibernateTemplate().get(clazz, id);
	}

	public void create(T baseBean) {
		getHibernateTemplate().persist(baseBean);
	}

	public Query createQuery(String hql) throws HibernateException {
	   this.session = super.getSession();
	   Query query = this.session.createQuery(hql);
	   return query;
	   
	}

	public void delete(T baseBean) {
		getHibernateTemplate().delete(baseBean);
	}

	@SuppressWarnings("unchecked")
	public List<T> list(String hql) {
		return getHibernateTemplate().find(hql);
	}

	public int getTotalCount(String hql, Object... params) {
		Query query = createQuery(hql);
		for (int i = 0; params != null && i < params.length; i++)
			query.setParameter(i + 1, params[i]);
		Object obj = createQuery(hql).uniqueResult();
		super.releaseSession(this.session);
		return ((Long) obj).intValue();
	}

	@SuppressWarnings("unchecked")
	//该方法不是纯粹的Spring和Hibernate结合的方法，会导致查询数据不能实时更新
	public List<T> list(String hql, int firstResult, int maxResults,
			Object... params) {
		Query query = createQuery(hql);
		for (int i = 0; params != null && i < params.length; i++)
			query.setParameter(i + 1, params[i]);
		List<T> list = createQuery(hql).setFirstResult(firstResult)
				.setMaxResults(maxResults).list();
		super.releaseSession(this.session);
		return list;
	}


	public void save(T baseBean) {
		getHibernateTemplate().save(baseBean);
	}
	
	public void update(T baseBean)
	{
		getHibernateTemplate().update(baseBean);
	}
}
