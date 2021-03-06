package cn.edu.zucc.takeoutassistant.model;

import java.util.Date;

public class BeanUserHoldCoupons extends BeanEntity {

	private int user_id;
	private int coupon_id;
	private int amount;
	private Date ddl;
	private String shop_name;
	private double coupon_amount;
	private int coupon_ordered_number_requirement;
	private Date coupon_start_time;
	private Date coupon_end_time;
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(int coupon_id) {
		this.coupon_id = coupon_id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getDdl() {
		return ddl;
	}
	public void setDdl(Date ddl) {
		this.ddl = ddl;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public double getCoupon_amount() {
		return coupon_amount;
	}
	public void setCoupon_amount(double coupon_amount) {
		this.coupon_amount = coupon_amount;
	}
	public int getCoupon_ordered_number_requirement() {
		return coupon_ordered_number_requirement;
	}
	public void setCoupon_ordered_number_requirement(int coupon_ordered_number_requirement) {
		this.coupon_ordered_number_requirement = coupon_ordered_number_requirement;
	}
	public Date getCoupon_start_time() {
		return coupon_start_time;
	}
	public void setCoupon_start_time(Date coupon_start_time) {
		this.coupon_start_time = coupon_start_time;
	}
	public Date getCoupon_end_time() {
		return coupon_end_time;
	}
	public void setCoupon_end_time(Date coupon_end_time) {
		this.coupon_end_time = coupon_end_time;
	}
	
	
}
