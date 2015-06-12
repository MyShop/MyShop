/*
 * $Id: Login.java 471756 2006-11-06 15:01:43Z husted $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.ibs.action.login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import Hibernate.HibernateSessionFactory;
import base.BaseAction;
import base.SystemVariable;
import base.ibsValidate;

import com.ibs.hibernate.bean.role.RoleType;
import com.ibs.hibernate.bean.system.Menu;
import com.ibs.hibernate.bean.user.User;
import com.ibs.hibernate.bean.user.UserRole;
import com.ibs.hibernate.bean.user.Userinfo;
import com.ibs.hibernate.dao.BaseDAO;
import com.opensymphony.xwork2.ActionContext;

public class loginAction<T> extends BaseAction{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4645416777642978771L;
	
	private String email;
	private String password;
	private String rePassword;
	private Userinfo userInfo;
	
    private BaseDAO<T> dao;
	
	
	/**
	 * 提交表单前验证
	 * @return
	 * @throws IOException
	 */
    
	public void  validateEnter() throws IOException
	{
		
			String hql = "" ;
			List<User> userList = null;
			//validate email
			if(ibsValidate.isEmpty(email))
			{
				this.addFieldError("email", errorMessage.login100);
			}
			else
			{
				hql =" from User u where u.email ='" + email +"'";
				userList = (List<User>) dao.list(hql);
				if(userList.size()==0)
				{
					this.addFieldError("email", errorMessage.login101);
				}
			}
			
			//validate password
			if(!ibsValidate.isEmpty(password) && userList!=null && userList.size() > 0)
			{
				if(ibsValidate.isEmpty(password))
				{
					this.addFieldError("password", errorMessage.login102);
				}
				else if(!password.equals(userList.get(0).getPassword()))
				{
					this.addFieldError("password", errorMessage.login103);
				}
			}
			
			//validate repPssword
			if(ibsValidate.isEmpty(rePassword))
			{
				this.addFieldError("rePassword", errorMessage.login104);
			}
			else if(!rePassword.equals(session.get("rePassword")))
			{
				this.addFieldError("rePassword", errorMessage.login105);
			}
			
			//字段校验通过再检验权限问题
			if(this.getFieldErrors().size() == 0)
			{
				userInfo = userList.get(0).getUserInfo();//将查询的用户信息保存
				//查询用户权限 
				hql =" from UserRole where userAccount='" + email +"'";
				List<UserRole> roles = (List<UserRole>) dao.list(hql);
				RoleType[] tempRoles =null;
				Iterator it =null;
				if(roles != null)
				{
					it =  roles.iterator();
				}
				
				//根据用户权限判断是否允许标志位
				boolean isAllow = false;
				
				while(it.hasNext())
				{
					Integer tempRole = ((UserRole)it.next()).getRoleId();
					System.out.print(SystemVariable.Role1.equals(tempRole));
					if(SystemVariable.Role1.equals(tempRole))
					{
						isAllow = true;
					    break ;
					}
					else if(SystemVariable.Role0.equals(tempRole))
					{
						isAllow = true;
						break ;
					}
				}
				
				if(isAllow == false)
				{
					this.addFieldError("email", errorMessage.login107);
				}

			}
}
	
	/**
	 * 提交表单，提交表单前需再次验证谨防通过修改JS修改表单数据
	 * @return
	 * @throws Exception
	 */
	public String Enter() throws Exception
	{
			session.put("userInfo",userInfo);
			userInfo = (Userinfo) session.get("userInfo");
			String hql =" from UserRole where userAccount='" + email +"'";
			List roles = dao.list(hql);
			RoleType[] tempRoles =null;
			Iterator it =null;
			if(roles != null)
			{
				it =  roles.iterator();
			}
			
			
			while(it.hasNext())
			{
				Integer tempRole = ((UserRole)it.next()).getRoleId();
				if(SystemVariable.Role1.equals(tempRole))
				{
					return "success";
				}
				else if(SystemVariable.Role0.equals(tempRole))
				{
					return "cosole";
				}
			}
			
			return "success";
		
	}
    
	public String index() throws Exception
	{
		return this.Enter();
	}
	
	public String execute()
	{
		return "input";
	}
	
	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Userinfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(Userinfo userInfo) {
		this.userInfo = userInfo;
	}
	

    public BaseDAO<T> getDao() {
		return dao;
	}

	public void setDao(BaseDAO<T> dao) {
		this.dao = dao;
	}

}