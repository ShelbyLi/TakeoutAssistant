package cn.edu.zucc.takeoutassistant.itf;

import cn.edu.zucc.takeoutassistant.model.BeanEntity;
import cn.edu.zucc.takeoutassistant.util.BaseException;

public interface IEntityManager {

	public void add(BeanEntity entity) throws BaseException;
	public void update(BeanEntity entity) throws BaseException;
	public void delete(BeanEntity entity) throws BaseException;
	public void load(BeanEntity entity) throws BaseException;
}
