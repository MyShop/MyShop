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
package com.ibs.action.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import base.BaseAction;
import base.ibsMessages;
import base.ibsValidate;

import com.ibs.hibernate.bean.place.City;
import com.ibs.hibernate.bean.place.Province;
import com.ibs.hibernate.bean.place.Xian;
import com.ibs.hibernate.bean.place.Zhen;
import com.ibs.hibernate.bean.user.Userinfo;
import com.ibs.hibernate.dao.BaseDAO;
import com.opensymphony.xwork2.Preparable;

public class userInfoAction<T> extends BaseAction{



	/**
	 * 修改用户资料Action
	 */
	private static final long serialVersionUID = -6568676460041536019L;
	

	private BaseDAO<T> dao;
	
	private String name;
	
	private String phone;
	
	private String province;
	
	private String city;
	
	private String xian;
	
	private String zhen;
	
	private List<Province> proList;
	
	private List<City> cityList;

	private List<Xian> xianList;
	
	private List<Zhen> zhenList;
	
    public BaseDAO<T> getDao() {
		return dao;
	}

	public void setDao(BaseDAO<T> dao) {
		this.dao = dao;
	}
	
	public String execute()
	{
		Userinfo userInfo = (Userinfo) session.getAttribute("userInfo");
		
		//判断该用户是填写过个人信息
		if(userInfo != null)
		{
			this.name = userInfo.getName();
			
			if(userInfo.getPhone() != null)
			this.phone = userInfo.getPhone();
			
			if(userInfo.getProvince() != null)
			this.province = userInfo.getProvince().toString();
			
			if(userInfo.getShi() != null)
			this.city = userInfo.getShi().toString();
			
			if(userInfo.getZhen() != null)
			this.xian = userInfo.getXian().toString();
			
			this.zhen = userInfo.getEmail();
			String hql =" from Province";
			 this.proList = (ArrayList<Province>)dao.list(hql);
			
			hql = " from City where province=" + userInfo.getProvince();
			this.cityList = (ArrayList<City>)dao.list(hql);
			
			hql =" from Xian where city=" + userInfo.getShi();
			this.xianList = (ArrayList<Xian>)dao.list(hql);
			
			hql = " from Zhen where xian=" + userInfo.getXian();
			this.zhenList = (ArrayList<Zhen>)dao.list(hql);
			
			request.setAttribute("proList", proList);
			request.setAttribute("cityList", cityList);
			request.setAttribute("xianList", xianList);
			request.setAttribute("zhenList", zhenList);
			return "input";
		}
		else
			return "result";
	}
	
	public String GetCityList() throws Exception
	{
		if(this.province != null)
		{	
			String hql = " from City where province=" + Integer.parseInt(this.province);
			ArrayList<City> cityList = (ArrayList<City>) dao.list(hql);
			this.cityList = cityList;
			StringBuffer  json = new StringBuffer();
			
			
				Iterator<City> it = cityList.iterator();
				json.append("[");
				
				//根据查询结果设置标志位
				if(cityList.size() > 0)
				{
					json.append("{\"result\"" + ":"+ "true},");
				}
				else
				{
					json.append("{\"result\"" + ":"+"false}}");
				}
				while(it.hasNext())
				{
					City city = (City) it.next();
					json.append("{");
					json.append("\"id\"" +":" +"\"" + city.getId()+"\",");
					json.append("\"name\"" +":" +"\"" + city.getName() +"\"");
					json.append("},");
				}
				json.delete(json.length()-1, json.length());
				json.append("]");
				this.printJson(json.toString());
			
		}
		return null;
	}
	
	public String GetXianList() throws Exception
	{
		if(this.city != null)
		{	
			String hql = " from Xian where city=" + Integer.parseInt(this.city);
			ArrayList<Xian> xianList = (ArrayList<Xian>) dao.list(hql);
			this.xianList = xianList;
			StringBuffer  json = new StringBuffer();
			
			
				Iterator<Xian> it = xianList.iterator();
				json.append("[");
				
				//根据查询结果设置标志位
				if(xianList.size() > 0)
				{
					json.append("{\"result\"" + ":"+ "true},");
				}
				else
				{
					json.append("{\"result\"" + ":"+"false}}");
				}
				while(it.hasNext())
				{
					Xian xian = (Xian) it.next();
					json.append("{");
					json.append("\"id\"" +":" +"\"" + xian.getId()+"\",");
					json.append("\"name\"" +":" +"\"" + xian.getName() +"\"");
					json.append("},");
				}
				json.delete(json.length()-1, json.length());
				json.append("]");
				this.printJson(json.toString());
			
		}
		return null;
	}
	
