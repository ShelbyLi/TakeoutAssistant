package cn.edu.zucc.takeoutassistant.model;

import java.util.Date;

public class BeanOrderForm extends BeanEntity {

	private int order_id;
	private int user_id;
	private int addr_id;
	private int shop_id;
	private int rider_id;
	private int coupon_id;
	private int fullreduction_id;
	private double order_original_amount;
	private double order_actual_amount;
	private Date order_time;
	private Date order_request_delivery_time;
	private int order_status;
	private Date order_cancle_time;
	
	public static final int delivering = 0;
	public static final int timeout = 1;
	public static final int arrived = 2;
	public static final int cancled = 3;
	
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getAddr_id() {
		return addr_id;
	}
	public void setAddr_id(int addr_id) {
		this.addr_id = addr_id;
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public int getRider_id() {
		return rider_id;
	}
	public void setRider_id(int rider_id) {
		this.rider_id = rider_id;
	}
	public int getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(int coupon_id) {
		this.coupon_id = coupon_id;
	}
	public int getFullreduction_id() {
		return fullreduction_id;
	}
	public void setFullreduction_id(int fullreduction_id) {
		this.fullreduction_id = fullreduction_id;
	}
	public double getOrder_original_amount() {
		return order_original_amount;
	}
	public void setOrder_original_amount(double order_original_amount) {
		this.order_original_amount = order_original_amount;
	}
	public double getOrder_actual_amount() {
		return order_actual_amount;
	}
	public void setOrder_actual_amount(double order_actual_amount) {
		this.order_actual_amount = order_actual_amount;
	}
	public Date getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}
	public Date getOrder_request_delivery_time() {
		return order_request_delivery_time;
	}
	public void setOrder_request_delivery_time(Date order_request_delivery_time) {
		this.order_request_delivery_time = order_request_delivery_time;
	}
	public int getOrder_status() {
		return order_status;
	}
	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}
	public Date getOrder_cancle_time() {
		return order_cancle_time;
	}
	public void setOrder_cancle_time(Date order_cancle_time) {
		this.order_cancle_time = order_cancle_time;
	}
	
	
}
