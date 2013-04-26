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
package com.ibs.action.place;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;

import Hibernate.HibernateSessionFactory;

import base.BaseAction;

import com.ibs.hibernate.bean.place.City;
import com.ibs.hibernate.bean.place.Province;
import com.opensymphony.xwork2.ActionSupport;

public class cityAction  extends ActionSupport
{
	private Province pro;
	public Province getPro() {
		return pro;
	}
	public void setPro(Province pro) {
		this.pro = pro;
	}
	private City city;
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String addValidate() throws Exception
	{
		Session hibSession = HibernateSessionFactory.getSessionFactory().openSession();
		Transaction tran = hibSession.getTransaction();
		try
		{
			tran.begin();
			List pros=hibSession.createQuery("from Province pro where pro.name ='" + pro.getName() +"'").list();
			if(pro.getName() == null || pro.getName().trim().length() ==0 )
			{
//				rejectMap.put("pro.name",placeMessage.Mes100);
			}
			if(pros.size() !=0 )
			{
//				rejectMap.put("pro.name",placeMessage.Mes101);
			}
//			if(rejectMap.size() > 0)
//			{
//				this.returnRejMap();
//				
//			}
			return null;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
//			rejectMap.put("systemError", loginMessage.Mes106);
//			this.returnRejMap();
			return null;
		}
		finally
		{
			hibSession.close();
		}
	}
	
	public String add()
	{
		Session hibSession = HibernateSessionFactory.getSessionFactory().openSession();
		Transaction tran = hibSession.getTransaction();
		try
		{
			tran.begin();
			hibSession.persist(pro);
			tran.commit();
//			response.sendRedirect(request.getContextPath()+"/place.xhtml");
			return null;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			tran.rollback();
			return null;
		}
		finally
		{
			hibSession.close();
		}
		
	}
	
	public String del() throws Exception
	{
		Session hibSession = HibernateSessionFactory.getSessionFactory().openSession();
		Transaction tran = hibSession.getTransaction();
		try
		{
			tran.begin();
			hibSession.delete(pro);
			tran.commit();
//			this.getResponse().getWriter().print("[{\"message\":\"success\"}]");
			return null;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			tran.rollback();
//			this.getResponse().getWriter().print("[{\"message\":\"false\"}]");
			return null;
		}
		finally
		{
			hibSession.close();
		}
	}
}