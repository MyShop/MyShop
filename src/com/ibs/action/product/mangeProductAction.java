package com.ibs.action.product;

import java.util.List;

import base.BaseAction;

import com.ibs.hibernate.bean.page.PageBean;
import com.ibs.hibernate.bean.product.Product;
import com.ibs.hibernate.dao.BaseDAO;

public class mangeProductAction<T> extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4530397280482366857L;
	
	//处理查询产品列表页面---字段
	private List<Product> products;
	private BaseDAO<T> dao;
	
    private int pageNow = 1;//页面数，初始化为1
	
	private int pageSize = 5;//页面行数
	
	
	//处理修改产品页面--字段
	private String id;
	
	private String name;
	
	private String price;
	
	private String type;
	
	private String picture;
	
	private String miaoshu;
	
	private String[] filePath;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getMiaoshu() {
		return miaoshu;
	}

	public void setMiaoshu(String miaoshu) {
		this.miaoshu = miaoshu;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public BaseDAO<T> getDao() {
		return dao;
	}

	public void setDao(BaseDAO<T> dao) {
		this.dao = dao;
	}

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

	private int pageCount;//总页数
	
	private int rowCount;//总行数
	
	public String intoProdPage()
	{
			String hql = "from Product";
			
			/*如果记录条数为0，就重新查询记录条数*/
			if(rowCount == 0)
			{
				
				rowCount =dao.list(hql).size();
				if(rowCount % pageSize > 0)
				{
					pageCount = rowCount/pageSize +1;
				}
				else
				{
					pageCount = rowCount/pageSize;
				}
			}
			
			products = (List<Product>)dao.list(hql,(pageNow-1)*pageSize,pageSize);
			request.setAttribute("products", products);
			
			
		return "intoSearch";
	}
	
	public String editProdPage()
	{
		this.id = request.getParameter("id");
		Product prod =  (Product)dao.find((Class<T>)Product.class, Integer.parseInt(id));
		this.id = prod.getId().toString();
		this.name = prod.getName();
		this.price = prod.getPrice().toString();
		this.type = prod.getType().toString();
		this.picture = prod.getPicture();
		this.miaoshu = prod.getMiaoshu();
		return "view";
	}
	
	public String updateProdPage()
	{
		Product prod = new Product();
		if(filePath != null)
		{
			for (String file : filePath) 
			{
				this.picture = this.picture +file + ";";
				
			}
		}
		
		
		prod.setId(Integer.parseInt(this.id));
		prod.setName(this.name);
		prod.setType(Integer.parseInt(this.type));
		prod.setPrice(Double.parseDouble(price));
		prod.setMiaoshu(this.miaoshu);
		prod.setPicture(this.picture);
		
		dao.update((T)prod);
		
		return null;
	}

	public String[] getFilePath() {
		return filePath;
	}

	public void setFilePath(String[] filePath) {
		this.filePath = filePath;
	}
}
