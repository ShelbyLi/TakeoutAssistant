package cn.edu.zucc.takeoutassistant.model;

import java.util.Date;

public class BeanUser extends BeanPeople {

	private int user_id;
	private int order_id;
	private String user_name;
	private int user_gender;
	private String user_pwd;
	private String user_phone_number;
	private String user_mail;
	private String user_city;
	private Date registration_time;
	private int user_is_vip;
	private Date user_vip_ddl;
	private Date user_logout_time;
	
	public static final int MALE = 0;
	public static final int FEMALE = 1;
	public static final int ISNOTVIP = 0;
	public static final int ISVIP = 1;
	
	public Date getUser_logout_time() {
		return user_logout_time;
	}
	public void setUser_logout_time(Date user_logout_time) {
		this.user_logout_time = user_logout_time;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getUser_gender() {
		return user_gender;
	}
	public void setUser_gender(int user_gender) {
		this.user_gender = user_gender;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_phone_number() {
		return user_phone_number;
	}
	public void setUser_phone_number(String user_phone_number) {
		this.user_phone_number = user_phone_number;
	}
	public String getUser_mail() {
		return user_mail;
	}
	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}
	public String getUser_city() {
		return user_city;
	}
	public void setUser_city(String user_city) {
		this.user_city = user_city;
	}
	public Date getRegistration_time() {
		return registration_time;
	}
	public void setRegistration_time(Date registration_time) {
		this.registration_time = registration_time;
	}
	public int getUser_is_vip() {
		return user_is_vip;
	}
	public void setUser_is_vip(int user_is_vip) {
		this.user_is_vip = user_is_vip;
	}
	public Date getUser_vip_ddl() {
		return user_vip_ddl;
	}
	public void setUser_vip_ddl(Date user_vip_ddl) {
		this.user_vip_ddl = user_vip_ddl;
	}
	
	
}
