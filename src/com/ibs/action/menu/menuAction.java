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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.Session;

import Hibernate.HibernateSessionFactory;
import base.BaseAction;

import com.ibs.hibernate.bean.product.ProductType;
import com.ibs.hibernate.bean.system.Menu;

public class menuAction extends BaseAction {
	
 
   private String role;
   
   private ArrayList<Menu> menuList;
   
   //产品列表List
   private ArrayList<ProductType> prodTypeList;
   
   //返回的Html信息
   private String html;
   
   
   public String loadMenu()
   {
	   //如果session中已存在菜单数据
	   if(session.get("menuList") != null)
	   {
		   this.menuList = (ArrayList<Menu>)session.get("menuList");
	   }
	   else
	   {
		   menuList = searchMenu(0,role);
	   }
	   
	   //将菜单保存到session中
	   session.put("menuList", menuList);
	   
	   return "view";
   }
   
   
   public String ProdTypeToMenu() throws IOException
   {
	   
	   //如果session中已存在菜单数据
	   if(session.get("ProdTypeList") != null)
	   {
		   this.prodTypeList = (ArrayList<ProductType>)session.get("ProdTypeList");
	   }
	   else
	   {
		   this.prodTypeList = searchProdType(0);
	   }
	   
	   //将菜单保存到session中
	   session.put("prodTypeList", prodTypeList);
	  
	   this.html = this.getHtml(this.prodTypeList);
	   this.printHtml(this.html);
	   return null;
   }
   
   public  String getHtml(List<ProductType> typeList)
   {
	   StringBuffer returnHtml = new StringBuffer();
	   for(int i = 0;i < typeList.size(); i++)
	   {
		   	if(0 == typeList.get(i).getLevel() && i == 0)
		   	{
		   		returnHtml.append("<div class=\"item bo\">");
		   		returnHtml.append("<h3><span>.</span><a onclick=\"getProductById("+typeList.get(i).getId()+")\">"+typeList.get(i).getName()+ "</a></h3>");
		   		returnHtml.append("<div class=\"item-list clearfix\">");
		   		returnHtml.append("<div class=\"close\">x</div>");
		   		returnHtml.append("<div class=\"subitem\">");
		   	    returnHtml.append(getHtml(typeList.get(i).getChild()));
		   	    returnHtml.append("</div>");
		   	    returnHtml.append("</div>");
		   	    returnHtml.append("</div>");
		   		
		   	}
		   	else if( 0 == typeList.get(i).getLevel() && i != 0)
		   	{
		   		returnHtml.append("<div class=\"item\">");
		   		returnHtml.append("<h3><span>.</span><a onclick=\"getProductById("+typeList.get(i).getId()+")\">"+typeList.get(i).getName()+ "</a></h3>");
		   		returnHtml.append("<div class=\"item-list clearfix\">");
		   		returnHtml.append("<div class=\"close\">x</div>");
		   		returnHtml.append("<div class=\"subitem\">");
		   		returnHtml.append(getHtml(typeList.get(i).getChild()));
		   		returnHtml.append("</div>");
		   	    returnHtml.append("</div>");
		   	    returnHtml.append("</div>");
		   	}
		   	else if(1 == typeList.get(i).getLevel())
		   	{
		   		returnHtml.append("<dl class=\"fore"+(i+1)+"\">");
		   		returnHtml.append("<dt><a onclick=\"getProductById("+typeList.get(i).getId()+")\">" +typeList.get(i).getName()+"</a></dt>");
		   		returnHtml.append("<dd>");
		   		returnHtml.append(getHtml(typeList.get(i).getChild()));
		   		returnHtml.append("</dd>");
		   		returnHtml.append("</dl>");
		   	}
		   	else if(2 == typeList.get(i).getLevel())
		   	{
		   		returnHtml.append("<em><a onclick=\"getProductById("+typeList.get(i).getId()+")\">" +typeList.get(i).getName()+"</a></em>");
		   	}
	   }
	   return returnHtml.toString();
   }
   public  ArrayList searchProdType(Integer parentid)
   {
	   Session  hibSession = HibernateSessionFactory.getSession();
	   try
	   {
		    String hql = " from ProductType where parentid = " +parentid;
		    ArrayList<ProductType> prodTypeList = (ArrayList<ProductType>)hibSession.createQuery(hql).list();
		    if(prodTypeList != null && prodTypeList.size() > 0)
		    {
		    	for(int i = 0;i < prodTypeList.size();i++)
		    	{
		    		Integer id = prodTypeList.get(i).getId();
		    		prodTypeList.get(i).setChild(searchProdType(id));
		    	}
		    }
		    return prodTypeList;
	   }
	   catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(hibSession.isOpen())
		   {
			   hibSession.close();
		   }
	   }
	   return null;
   }
   
   public  ArrayList searchMenu(Integer parent,String role)
   {
	   Session hibSession = HibernateSessionFactory.getSession();
	   
	   try
	   {
		   String sql =" from Menu where parent ="+parent+ " and role ='"+role+"'";
		   ArrayList<Menu> listMenu = (ArrayList<Menu>) hibSession.createQuery(sql).list();
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
		   if(hibSession.isOpen())
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

@JSON(name="prodTypeList")
public ArrayList<ProductType> getProdTypeList() {
	return prodTypeList;
}


public void setProdTypeList(ArrayList<ProductType> prodTypeList) {
	this.prodTypeList = prodTypeList;
}


public String getRole() {
	return role;
}


public void setRole(String role) {
	this.role = role;
}
	
}