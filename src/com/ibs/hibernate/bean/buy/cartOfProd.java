package com.ibs.hibernate.bean.buy;

import java.io.Serializable;

import com.ibs.hibernate.bean.product.Product;


public class cartOfProd implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9159134131815584134L;

	private Integer count;
	
	private Product prod;
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Product getProd() {
		return prod;
	}

	public void setProd(Product prod) {
		this.prod = prod;
	}
	
	
}
