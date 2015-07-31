package com.ibs.action.online;

import java.util.ArrayList;
import java.util.Hashtable;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import Hibernate.HibernateSessionFactory;

import com.ibs.hibernate.bean.product.ProductType;

/**
 * 进行Session全局托管，防止用户重复登录
 * create by @Robin 2015-07-11 
 *
 */
public class AuthsOfSession {
	
	private static Logger logger = Logger.getLogger(AuthsOfSession.class.getName());
	
	// 单例模式实例
	private static AuthsOfSession authSessions = null;
	
	//托管session集合对象
	private Hashtable<Integer,HttpSession> sessionTable = null;
	
	// 在线访问人数
	private static  Integer activeOnline = null;
	
	//历史访问人数
	private static Integer historyOnline = null;
	
	//用于保存网站的菜单选项
	private static ArrayList<ProductType> productTypes = null;
	
	
	private static synchronized void syncInit() {
	      if (authSessions == null) {
	    	  authSessions = new AuthsOfSession();
	      }
	    }
	
    public static ArrayList<ProductType> getProductTypes() {
		return productTypes;
	}

	public static void setProductTypes(ArrayList<ProductType> productTypes) {
		AuthsOfSession.productTypes = productTypes;
	}

	public static void setProductTypes()
	{
		AuthsOfSession.productTypes = searchProdType(0);
	}
	public static Integer getActiveOnline() {
		return activeOnline;
	}
    
	public static ArrayList searchProdType(Integer parentid) {
		/**Session会自动关闭*/
		Session hibSession = HibernateSessionFactory.getSession();
		if(hibSession == null)
		{
			logger.error("HibSession is NULL!!!");
		}
		try {
			String hql = " from ProductType where parentid = " + parentid;
			ArrayList<ProductType> prodTypeList = (ArrayList<ProductType>) hibSession.createQuery(hql).list();
			if (prodTypeList != null && prodTypeList.size() > 0) {
				for (int i = 0; i < prodTypeList.size(); i++) {
					Integer id = prodTypeList.get(i).getId();
					prodTypeList.get(i).setChild(searchProdType(id));
				}
			}
			return prodTypeList;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Hibernate查询错误！");
		} 
		finally {
			if (hibSession.isOpen()) {
				hibSession.close();
			}
		}
		return null;
	}


    public static synchronized void minusActiveOnline() 
    {
    	activeOnline = activeOnline - 1;
    }
    
    public static synchronized void addActiveOnlien()
    {
    	activeOnline = activeOnline + 1;
    	
    }
    
    public  static synchronized  void  addHistoryOnline()
    {
    	historyOnline = historyOnline + 1;
    }
    
    public static synchronized void setActiveOnline(Integer number)
    {
    	activeOnline = number;
    }
    
    public static synchronized void setHistoryOnline(Integer number)
    {
    	historyOnline = number;
    }
	public static Integer getHistoryOnline() {
		return historyOnline;
	}

	private AuthsOfSession()
    {
    	sessionTable = new Hashtable();
    }
    
    public static AuthsOfSession getInstance()
    {
    	if(authSessions == null)
    	{
    		 syncInit();
    	}
    	return authSessions;
    }
	
   
    
    public Hashtable getSessionTable()
    {
    	return sessionTable;
    }
}
