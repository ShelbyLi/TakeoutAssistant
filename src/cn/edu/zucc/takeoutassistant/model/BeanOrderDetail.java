package cn.edu.zucc.takeoutassistant.model;

public class BeanOrderDetail extends BeanEntity {

	private int order_id;
	private int product_id;
	private int amount;
	private double price;
	private String product_name;
	private double discounted_price;
	
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public double getDiscounted_price() {
		return discounted_price;
	}
	public void setDiscounted_price(double discounted_price) {
		this.discounted_price = discounted_price;
	}
	
	
}
