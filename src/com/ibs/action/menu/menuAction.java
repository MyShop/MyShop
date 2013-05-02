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
package com.ibs.action.menu;

import java.util.ArrayList;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.Session;

import Hibernate.HibernateSessionFactory;
import base.BaseAction;
import base.SystemVariable;

import com.ibs.hibernate.bean.system.Menu;
import com.opensymphony.xwork2.ActionSupport;

public class menuAction extends ActionSupport{
	
 
   private String role;
   private ArrayList<Menu> menuList;
   public String load()
   {
	   menuList = searchMenu(0,role);
	   return "view";
   }
   
   
   public static ArrayList searchMenu(Integer parent,String role)
   {
	   Session hibSession = HibernateSessionFactory.getSessionFactory().openSession();
	   try
	   {
		   String sql =" from Menu where parent =:parent and role =:role";
		   ArrayList<Menu> listMenu = (ArrayList<Menu>) hibSession.createQuery(sql).setParameter("parent", parent).setParameter("role", role).list();
		   if(listMenu !=null && listMenu.size() > 0)
		   {
			   for(int i = 0; i < listMenu.size();i++)
			   {
				   Integer id = listMenu.get(i).getId();
				   listMenu.get(i).setChild(searchMenu(id,role));
			   }
		   }
		   
		   return listMenu;
	   }
	   catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   hibSession.close();
	   }
	   return null;
   }
   
@JSON(name="menuList")
public ArrayList<Menu> getMenuList() {
	return menuList;
}
public void setMenuList(ArrayList<Menu> menuList) {
	this.menuList = menuList;
}


public String getRole() {
	return role;
}


public void setRole(String role) {
	this.role = role;
}
	
}