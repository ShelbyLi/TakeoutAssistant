package cn.edu.zucc.takeoutassistant.model;

import java.util.Date;

public class BeanEvaluate extends BeanEntity {

	private int user_id;
	private int order_id;
	private String evaluate_content;
	private Date evaluate_date;
	private int score;
	private byte[] evaluate_photo;
	// FIXME 学关于图片的处理
	
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
	public String getEvaluate_content() {
		return evaluate_content;
	}
	public void setEvaluate_content(String evaluate_content) {
		this.evaluate_content = evaluate_content;
	}
	public Date getEvaluate_date() {
		return evaluate_date;
	}
	public void setEvaluate_date(Date evaluate_date) {
		this.evaluate_date = evaluate_date;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public byte[] getEvaluate_photo() {
		return evaluate_photo;
	}
	public void setEvaluate_photo(byte[] evaluate_photo) {
		this.evaluate_photo = evaluate_photo;
	}
	
}
