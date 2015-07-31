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
package com.ibs.action.merch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import base.BaseAction;
import base.ibsMessages;
import base.ibsValidate;

import com.ibs.hibernate.bean.merch.IpVote;
import com.ibs.hibernate.bean.merch.Merchant;
import com.ibs.hibernate.dao.BaseDAO;

/**
 * 招商功能Action
 * @author Robin
 *
 * @param <T>
 * create 2015-04-21 by Robin
 */
public class merchantsAction<T> extends BaseAction{



	private static final long serialVersionUID = -6568676460041536019L;
	

	private String name;
	
	private String phone;
	
	private String addr;
	
	private String project;

	private String file[];
	
	private int pageNow = 1;//页面数，初始化为1
	
	private int pageSize = 50;//页面行数
	
	private int pageCount;//总页数
	
	private int rowCount;//总行数
	
	
	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}


	private BaseDAO<T> dao;
	
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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String[] getFile() {
		return file;
	}

	public void setFile(String[] file) {
		this.file = file;
	}

	public String Inform()
	{
		return "input";
	}
	
	public void validateCommit()
	{
		//校验名称字段
		if(ibsValidate.isEmpty(this.name))
			
			this.addFieldError("name", ibsMessages.getString("merchant.commit.01"));
		
		else if(this.name.length() > 40)
		{
			//检查名称的长度
			this.addFieldError("name", ibsMessages.getString("merchant.commit.03"));
			
		}
		else
		{
			//检查是否已被注册
			String hql = " from Merchant where name='"+this.name+"'";
			
			List<Merchant> merchs = (List<Merchant>)dao.list(hql);
			
			if(merchs.size() > 0)
			{
				this.addFieldError("name", ibsMessages.getString("merchant.commit.02"));
			}
			
		}
		
		
		//校验联系电话
		if(ibsValidate.isEmpty(this.phone))
		{
			this.addFieldError("phone", ibsMessages.getString("merchant.commit.04"));
		}
		else if(!ibsValidate.isMobileNumber(this.phone))
		{
			this.addFieldError("phone",ibsMessages.getString("merchant.commit.05"));
		}
		else
		{
			String hql = " from  Merchant where phone='" + this.phone +"'";
			List<Merchant> merchs = (List<Merchant>)dao.list(hql);
			if(merchs.size() > 0)
			{
				this.addFieldError("phone", ibsMessages.getString("merchant.commit.10"));
			}
		}
		
		if(ibsValidate.isEmpty(this.addr))
		{
			this.addFieldError("addr", ibsMessages.getString("merchant.commit.06"));
		}
		else if(this.addr.length() > 45)
		{
			this.addFieldError("addr", ibsMessages.getString("merchant.commit.07"));
		}
		
		if(ibsValidate.isEmpty(this.project))
		{
			this.addFieldError("project", ibsMessages.getString("merchant.commit.08"));
		}
		else if(this.project.length() > 45)
		{
			this.addFieldError("project", ibsMessages.getString("merchant.commit.09"));
		}
	}
	
	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String Commit()
	{
		Merchant merch = new Merchant();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		
		merch.setName(this.name);
		merch.setPhone(this.phone);
		merch.setAddr(this.addr);
		merch.setProject(this.project);
		merch.setDate(df.format(new Date()));
		dao.save((T) merch);
		
		
		//页面预处理
		this.conrolPageData();
		return "intoVote";
	}
	
	public String toVote()
	{
		this.conrolPageData();
		return "intoVote";
	}
	
	public String Vote() throws Exception
	{
		
		String id = request.getParameter("id");
		Integer merchId = Integer.parseInt(id);
		Merchant merch = (Merchant)dao.find((Class<T>)Merchant.class, merchId);
		Integer merchVote = merch.getVotes();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String Date = df.format(new Date());//获得时间以供更新表所需
		
		//防止数据库中该字段为NULL
		if(merchVote == null)
			merchVote = 0;
		merchVote = merchVote + 1;
		merch.setVotes(merchVote);
		StringBuffer  json = new StringBuffer();
		try
		{
			//查询IP投票表验证该IP是否已经投过该用户
			String userIP = request.getRemoteAddr();
			String hql = " from IpVote where ip = '" +userIP +"'";
			List<IpVote> ipvote = (List<IpVote>)dao.list(hql);
			if(ipvote.size() == 0)
			{	
				//更新商家投票数
				dao.update((T)merch);
				
				//更新IP投票表
				IpVote  vote = new IpVote();
				vote.setIp(userIP);
				vote.setIpvote1(id);
				vote.setDate(Date);
				dao.create((T)vote);
				json.append("[{\"result\":\"success\",");
				json.append("\"votes\":\""+merchVote.toString()+"\"}]");
			}
			else
			{
				IpVote  iptemp = ipvote.get(0);
				
				
				
				boolean isContan = false;//是否包含该投票标志
				if(id.equals(iptemp.getIpvote1()))
				{
					isContan = true;
				}
				else if(id.equals(iptemp.getIpvote2()))
				{
					isContan = true;
				}
				else if(id.equals(iptemp.getIpvote3()))
				{
					isContan = true;
				}
				else if(id.equals(iptemp.getIpvote4()))
				{
					isContan = true;
				}
				else if(id.equals(iptemp.getIpvote5()))
				{
					isContan = true;
				}
				
				if(isContan)
				{
					json.append("[{\"result\":\"false\",");
					json.append("\"message\":\""+ibsMessages.getString("merchant.vote.01")+"\"}]");
					return null;
				}
				else
				{
					boolean isFull = false;//是否已投票满5次
					int index = 1;//投票字段列索引
					if(!ibsValidate.isEmpty(iptemp.getIpvote1()) && ibsValidate.isEmpty(iptemp.getIpvote2()) )
					{
						index = 2;
					}
					else if(!ibsValidate.isEmpty(iptemp.getIpvote2()) && ibsValidate.isEmpty(iptemp.getIpvote3()))
					{
						index = 3;
					}
					else if(!ibsValidate.isEmpty(iptemp.getIpvote3()) && ibsValidate.isEmpty(iptemp.getIpvote4()))
					{
						index = 4;
					}
					else if(!ibsValidate.isEmpty(iptemp.getIpvote4()) && ibsValidate.isEmpty(iptemp.getIpvote5()))
					{
						index = 5;
					}
					else if(!ibsValidate.isEmpty(iptemp.getIpvote5()))
					{
						isFull = true;
					}
					
					if(isFull)
					{
						json.append("[{\"result\":\"false\",");
						json.append("\"message\":\""+ibsMessages.getString("merchant.vote.02")+"\"}]");
					}
					else
					{
						
						switch(index)
						{
							case 1:iptemp.setIpvote1(id);break;
						
							case 2:iptemp.setIpvote2(id);break;
						
							case 3:iptemp.setIpvote3(id);break;
						
							case 4:iptemp.setIpvote4(id);break;
						
							case 5:iptemp.setIpvote5(id);break;

						}
						iptemp.setDate(Date);//设置更新时间
						dao.update((T)iptemp);
						dao.update((T)merch);
						json.append("[{\"result\":\"success\",");
						json.append("\"votes\":\""+merchVote.toString()+"\"}]");
					}
				}

			}
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			json.append("[{\"result\":\"false\",");
			json.append("\"message\":\""+ibsMessages.getString("merchant.vote.03")+"\"}]");
		}
		finally
		{
			this.printJson(json.toString());
		}
		return null;
	}
	
	public BaseDAO<T> getDao() {
		return dao;
	}


	public void setDao(BaseDAO<T> dao) {
		this.dao = dao;
	}

	
	public void conrolPageData()
	{
		//进入投票页面，对商家列表进行查询分页等预处理
				String PageURL = ServletActionContext.getRequest().getRequestURL().toString();//取得上一页或下一页链接的访问地址
				String hql = " from Merchant order by votes desc";
								
				if(rowCount == 0)
				{
					rowCount = dao.list(hql).size();
									
					if(rowCount % pageSize > 0)
					{
						pageCount = rowCount/pageSize +1;
					}
					else
					{
						pageCount = rowCount/pageSize;
					}

				}
						
				List<Merchant> merchsList = (List<Merchant>)dao.list(hql,(pageNow-1)*pageSize,pageSize);
				request.setAttribute("merchsList", merchsList);
				request.setAttribute("PageURL", PageURL);

	}
}