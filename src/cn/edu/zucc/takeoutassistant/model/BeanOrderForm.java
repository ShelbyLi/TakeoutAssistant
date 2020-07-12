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
	private String shop_name;
	private String addr_province;
	private String addr_city;
	private String addr_district;
	private String addr_detailed_addr;
	private String addr_contact_person;
	private String addr_contact_phone;
	private double order_actual_vip_amount;
	private double fullreduction_discounted_price;
	private double coupon_amount;
	private double final_amount;
	
	
	public static final int waiting = 0;
	public static final int delivering = 1;
	public static final int arrived = 2;
	public static final int timeout = 3;
	public static final int ordering = -1;
//	public static final int cancled = 3;
	
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
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
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
	public double getOrder_actual_vip_amount() {
		return order_actual_vip_amount;
	}
	public void setOrder_actual_vip_amount(double order_actual_vip_amount) {
		this.order_actual_vip_amount = order_actual_vip_amount;
	}
	public double getFullreduction_discounted_price() {
		return fullreduction_discounted_price;
	}
	public void setFullreduction_discounted_price(double fullreduction_discounted_price) {
		this.fullreduction_discounted_price = fullreduction_discounted_price;
	}
	public double getCoupon_amount() {
		return coupon_amount;
	}
	public void setCoupon_amount(double coupon_amount) {
		this.coupon_amount = coupon_amount;
	}
	public double getFinal_amount() {
		return final_amount;
	}
	public void setFinal_amount(double final_amount) {
		this.final_amount = final_amount;
	}
	
	
}
