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

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import Hibernate.HibernateSessionFactory;

import base.BaseAction;

import com.ibs.hibernate.bean.place.City;
import com.ibs.hibernate.bean.place.Province;
import com.opensymphony.xwork2.ActionSupport;

public class placeAction extends ActionSupport{
	
	private Integer provinceId;
	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public List<City> getCityList() {
		return cityList;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

	private Integer cityId;
	private List<Province> provinceList = new ArrayList<Province>();
	private List<City> cityList = new ArrayList<City>();
	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	
	
	public List<Province> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<Province> provinceList) {
		this.provinceList = provinceList;
	}

	public String execute()
	{
		Session hibSession = HibernateSessionFactory.getSessionFactory().openSession();
		try
		{
			String hql = "";
			hql =" from Province";
			setProvinceList(hibSession.createQuery(hql).list());
			return "view";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			hibSession.close();
		}
	}
	
}