	public String GetZhenList() throws Exception
	{
		if(this.xian != null)
		{	
			String hql = " from Zhen where xian=" + Integer.parseInt(this.xian);
			ArrayList<Zhen> zhenList = (ArrayList<Zhen>) dao.list(hql);
			this.zhenList = zhenList;
			StringBuffer  json = new StringBuffer();
			
			
				Iterator<Zhen> it = zhenList.iterator();
				json.append("[");
				
				//根据查询结果设置标志位
				if(zhenList.size() > 0)
				{
					json.append("{\"result\"" + ":"+ "true},");
				}
				else
				{
					json.append("{\"result\"" + ":"+"false}}");
				}
				while(it.hasNext())
				{
					Zhen zhen = (Zhen) it.next();
					json.append("{");
					json.append("\"id\"" +":" +"\"" + zhen.getId()+"\",");
					json.append("\"name\"" +":" +"\"" + zhen.getName() +"\"");
					json.append("},");
				}
				json.delete(json.length()-1, json.length());
				json.append("]");
				this.printJson(json.toString());
			
		}
		return null;
	}
	
	//用于校验不通过时获取省份列表
	public  List ValidateProList()
	{
		List<Province> proList = new ArrayList<Province>();
		String hql =" from Province";
		proList = (List<Province>) dao.list(hql);
		if(proList == null)
			proList = new ArrayList<Province>();
		return proList;
	}
	
	//用于校验不通过时获取城市列表
	public List ValidateCityList()
	{
		List<City> cityList = new ArrayList<City>();
		String hql = null ;
		if(!ibsValidate.isEmpty(this.province))
		{
			hql = " from City where province=" + this.getProvince();
		}
		cityList = (List<City>)dao.list(hql);
		if(cityList == null)
			cityList = new ArrayList<City>();
		return cityList;
	}
	
	
	//用于校验不通过时获取县域列表
	public List ValidateXianList()
	{
		List<Xian> xianList = new ArrayList<Xian>();
		String hql = null ;
		if(!ibsValidate.isEmpty(this.city))
		{
			hql = " from Xian where city=" + this.city;
		}
		xianList = (List<Xian>)dao.list(hql);
		if(xianList == null )
			xianList = new ArrayList<Xian>();
		return xianList;
	}
	
	//用于校验不通过时获取乡镇列表
	public List ValidateZhenList()
	{
		List<Zhen> zhenList = new ArrayList<Zhen>();
		String hql = null;
		if(!ibsValidate.isEmpty(this.xian))
		{
			hql = " from Zhen where xian=" + this.xian;
		}
		zhenList =(List<Zhen>)dao.list(hql);
		if(zhenList == null)
			zhenList = new ArrayList<Zhen>();
		return zhenList;
	}
	public void validateUpdate()
	{
		//检查用户名是否为空
		if(ibsValidate.isEmpty(this.name))
		{
			this.addFieldError("name", ibsMessages.getString("userInfo.Update.01"));
		}
		
		//检查用户名的长度
		if(!ibsValidate.isEmpty(this.name) && this.name.length() > 40)
		{
			this.addFieldError("name", ibsMessages.getString("userInfo.Update.02"));
		}
		
		//检查电话号码是否为空
		if(ibsValidate.isEmpty(phone))
		{
			this.addFieldError("phone", ibsMessages.getString("userInfo.Update.03"));
		}
		//检查是否是有效的电话号码
		else if(!ibsValidate.isMobileNumber(this.phone))
		{
			this.addFieldError("phone", ibsMessages.getString("userInfo.Update.04"));
		}
	}
	
	
	
	public String Update()
	{
		try
		{
			Userinfo userinfo = (Userinfo) session.getAttribute("userInfo");
			userinfo.setName(name);
			userinfo.setPhone(phone);
			
			if(!ibsValidate.isEmpty(this.province))
			
				userinfo.setProvince(Integer.parseInt(province));
			else
				userinfo.setProvince(null);
			
			if(!ibsValidate.isEmpty(this.city))
				
				userinfo.setShi(Integer.parseInt(city));
			else
				userinfo.setShi(null);
			
			if(!ibsValidate.isEmpty(this.xian))
				
				userinfo.setXian(Integer.parseInt(xian));
			else
				userinfo.setXian(null);
			
			if(!ibsValidate.isEmpty(this.zhen))
				
				userinfo.setZhen(Integer.parseInt(zhen));
			else
				userinfo.setZhen(null);
			
			dao.update((T) userinfo);
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getXian() {
		return xian;
	}

	public void setXian(String xian) {
		this.xian = xian;
	}

	public String getZhen() {
		return zhen;
	}

	public void setZhen(String zhen) {
		this.zhen = zhen;
	}

	public List<Province> getProList() {
		return proList;
	}

	public void setProList(List<Province> proList) {
		this.proList = proList;
	}

	public List<City> getCityList() {
		return cityList;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

	public List<Xian> getXianList() {
		return xianList;
	}

	public void setXianList(List<Xian> xianList) {
		this.xianList = xianList;
	}

	public List<Zhen> getZhenList() {
		return zhenList;
	}

	public void setZhenList(List<Zhen> zhenList) {
		this.zhenList = zhenList;
	}
}