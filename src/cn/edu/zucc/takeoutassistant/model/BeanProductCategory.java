package cn.edu.zucc.takeoutassistant.model;

import java.util.ArrayList;
import java.util.List;

public class BeanProductCategory extends BeanEntity {

	private int productcategory_id;
	private int shop_id;
	private String productcategory_name;
	private int productcategory_delete_time;
	private List<BeanProduct> products = new ArrayList<BeanProduct>();
	private int products_cnt;
	
	public int getProductcategory_id() {
		return productcategory_id;
	}
	public void setProductcategory_id(int productcategory_id) {
		this.productcategory_id = productcategory_id;
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public String getProductcategory_name() {
		return productcategory_name;
	}
	public void setProductcategory_name(String productcategory_name) {
		this.productcategory_name = productcategory_name;
	}
	public int getProductcategory_delete_time() {
		return productcategory_delete_time;
	}
	public void setProductcategory_delete_time(int productcategory_delete_time) {
		this.productcategory_delete_time = productcategory_delete_time;
	}
	public List<BeanProduct> getProducts() {
		return products;
	}
	public void setProducts(List<BeanProduct> products) {
		this.products = products;
	}
	public int getProducts_cnt() {
		return products_cnt;
	}
	public void setProducts_cnt(int products_cnt) {
		this.products_cnt = products_cnt;
	}
	
	
}
