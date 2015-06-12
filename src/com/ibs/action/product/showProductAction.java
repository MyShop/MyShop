package com.ibs.action.product;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import base.BaseAction;

import com.ibs.hibernate.bean.product.Product;
import com.ibs.hibernate.dao.BaseDAO;

public class showProductAction<T> extends BaseAction{
	
	private static final long serialVersionUID = -4915833268799958654L;
	
	//商品的种类
	private String type;
	
	//商品列表
	private List<Product> productList;
	
	private BaseDAO<T> dao;
	
	
	
	private int pageNow = 1;//页面数，初始化为1
	
	private int pageSize = 3;//页面行数
	
	private int pageCount;//总页数
	
	private int rowCount;//总行数
	
	public int getPageNow()
	{
		return pageNow;
	}
	
	public void setPageNow(int pageNow)
	{
		this.pageNow = pageNow;
	}
	
	public int getPageCount()
	{
		return pageCount;
	}
	
	public void setPageCount(int pageCount)
	{
		this.pageCount = pageCount;
	}
	
	public int  getRowCount()
	{
		return rowCount;
	}
	
	public void setRowCount(int  rowCount)
	{
		this.rowCount = rowCount;
	}
	
	public BaseDAO<T> getDao() {
		return dao;
	}

	public void setDao(BaseDAO<T> dao) {
		this.dao = dao;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Product> getProduct() {
		return productList;
	}

	public void setProduct(List<Product> productList) {
		this.productList = productList;
	}

	
	/**
	 * 
	 */
	
	
	public String show()
	{
		String prodUrl = ServletActionContext.getRequest().getRequestURL().toString();
		String hql = null;
		/*设置默认当前页为1*/
		
		hql =  "  from Product where type="+Integer.parseInt(type);
		
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
			
		productList = (List<Product>) dao.list(hql,(pageNow-1)*pageSize,pageSize);
			
		if(productList.size() > 0)
		{
			for (int i = 0;i < productList.size(); i++) 
			{
				String[] pictures = null;
				if(productList.get(i).getPicture() != null)
				pictures = productList.get(i).getPicture().split(";");
				if(pictures != null && pictures.length > 0)
				productList.get(i).setFirstPicture(pictures[0]);
			}
		}
		
		request.setAttribute("productList", productList);
		request.setAttribute("prodUrl", prodUrl);
		request.setAttribute("type", type);
		return "view";
	
	}
	
	public String Info()
	{
		String id = request.getParameter("id");
		if(id != null)
		{
			Integer proid = Integer.parseInt(id);
		}
		
		Product prod = (Product)dao.find((Class<T>)Product.class, Integer.parseInt(id));
		//定义产品缩略图路径
		String[] firstPicture = null;
		if(prod.getPicture() != null)
		{
			firstPicture = prod.getPicture().split(";");
		}
		
		if(firstPicture != null)
		{
			prod.setFirstPicture(firstPicture[0]);
		}
		request.setAttribute("prod",prod );
		return "Info";
	}
}
