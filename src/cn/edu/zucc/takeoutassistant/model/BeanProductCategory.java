package cn.edu.zucc.takeoutassistant.model;

public class BeanProductCategory extends BeanEntity {

	private int productcategory_id;
	private int shop_id;
	private String productcategory_name;
	private int productcategory_delete_time;
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
	
	
}
