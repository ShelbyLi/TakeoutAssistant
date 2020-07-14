package cn.edu.zucc.takeoutassistant.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.takeoutassistant.itf.IEntityManager;
import cn.edu.zucc.takeoutassistant.model.BeanEntity;
import cn.edu.zucc.takeoutassistant.model.BeanOrderForm;
import cn.edu.zucc.takeoutassistant.model.BeanRider;
import cn.edu.zucc.takeoutassistant.model.BeanRiderDeliverOrder;
import cn.edu.zucc.takeoutassistant.util.BaseException;
import cn.edu.zucc.takeoutassistant.util.DBUtil;
import cn.edu.zucc.takeoutassistant.util.DbException;

public class RiderDeliverOrderManager implements IEntityManager {

	@Override
	public void add(BeanEntity entity) throws BaseException {
		// TODO 骑手接单 只添加前2个字段  时间 用户评分和收入为null
		BeanRiderDeliverOrder riderdeliverorder = (BeanRiderDeliverOrder) entity;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO riderdeliverorder (order_id, rider_id) \r\n" + 
					"VALUES (?, ?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, riderdeliverorder.getOrder_id());
			pst.setInt(2, riderdeliverorder.getRider_id());
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

	@Override
	public void update(BeanEntity entity) throws BaseException {
		// TODO 更新后面3个字段. 送达后即更新 未评价  用户评价后再更新
//		BeanRiderDeliverOrder riderdeliverorder = (BeanRiderDeliverOrder) entity;
//		Connection conn = null;
//		try {
//			conn = DBUtil.getConnection();
//			String sql = "UPDATE riderdeliverorder\r\n" + 
//					"SET deliver_time=NOW(), deliver_user_rate=?";
//			PreparedStatement pst = conn.prepareStatement(sql);
//			pst.setInt(1, riderdeliverorder.getDeliver_user_rate());
//			pst.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new DbException(e);
//		}
//		finally{
//			if(conn!=null)
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//		}
	}

	@Override
	public void delete(int id) throws BaseException {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		RiderDeliverOrderManager rdom = new RiderDeliverOrderManager();
		BeanRiderDeliverOrder riderdeliverorder = new BeanRiderDeliverOrder();
		riderdeliverorder.setOrder_id(2);
		riderdeliverorder.setRider_id(1);
		riderdeliverorder.setDeliver_user_rate(0);
		riderdeliverorder.setDeliver_single_income(3);
		try {
//			rdom.add(riderdeliverorder);
			List<BeanRiderDeliverOrder> riders = new ArrayList<BeanRiderDeliverOrder>();
			riders = rdom.loadAll(2, 1);
			for (BeanRiderDeliverOrder item: riders) {
				System.out.println(item.getAddr_city());
			}
		} catch (BaseException e) {
			e.printStackTrace();
		}
	}

	public List<BeanRiderDeliverOrder> loadAll(int rider_id, int order_status) throws BaseException {
		List<BeanRiderDeliverOrder> result = new ArrayList<BeanRiderDeliverOrder>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = null;
			PreparedStatement pst = null;
//			if (rider_id == 0) { //不要管骑手信息 全部加载
//				sql="SELECT order_id, deliver_time, deliver_user_rate, push_money, shop_id, order_time, order_request_delivery_time, order_status,\r\n" + 
//						"addr_id, addr_province, addr_city, addr_district, addr_detailed_addr, addr_contact_person, addr_contact_phone\r\n" + 
//						"FROM riderdeliverorderdetails\r\n" + 
//						"WHERE order_status = ?";
//				pst = conn.prepareStatement(sql);	
//				pst.setInt(1, order_status);
//			} else {
				sql="SELECT order_id, deliver_time, deliver_user_rate, push_money, shop_id, order_time, order_request_delivery_time, order_status,\r\n" + 
						"addr_id, addr_province, addr_city, addr_district, addr_detailed_addr, addr_contact_person, addr_contact_phone\r\n" + 
						"FROM riderdeliverorderdetails\r\n" + 
						"WHERE rider_id = ?\r\n" + 
						"AND order_status = ?";
				pst = conn.prepareStatement(sql);	
				pst.setInt(1, rider_id);
				pst.setInt(2, order_status);
//			}
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				BeanRiderDeliverOrder rdo = new BeanRiderDeliverOrder();
				rdo.setOrder_id(rs.getInt(1));
				rdo.setDeliver_time(rs.getTimestamp(2));
				rdo.setDeliver_user_rate(rs.getInt(3));
				rdo.setPush_money(rs.getDouble(4));
				rdo.setShop_id(rs.getInt(5));
				rdo.setOrder_time(rs.getTimestamp(6));
				rdo.setOrder_request_delivery_time(rs.getTimestamp(7));
				rdo.setOrder_status(rs.getInt(8));
				rdo.setAddr_id(rs.getInt(9));
				rdo.setAddr_province(rs.getString(10));
				rdo.setAddr_city(rs.getString(11));
				rdo.setAddr_district(rs.getString(12));
				rdo.setAddr_detailed_addr(rs.getString(13));
				rdo.setAddr_contact_person(rs.getString(14));
				rdo.setAddr_contact_phone(rs.getString(15));
				result.add(rdo);
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

	public void arrived(int order_id) throws BaseException {
		// TODO 更新后面3个字段. 送达后即更新 未评价  用户评价后再更新
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE riderdeliverorder\r\n" + 
					"SET deliver_time=NOW()\r\n" + 
					"WHERE order_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, order_id);
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

	public void userRate(int order_id, int user_rate) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE riderdeliverorder\r\n" + 
					"SET deliver_user_rate = ?\r\n" + 
					"WHERE order_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, user_rate);
			pst.setInt(2, order_id);
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
}
