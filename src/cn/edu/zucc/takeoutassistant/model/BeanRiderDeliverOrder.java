package cn.edu.zucc.takeoutassistant.model;

import java.util.Date;

public class BeanRiderDeliverOrder extends BeanEntity {

	private int order_id;
	private int rider_id;
	private Date deliver_time;
	private int deliver_user_rate;
	private double deliver_single_income;
	
	
	
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getRider_id() {
		return rider_id;
	}
	public void setRider_id(int rider_id) {
		this.rider_id = rider_id;
	}
	public Date getDeliver_time() {
		return deliver_time;
	}
	public void setDeliver_time(Date deliver_time) {
		this.deliver_time = deliver_time;
	}
	public int getDeliver_user_rate() {
		return deliver_user_rate;
	}
	public void setDeliver_user_rate(int deliver_user_rate) {
		this.deliver_user_rate = deliver_user_rate;
	}
	public double getDeliver_single_income() {
		return deliver_single_income;
	}
	public void setDeliver_single_income(double deliver_single_income) {
		this.deliver_single_income = deliver_single_income;
	}
	
}
