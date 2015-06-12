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
package com.ibs.action.reg;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Hibernate.HibernateSessionFactory;
import base.BaseAction;
import base.ibsMessages;
import base.ibsValidate;

import com.ibs.hibernate.bean.user.User;
import com.ibs.hibernate.bean.user.UserRole;
import com.ibs.hibernate.dao.BaseDAO;

public class registerAction<T> extends BaseAction{



	/**
	 * 用户注册登记Action
	 */
	private static final long serialVersionUID = -6568676460041536019L;
	
	private String account;
	
	private String password;
	
	private String rePassword;

	private BaseDAO<T> dao;
	
	 
    public BaseDAO<T> getDao() {
		return dao;
	}

	public void setDao(BaseDAO<T> dao) {
		this.dao = dao;
	}
	
	public String execute()
	{
		return "input";
	}
	
	@SuppressWarnings("unchecked")
	public String Account()
	{
		User user = new User();
		user.setEmail(account);
		user.setPassword(password);
		UserRole userRole = new UserRole();
		userRole.setUserAccount(account);
		userRole.setRoleId(1);
		boolean result = false;
//		Session session = null;
//		Transaction tx = null;
		try
		{
//			session = HibernateSessionFactory.getSession();
//			tx = session.beginTransaction();
			dao.save((T)user);
			dao.save((T)userRole);
//			session.persist(user);
//			session.persist(userRole);
//			tx.commit();
			result = true;
		}
		catch(Exception ex)
		{
//			ex.printStackTrace();
//			tx.rollback();
			result = false;
		}
		request.setAttribute("result", result);
		return "result";
	}
	
	public void validateAccount()
	{
		//validate account
		if(ibsValidate.isEmpty(account))
		{
			this.addFieldError("account", ibsMessages.getString("register01"));
		}
		
		//检查是否是邮箱
		if(!ibsValidate.isMail(account))
		{
			this.addFieldError("account", ibsMessages.getString("register05"));
		}
		
		//validate password
		if(ibsValidate.isEmpty(password))
		{
			this.addFieldError("password", ibsMessages.getString("register02"));
		}
		
		//validate rePassword
		if(ibsValidate.isEmpty(rePassword))
		{
			this.addFieldError("rePassword", ibsMessages.getString("register03"));
		}
		
		//判断密码和确认密码是否一致
		if(!password.equals(rePassword))
		{
			this.addFieldError("rePassword", ibsMessages.getString("register04"));
		}
		
		String hql = " from User where email = '" + account +"'";
		List<User> userList = (List<User>) dao.list(hql);
		if(userList.size() > 0)
		{
			this.addFieldError("account", ibsMessages.getString("register06"));
		}
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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
}