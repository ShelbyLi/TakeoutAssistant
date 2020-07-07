package cn.edu.zucc.takeoutassistant.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.edu.zucc.takeoutassistant.itf.IEntityManager;
import cn.edu.zucc.takeoutassistant.model.BeanDeliveryAddr;
import cn.edu.zucc.takeoutassistant.model.BeanEntity;
import cn.edu.zucc.takeoutassistant.util.BaseException;
import cn.edu.zucc.takeoutassistant.util.DBUtil;
import cn.edu.zucc.takeoutassistant.util.DbException;

public class DeliveryAddrManager implements IEntityManager {

	@Override
	public void add(BeanEntity entity) throws BaseException {
		// TODO Auto-generated method stub
		BeanDeliveryAddr deliveryadd = (BeanDeliveryAddr) entity;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO deliveryaddr(user_id, addr_province, addr_city, addr_district, addr_detailed_addr, addr_contact_person, addr_contact_phone) \r\n" + 
					"VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, deliveryadd.getUser_id());
			pst.setString(2, deliveryadd.getAddr_province());
			pst.setString(3, deliveryadd.getAddr_city());
			pst.setString(4, deliveryadd.getAddr_district());
			pst.setString(5, deliveryadd.getAddr_detailed_addr());
			pst.setString(6, deliveryadd.getAddr_contact_person());
			pst.setString(7, deliveryadd.getAddr_contact_phone());
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	@Override
	public void update(BeanEntity entity) throws BaseException {
		// TODO Auto-generated method stub
		// 对外封装为update 实际因有订单记录 为add操作
		BeanDeliveryAddr deliveryadd = (BeanDeliveryAddr) entity;
		add(deliveryadd);
	}

	@Override
	public void delete(BeanEntity entity) throws BaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load(BeanEntity entity) throws BaseException {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DeliveryAddrManager dm = new DeliveryAddrManager();
		BeanDeliveryAddr deliveryaddr = new BeanDeliveryAddr();
		deliveryaddr.setUser_id(1);
		deliveryaddr.setAddr_province("zhejiang");
		deliveryaddr.setAddr_city("hanghzou");
		deliveryaddr.setAddr_district("gongshu");
		deliveryaddr.setAddr_detailed_addr("zucc");
		deliveryaddr.setAddr_contact_person("lxz");
		deliveryaddr.setAddr_contact_phone("19858111581");
		
		try {
			dm.add(deliveryaddr);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
