package cn.edu.zucc.takeoutassistant.model;

import java.util.Date;

public class BeanDeliveryAddr extends BeanEntity {

	private int address_id;
	private int user_id;
	private String addr_province;
	private String addr_city;
	private String addr_district;
	private String addr_detailed_addr;
	private String addr_contact_person;
	private String addr_contact_phone;
	private Date addr_delete_time;
	
	public int getAddress_id() {
		return address_id;
	}
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
	public Date getAddr_delete_time() {
		return addr_delete_time;
	}
	public void setAddr_delete_time(Date addr_delete_time) {
		this.addr_delete_time = addr_delete_time;
	}
}
