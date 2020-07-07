package cn.edu.zucc.takeoutassistant.model;

public class BeanProduct extends BeanEntity {

	private int product_id;
	private int productcategory_id;
	private int shop_id;
	private String product_name;
	private double product_price;
	private double product_discounted_price;
	
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getProductcategory_id() {
		return productcategory_id;
	}
	public void setProductcategory_id(int productcategory_id) {
		this.productcategory_id = productcategory_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public double getProduct_price() {
		return product_price;
	}
	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}
	public double getProduct_discounted_price() {
		return product_discounted_price;
	}
	public void setProduct_discounted_price(double product_discounted_price) {
		this.product_discounted_price = product_discounted_price;
	}
	
}