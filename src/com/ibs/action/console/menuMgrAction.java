package com.ibs.action.console;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Hibernate.HibernateSessionFactory;

import com.ibs.action.menu.menuAction;
import com.ibs.hibernate.bean.role.RoleType;
import com.ibs.hibernate.bean.system.Menu;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class menuMgrAction<T> extends ActionSupport {

	private static final long serialVersionUID = -7984097648694196439L;
	
	//选中的角色
	private String role;
		
	//角色列表
	private List<RoleType> roleList;
	
	//菜单列表
	private List<Menu> menuList;
	
	ActionContext context = ActionContext.getContext();  
    HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);  
    HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);  
    Map session = context.getSession();  
	
	public String intoRole()
	{
		Session session  = null;
		Transaction tx = null;
		try
		{
			session = HibernateSessionFactory.getSessionFactory().openSession();
			tx = session.beginTransaction();
			roleList = session.createQuery("from RoleType").list();
			tx.commit();
			request.setAttribute("roleList", roleList);
			return "selectRole";
			
		}catch(Exception ex)
		{
			tx.rollback();
		}
		finally
		{
			session.close();
		}
		return null;
	}
	
	public String viewMenu()
	{
		return null;
	}
	
	public String intoMenu()
	{
		Session session  = null;
		Transaction tx = null;
		try
		{
			session = HibernateSessionFactory.getSessionFactory().openSession();
			tx = session.beginTransaction();
			tx.begin();
			menuList = menuAction.searchMenu(0, role);
			tx.commit();
		}
		catch(Exception ex)
		{
			tx.rollback();
		}
		finally
		{
			session.close();
		}
		request.setAttribute("menuList", menuList);
		return "viewMenu";
	}
	
	public String getMenuById()
	{
		String menuId = (String) request.getParameter("id");
		Menu menu = null;
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateSessionFactory.getSessionFactory().openSession();
			tx = session.beginTransaction();
			menu = (Menu) session.createQuery(" from Menu where id=:id").setParameter("id", new Integer(menuId)).uniqueResult();
			PrintWriter out = response.getWriter();
			JSONObject jsonObject = JSONObject.fromObject(menu);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-json");
			out.print(jsonObject.toString());
			tx.commit();
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
		return null;
	}
	
	public List<RoleType> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleType> roleList) {
		this.roleList = roleList;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
}
