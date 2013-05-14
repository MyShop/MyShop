package com.ibs.action.product;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Hibernate.HibernateSessionFactory;

import com.ibs.hibernate.bean.page.PageBean;
import com.ibs.hibernate.bean.product.Product;
import com.opensymphony.xwork2.ActionSupport;

public class MangeProductAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4530397280482366857L;
	private List<Product> products;
	PageBean pb = new PageBean();
	public String intoProdPage()
	{
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateSessionFactory.getSessionFactory().openSession();
			tx = session.beginTransaction();
			String hql = "from Product";
			products = session.createQuery(hql).setFirstResult((pb.getPageNo() -1) * pb.getPageSize()).setMaxResults(pb.getPageSize()).list();
			pb.setList(products);
			
			//查询下总记录数
			hql = "select count(id) "+ hql;
			Integer count = (Integer)session.createQuery(hql).uniqueResult();
			pb.setRowCount(count);
			
		}
		catch(Exception ex)
		{
			tx.rollback();
			ex.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return "intoSearch";
	}
}
