package com.ibs.action.buy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import base.BaseAction;
import base.PrimaryGenerater;

import com.ibs.hibernate.bean.buy.cartOfProd;
import com.ibs.hibernate.bean.ord.Order;
import com.ibs.hibernate.bean.ord.OrderProd;
import com.ibs.hibernate.bean.ord.OrderReceive;
import com.ibs.hibernate.bean.place.City;
import com.ibs.hibernate.bean.place.Province;
import com.ibs.hibernate.bean.place.Xian;
import com.ibs.hibernate.bean.place.Zhen;
import com.ibs.hibernate.bean.product.Product;
import com.ibs.hibernate.bean.user.Userinfo;
import com.ibs.hibernate.dao.BaseDAO;

public class buyProductAction<T> extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2366331374143722706L;

	// 商品的id
	private String id;

	// 商品数量
	private String count;
	
	//收款人地址信息
	private String province;
	
	private String city;
	
	private String xian;
	
	private String zhen;
	
	private String area;
	
	private List<Province> proList;
	
	private List<City> cityList;

	private List<Xian> xianList;
	
	private List<Zhen> zhenList;
	
	//付款方式
	private String payType;
	
	//收款人联系方式
	private String phone;
	
	
	public String getIsBuy() {
		return isBuy;
	}

	public void setIsBuy(String isBuy) {
		this.isBuy = isBuy;
	}

	private String isBuy;

	private BaseDAO<T> dao;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BaseDAO<T> getDao() {
		return dao;
	}

	public void setDao(BaseDAO<T> dao) {
		this.dao = dao;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setZhenList(List<Zhen> zhenList) {
		this.zhenList = zhenList;
	}

	public String getPayType() {
		return payType;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String addToCart() {

		Integer prodId = Integer.parseInt(this.id);
		Integer prodCount = Integer.parseInt(this.count);// 购买数量
		Product prod = (Product) dao.find((Class<T>) Product.class, prodId);// 查询该ID的产品
		Double subTotal = 0.0;// 商品总价多少
		cartOfProd cart = new cartOfProd();// 构造一个购物车对象
		cart.setCount(prodCount);// 给购物车赋值
		cart.setProd(prod);// 给购物车赋值
		List<cartOfProd> cartList = (List<cartOfProd>) session.get("cartList");

		boolean isContan = false;// 购物栏是否包含该产品
		if (cartList != null) {
			Iterator it = cartList.iterator();
			while (it.hasNext()) {
				cartOfProd tempCart = (cartOfProd) it.next();
				if (prodId.equals(tempCart.getProd().getId())) {
					isContan = true;
					Integer count = tempCart.getCount();
					// 判断是不是购买提交
					if ("true".equals(this.isBuy)) {
						tempCart.setCount(count + prodCount);
					}
					subTotal = tempCart.getCount()
							* tempCart.getProd().getPrice();
				}

			}

			if (!isContan && "true".equals(this.isBuy)) {
				cartList.add(cart);
			}

			session.put("cartList", cartList);
		} else {

			if ("true".equals(this.isBuy)) {
				cartList = new ArrayList<cartOfProd>();
				cartList.add(cart);
				subTotal = prodCount * prod.getPrice();
				session.put("cartList", cartList);
			}
		}

		request.setAttribute("cartList", cartList);
		request.setAttribute("subTotal", subTotal);
		return "toCart";

	}

	public String addOneToCart() throws Exception {
		String id = request.getParameter("id");
		Integer prodId = Integer.parseInt(id);
		List<cartOfProd> cartList = (List<cartOfProd>) session.get("cartList");
		StringBuffer json = new StringBuffer();
		for (cartOfProd cartTemp : cartList) {
			if (prodId.equals(cartTemp.getProd().getId())) {
				int count = cartTemp.getCount();
				count += 1;
				cartTemp.setCount(count);
				json.append("[{\"result\":\"success\"}]");
				break;
			}
		}
		this.printJson(json.toString());
		return null;
	}

	public String delFromCart() throws Exception {
		String id = request.getParameter("id");
		Integer prodId = Integer.parseInt(id);
		List<cartOfProd> cartList = (List<cartOfProd>) session.get("cartList");
		StringBuffer json = new StringBuffer();
		for (cartOfProd cartTemp : cartList) {
			if (prodId.equals(cartTemp.getProd().getId())) {
				cartList.remove(cartTemp);
				json.append("[{\"result\":\"success\"}]");
				break;
			}
		}
		this.printJson(json.toString());
		return null;
	}


	public String toAccountCart() {
		String hql;
		hql =" from Province";
		List<Province> proList = (List<Province>)dao.list(hql);
		
		List<cartOfProd> cartList = (List<cartOfProd>)session.get("cartList");
		request.setAttribute("proList", proList);
		request.setAttribute("cartList", cartList);
		
		
		return "toConfirm";
	}
	
	public String provinceToCart() throws Exception 
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
	
	public String cityToCart() throws Exception
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
	
	public String xianToCart() throws Exception
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
	
	public String commitMyCart()
	{
		// 预处理Order表中的信息
				Userinfo userinfo = (Userinfo) session.get("userInfo");
				Integer userNo = userinfo.getId();
				String phone = userinfo.getPhone();
				// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date javadate = new java.util.Date();
				int year = javadate.getYear();
				int month = javadate.getMonth();
				int day = javadate.getDay();
				java.sql.Date sqldate = new java.sql.Date(year, month, day);
				// String dataStr = sdf.format(date);
				String orderNo = PrimaryGenerater.getInstance().generaterNextNumber();// 生成交易流水号

				// 创建插入的JavaBean并赋值
				Order order = new Order();
				order.setOrderNo(orderNo);
				order.setUserNo(userNo);
				order.setOrderDate(sqldate);
				order.setPhone(phone);

				// 预处理Order表中的Product的信息
				List<cartOfProd> cartList = (List<cartOfProd>) session.get("cartList");

				for (cartOfProd cartTemp : cartList) {
					OrderProd orderProd = new OrderProd();
					orderProd.setOrderNo(orderNo);
					orderProd.setProdNo(cartTemp.getProd().getId());
					orderProd.setPrice(cartTemp.getProd().getPrice());
					orderProd.setCount(cartTemp.getCount());
					dao.create((T) orderProd);
				}

				// 测试 OrderReceive表
				OrderReceive orderReceive = new OrderReceive();
				orderReceive.setOrderNo(orderNo);
				orderReceive.setProvince(Integer.parseInt(this.province));
				orderReceive.setCity(Integer.parseInt(this.city));
				orderReceive.setXian(Integer.parseInt(this.xian));
				orderReceive.setZhen(Integer.parseInt(this.zhen));
				orderReceive.setArea(this.area);
				orderReceive.setPayType(Integer.parseInt(this.payType));
				orderReceive.setPhone(this.phone);
				dao.create((T) orderReceive);
				return "toResult";
	}
}
