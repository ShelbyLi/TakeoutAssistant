package cn.edu.zucc.takeoutassistant.model;

import java.util.Date;

public class BeanCoupon extends BeanEntity {

	private int coupon_id;
	private int shop_id;
	private double coupon_amount;
	private int coupon_ordered_number_requirement;
	private Date coupon_start_time;
	private Date coupon_end_time;
	private Date coupon_delete_time;
	public int getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(int coupon_id) {
		this.coupon_id = coupon_id;
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
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
	public Date getCoupon_delete_time() {
		return coupon_delete_time;
	}
	public void setCoupon_delete_time(Date coupon_delete_time) {
		this.coupon_delete_time = coupon_delete_time;
	}
}
