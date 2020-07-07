package cn.edu.zucc.takeoutassistant.model;

import java.util.Date;

public class BeanFullReductionScheme extends BeanEntity {

	private int fullreduction_id;
	private int shop_id;
	private double fullreduction_amount;	// Âú¼õ½ð¶î
	private double fullreduction_discounted_price;  // Âú¼õÓÅ»Ý
	private int fullreduction_can_superimposed_with_coupons;
	private Date fuureduction_delete_time;
	
	public static final int cannot_superimosed_with_coupons = 0;
	public static final int can_superimosed_with_coupons = 1;
	
	public int getFullreduction_id() {
		return fullreduction_id;
	}
	public void setFullreduction_id(int fullreduction_id) {
		this.fullreduction_id = fullreduction_id;
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public double getFullreduction_amount() {
		return fullreduction_amount;
	}
	public void setFullreduction_amount(double fullreduction_amount) {
		this.fullreduction_amount = fullreduction_amount;
	}
	public double getFullreduction_discounted_price() {
		return fullreduction_discounted_price;
	}
	public void setFullreduction_discounted_price(double fullreduction_discounted_price) {
		this.fullreduction_discounted_price = fullreduction_discounted_price;
	}
	public int getFullreduction_can_superimposed_with_coupons() {
		return fullreduction_can_superimposed_with_coupons;
	}
	public void setFullreduction_can_superimposed_with_coupons(int fullreduction_can_superimposed_with_coupons) {
		this.fullreduction_can_superimposed_with_coupons = fullreduction_can_superimposed_with_coupons;
	}
	public Date getFuureduction_delete_time() {
		return fuureduction_delete_time;
	}
	public void setFuureduction_delete_time(Date fuureduction_delete_time) {
		this.fuureduction_delete_time = fuureduction_delete_time;
	}
	
	
}
