package cn.edu.zucc.takeoutassistant.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.takeoutassistant.itf.IEntityManager;
import cn.edu.zucc.takeoutassistant.model.BeanAdmin;
import cn.edu.zucc.takeoutassistant.model.BeanDeliveryAddr;
import cn.edu.zucc.takeoutassistant.model.BeanEntity;
import cn.edu.zucc.takeoutassistant.model.BeanFullReductionScheme;
import cn.edu.zucc.takeoutassistant.util.BaseException;
import cn.edu.zucc.takeoutassistant.util.DBUtil;
import cn.edu.zucc.takeoutassistant.util.DbException;

public class DeliveryAddrManager implements IEntityManager {

	@Override
	public void add(BeanEntity entity) throws BaseException {
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
	public void delete(int id) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE deliveryaddr\r\n" + 
					"SET addr_delete_time = NOW()\r\n" + 
					"WHERE addr_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
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

	public BeanDeliveryAddr search(int addr_id) throws BaseException {
		BeanDeliveryAddr result = new BeanDeliveryAddr();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT user_id, addr_province, addr_city, addr_district, addr_detailed_addr, addr_contact_person, addr_contact_phone\r\n" + 
					"FROM deliveryaddr\r\n" + 
					"WHERE addr_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, addr_id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				result.setUser_id(rs.getInt(1));
				result.setAddr_province(rs.getString(2));
				result.setAddr_city(rs.getString(3));
				result.setAddr_district(rs.getString(4));
				result.setAddr_detailed_addr(rs.getString(5));
				result.setAddr_contact_person(rs.getString(6));
				result.setAddr_contact_phone(rs.getString(7));
			}
			pst.execute();
			pst.close();
		} catch (SQLException e) {
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
		return result;
	}

	public List<BeanDeliveryAddr> loadAll(int user_id) throws BaseException {
		List<BeanDeliveryAddr> result = new ArrayList<BeanDeliveryAddr>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT addr_id, user_id, addr_province, addr_city, addr_district, addr_detailed_addr, addr_contact_person, addr_contact_phone\r\n" + 
					"FROM deliveryaddr\r\n" + 
					"WHERE user_id = ?\r\n" + 
					"AND addr_delete_time IS NULL";
			PreparedStatement pst = conn.prepareStatement(sql);	
			pst.setInt(1, user_id);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanDeliveryAddr a = new BeanDeliveryAddr();
				a.setAddress_id(rs.getInt(1));
				a.setUser_id(rs.getInt(2));
				a.setAddr_province(rs.getString(3));
				a.setAddr_city(rs.getString(4));
				a.setAddr_district(rs.getString(5));
				a.setAddr_detailed_addr(rs.getString(6));
				a.setAddr_contact_person(rs.getString(7));
				a.setAddr_contact_phone(rs.getString(8));
				result.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return result;
	}

//	public List<BeanDeliveryAddr> fuzzySearch(String keyWord, int user_id) throws BaseException {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
