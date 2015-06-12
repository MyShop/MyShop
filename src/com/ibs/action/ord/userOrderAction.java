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
package com.ibs.action.ord;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import base.BaseAction;

import com.ibs.hibernate.bean.ord.Order;
import com.ibs.hibernate.bean.ord.OrderProd;
import com.ibs.hibernate.bean.user.Userinfo;
import com.ibs.hibernate.dao.BaseDAO;

public class userOrderAction<T> extends BaseAction{

	/**
	 * 查询用户订单信息
	 */
	private static final long serialVersionUID = 9059859757758116322L;

	private List<Order> orderList;

	private Userinfo userInfo;

	/** 该属性Spring的配置文件已配置 */
	private BaseDAO<T> dao;


	public String List() throws Exception {

		try {
			userInfo = (Userinfo)session.get("userInfo");
			String userNo = userInfo.getId().toString();
			String hql = "  from Order where userNo = '" + userNo + "'";
			orderList = (List<Order>) dao.list(hql);
			if (orderList != null && orderList.size() > 0) {
				Iterator<Order> it = orderList.iterator();
				while (it.hasNext()) {
					Order order =  it.next();
					Integer orderNo =  Integer.parseInt(order.getOrderNo());
					/* 查询该订单所购的产品 */
					String hql1 = " select prod.name,order.count,prod.price  from  OrderProd order,Product prod where order.prodNo=prod.id and order.orderNo ='"
							+ orderNo + "'";
					List<Object[]> list= (List<Object[]>) dao.list(hql1);
					Iterator<Object[]> obIt = list.iterator();
					List<OrderProd> orderProdList = new ArrayList();
					while(obIt.hasNext())
					{
						Object[] ob = obIt.next();
						OrderProd orderProd = new OrderProd();
						orderProd.setName((String)ob[0]);
						orderProd.setCount(Integer.valueOf(ob[1].toString()));
						orderProd.setPrice(Double.valueOf(ob[2].toString()));
						orderProdList.add(orderProd);
					}
					order.setProdList(orderProdList);
				}
			}
			request.setAttribute("orderList", orderList);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "orderList";
	}
	
	public String Repair()
	{
		String id = request.getParameter("id");
		String hql = "  from Order where orderNo = '" + id + "'";
		orderList = (List<Order>) dao.list(hql);
		
		/**默认一个订单号对应一条记录*/
		
		Order order = orderList.get(0);
		Date datefrom = order.getOrderDate();
		/**预定的时间加两年。即760天*/
		long timeRepair = (long) (datefrom.getTime() +365*2*24*60*60*1000.0);
		request.setAttribute("timeRepair", timeRepair);
		return "orderRepair";
	}

	public BaseDAO<T> getDao() {
		return dao;
	}

	public void setDao(BaseDAO<T> dao) {
		this.dao = dao;
	}

}