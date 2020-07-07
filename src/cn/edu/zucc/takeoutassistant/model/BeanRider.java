package cn.edu.zucc.takeoutassistant.model;

import java.util.Date;

public class BeanRider extends BeanPeople {

	private int rider_id;
	private String rider_name;
	private String rider_pwd;
	private Date rider_entry_date;
	private int rider_identity;
	private double rider_total_income;
	private Date rider_logout_time;
	
	// ∆Ô ÷…Ì∑›
	public static final int newcomer = 1;
	public static final int regularEmployee = 2;
	public static final int king = 3;
	
	public int getRider_id() {
		return rider_id;
	}
	public void setRider_id(int rider_id) {
		this.rider_id = rider_id;
	}
	public String getRider_name() {
		return rider_name;
	}
	public void setRider_name(String rider_name) {
		this.rider_name = rider_name;
	}
	public String getRider_pwd() {
		return rider_pwd;
	}
	public void setRider_pwd(String rider_pwd) {
		this.rider_pwd = rider_pwd;
	}
	public Date getRider_entry_date() {
		return rider_entry_date;
	}
	public void setRider_entry_date(Date rider_entry_date) {
		this.rider_entry_date = rider_entry_date;
	}
	public int getRider_identity() {
		return rider_identity;
	}
	public void setRider_identity(int rider_identity) {
		this.rider_identity = rider_identity;
	}
	public double getRider_total_income() {
		return rider_total_income;
	}
	public void setRider_total_income(double rider_total_income) {
		this.rider_total_income = rider_total_income;
	}
	public Date getRider_logout_time() {
		return rider_logout_time;
	}
	public void setRider_logout_time(Date rider_logout_time) {
		this.rider_logout_time = rider_logout_time;
	}
}
