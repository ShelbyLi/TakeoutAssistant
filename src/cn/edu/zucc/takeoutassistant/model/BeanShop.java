package cn.edu.zucc.takeoutassistant.model;

import java.sql.Date;

public class BeanShop extends BeanPeople{

	private int shop_id;
	private String shop_name;
	private String shop_pwd;
	private double shop_level;
	private double shop_per_capita_consumption;
	private int shop_total_sales;
	private Date shop_logout_time;
	
	public String getShop_pwd() {
		return shop_pwd;
	}
	public void setShop_pwd(String shop_pwd) {
		this.shop_pwd = shop_pwd;
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public double getShop_level() {
		return shop_level;
	}
	public void setShop_level(double shop_level) {
		this.shop_level = shop_level;
	}
	public double getShop_per_capita_consumption() {
		return shop_per_capita_consumption;
	}
	public void setShop_per_capita_consumption(double shop_per_capita_consumption) {
		this.shop_per_capita_consumption = shop_per_capita_consumption;
	}
	public int getShop_total_sales() {
		return shop_total_sales;
	}
	public void setShop_total_sales(int shop_total_sales) {
		this.shop_total_sales = shop_total_sales;
	}
	public Date getShop_logout_time() {
		return shop_logout_time;
	}
	public void setShop_logout_time(Date shop_logout_time) {
		this.shop_logout_time = shop_logout_time;
	}
	

}
