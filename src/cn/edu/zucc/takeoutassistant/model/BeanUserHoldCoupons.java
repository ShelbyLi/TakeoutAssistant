package cn.edu.zucc.takeoutassistant.model;

import java.util.Date;

public class BeanUserHoldCoupons extends BeanEntity {

	private int user_id;
	private int coupon_id;
	private int amount;
	private Date ddl;
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
	
}
