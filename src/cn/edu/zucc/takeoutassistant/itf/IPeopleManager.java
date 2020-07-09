package cn.edu.zucc.takeoutassistant.itf;

import cn.edu.zucc.takeoutassistant.model.BeanPeople;
import cn.edu.zucc.takeoutassistant.util.BaseException;

public interface IPeopleManager {

	public void register(BeanPeople people) throws BaseException;
	
	public BeanPeople login(String name, String pwd) throws BaseException;
	
	public void changePwd(int id, String oldPwd, String newPwd) throws BaseException;
	
	public void logout(int id) throws BaseException;
	
	public void updateInfo(BeanPeople people) throws BaseException;
}
