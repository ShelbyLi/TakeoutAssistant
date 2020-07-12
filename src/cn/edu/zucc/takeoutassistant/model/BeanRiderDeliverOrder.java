package cn.edu.zucc.takeoutassistant.model;

import java.util.Date;

public class BeanRiderDeliverOrder extends BeanEntity {

	private int order_id;
	private int rider_id;
	private Date deliver_time;
	private int deliver_user_rate;
	private double deliver_single_income;
	private double push_money;
	private int shop_id;
	private Date order_time;
	private Date order_request_delivery_time;
	private int order_status;
	private int addr_id;
	private String addr_province;
	private String addr_city;
	private String addr_district;
	private String addr_detailed_addr;
	private String addr_contact_person;
	private String addr_contact_phone;
	
	
	
	public static final int NOREVIEW = 0;
	public static final int GOODREVIEW = 1;
	public static final int BADREVIEW = 2;
	
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
	public double getPush_money() {
		return push_money;
	}
	public void setPush_money(double push_money) {
		this.push_money = push_money;
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
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
	public int getAddr_id() {
		return addr_id;
	}
	public void setAddr_id(int addr_id) {
		this.addr_id = addr_id;
	}
	public String getAddr_province() {
		return addr_province;
	}
	public void setAddr_province(String addr_province) {
		this.addr_province = addr_province;
	}
	public String getAddr_city() {
		return addr_city;
	}
	public void setAddr_city(String addr_city) {
		this.addr_city = addr_city;
	}
	public String getAddr_district() {
		return addr_district;
	}
	public void setAddr_district(String addr_district) {
		this.addr_district = addr_district;
	}
	public String getAddr_detailed_addr() {
		return addr_detailed_addr;
	}
	public void setAddr_detailed_addr(String addr_detailed_addr) {
		this.addr_detailed_addr = addr_detailed_addr;
	}
	public String getAddr_contact_person() {
		return addr_contact_person;
	}
	public void setAddr_contact_person(String addr_contact_person) {
		this.addr_contact_person = addr_contact_person;
	}
	public String getAddr_contact_phone() {
		return addr_contact_phone;
	}
	public void setAddr_contact_phone(String addr_contact_phone) {
		this.addr_contact_phone = addr_contact_phone;
	}
	
}
