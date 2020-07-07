package cn.edu.zucc.takeoutassistant.model;

import java.util.Date;

public class BeanAdmin extends BeanPeople {

	private int admin_id;
	private String admin_name;
	private String admin_pwd;
	private Date admin_logout_time;
	
	public Date getAdmin_logout_time() {
		return admin_logout_time;
	}
	public void setAdmin_logout_time(Date admin_logout_time) {
		this.admin_logout_time = admin_logout_time;
	}
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getAdmin_pwd() {
		return admin_pwd;
	}
	public void setAdmin_pwd(String admin_pwd) {
		this.admin_pwd = admin_pwd;
	}
	
}
