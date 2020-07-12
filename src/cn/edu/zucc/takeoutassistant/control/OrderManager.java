package cn.edu.zucc.takeoutassistant.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.takeoutassistant.itf.IEntityManager;
import cn.edu.zucc.takeoutassistant.model.BeanEntity;
import cn.edu.zucc.takeoutassistant.model.BeanFullReductionScheme;
import cn.edu.zucc.takeoutassistant.model.BeanOrderForm;
import cn.edu.zucc.takeoutassistant.util.BaseException;
import cn.edu.zucc.takeoutassistant.util.BusinessException;
import cn.edu.zucc.takeoutassistant.util.DBUtil;
import cn.edu.zucc.takeoutassistant.util.DbException;

public class OrderManager implements IEntityManager {

	@Override
	public void add(BeanEntity entity) throws BaseException {
		BeanOrderForm order = (BeanOrderForm) entity;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			// 先查看购物车(order_status=-1)是否存在
			String sql = "SELECT *\r\n" + 
					"FROM usercart\r\n" + 
					"WHERE user_id=?\r\n" + 
					"AND shop_id=?\r\n" + 
					"AND product_id IS NOT NULL";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, order.getUser_id());
			pst.setInt(2, order.getShop_id());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				rs.close();
				pst.close();
				throw new BusinessException("该店的购物车已存在");
			}
			
			String sql1 = "INSERT INTO orderform (shop_id, user_id, order_status)\r\n" + 
					"VALUES (?, ?, ?)";
			PreparedStatement pst1 = conn.prepareStatement(sql1);
			pst1.setInt(1, order.getShop_id());
			pst1.setInt(2, order.getUser_id());
			pst1.setInt(3, order.getOrder_status());
//			String sql = "INSERT INTO orderform (shop_id, fullreduction_id, addr_id, user_id, coupon_id, order_original_amount, order_actual_amount, order_time, order_request_delivery_time, order_status, order_cancle_time) \r\n" + 
//			"VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), ?, ?, ?)";
//			pst.setInt(1, order.getShop_id());
//			if (order.getFullreduction_id() != 0) pst.setInt(2, order.getFullreduction_id());
//			else pst.setNull(2, Types.INTEGER);
//			pst.setInt(3, order.getAddr_id());
//			pst.setInt(4, order.getUser_id());
//			if (order.getCoupon_id() != 0) pst.setInt(5, order.getCoupon_id());
//			else pst.setNull(5, Types.INTEGER);
//			pst.setDouble(6, order.getOrder_original_amount());
//			pst.setDouble(7, order.getOrder_actual_amount());
//			if (order.getOrder_request_delivery_time() != null) pst.setTimestamp(8, new Timestamp(order.getOrder_request_delivery_time().getTime()));
//			else pst.setNull(8, Types.TIMESTAMP);
//			pst.setInt(9, order.getOrder_status());
//			if (order.getOrder_cancle_time() != null) pst.setTimestamp(9, new Timestamp(order.getOrder_cancle_time().getTime()));
//			else pst.setNull(10, Types.TIMESTAMP);
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
		
		//  TODO 更新userholdcoupons表
		
	}

	@Override
	public void update(BeanEntity entity) throws BaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) throws BaseException {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		OrderManager om = new OrderManager();
		BeanOrderForm order = new BeanOrderForm();
		order.setShop_id(1);
		order.setAddr_id(1);
		order.setUser_id(1);
		order.setOrder_original_amount(27);
		order.setOrder_actual_amount(11);
		order.setOrder_status(BeanOrderForm.waiting);
		
		try {
			om.add(order);
//			List<BeanOrderForm> orders = new ArrayList<BeanOrderForm>();
//			orders = om.loadAll(1);
//			for (BeanOrderForm item: orders) {
//				System.out.println(item.getOrder_actual_amount());
//				System.out.println(item.getOrder_original_amount());
//				System.out.println(item.getOrder_time());
//			}
//			om.recieved(3);
//			om.arrived(3);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		
	}

	public List<BeanOrderForm> loadAll(int shop_id) throws BaseException {
		List<BeanOrderForm> result = new ArrayList<BeanOrderForm>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT order_id, user_id, fullreduction_id, coupon_id, order_original_amount, order_actual_amount, order_time, order_request_delivery_time, order_status\r\n" + 
					"FROM orderform\r\n" + 
					"WHERE shop_id = ?\r\n" + 
					"AND order_cancle_time IS NULL";
			PreparedStatement pst = conn.prepareStatement(sql);	
			pst.setInt(1, shop_id);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanOrderForm o = new BeanOrderForm();
				o.setOrder_id(rs.getInt(1));
				o.setUser_id(rs.getInt(2));
				o.setFullreduction_id(rs.getInt(3));
				o.setCoupon_id(rs.getInt(4));
				o.setOrder_original_amount(rs.getDouble(5));
				o.setOrder_actual_amount(rs.getDouble(6));
				o.setOrder_time(rs.getTimestamp(7));
				o.setOrder_request_delivery_time(rs.getTimestamp(8));
				o.setOrder_status(rs.getInt(9));
				result.add(o);
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

	public List<BeanOrderForm> loadAllByUser(int user_id) throws BaseException {
		List<BeanOrderForm> result = new ArrayList<BeanOrderForm>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT order_id, shop_name, fullreduction_id, coupon_id, order_original_amount, order_actual_amount, order_time, order_request_delivery_time, order_status, addr_id\r\n" + 
					"FROM orderform a, shopinfo b\r\n" + 
					"WHERE a.shop_id = b.shop_id\r\n" + 
					"AND user_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);	
			pst.setInt(1, user_id);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanOrderForm o = new BeanOrderForm();
				o.setOrder_id(rs.getInt(1));
				o.setShop_name((rs.getString(2)));
				o.setFullreduction_id(rs.getInt(3));
				o.setCoupon_id(rs.getInt(4));
				o.setOrder_original_amount(rs.getDouble(5));
				o.setOrder_actual_amount(rs.getDouble(6));
				o.setOrder_time(rs.getTimestamp(7));
				o.setOrder_request_delivery_time(rs.getTimestamp(8));
				o.setOrder_status(rs.getInt(9));
				o.setAddr_id(rs.getInt(10));
				result.add(o);
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
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE orderform\r\n" + 
					"SET order_status = 2\r\n" + 
					"WHERE order_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, order_id);
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

	public List<BeanOrderForm> loadAllByStatusWaiting() throws BaseException {
		List<BeanOrderForm> result = new ArrayList<BeanOrderForm>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT order_id, shop_name, order_time, order_request_delivery_time, addr_province, addr_city, \r\n" + 
					"addr_district, addr_detailed_addr, addr_contact_person, addr_contact_phone\r\n" + 
					"FROM orderform a, deliveryaddr b, shopinfo c\r\n" + 
					"WHERE a.addr_id = b.addr_id\r\n" + 
					"AND a.shop_id = c.shop_id\r\n" + 
					"AND order_status = 0\r\n" + 
					"AND order_cancle_time IS NULL";
			PreparedStatement pst = conn.prepareStatement(sql);	
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanOrderForm o = new BeanOrderForm();
				o.setOrder_id(rs.getInt(1));
				o.setShop_name((rs.getString(2)));
				o.setOrder_time(rs.getTimestamp(3));
				o.setOrder_request_delivery_time(rs.getTimestamp(4));
				o.setAddr_province(rs.getString(5));
				o.setAddr_city(rs.getString(6));
				o.setAddr_district(rs.getString(7));
				o.setAddr_detailed_addr(rs.getString(8));
				o.setAddr_contact_person(rs.getString(9));
				o.setAddr_contact_phone(rs.getString(10));
				result.add(o);
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

	public void recieved(int order_id) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE orderform\r\n" + 
					"SET order_status = 1\r\n" + 
					"WHERE order_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, order_id);
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

	
	public void updateShopId(int user_id, int shop_id) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			// 先查看购物车(order_status=-1) 中是否有商品存在 有则清空
			String sql = "SELECT *\r\n" + 
					"FROM usercart\r\n" + 
					"WHERE user_id=?\r\n" + 
					"AND product_id IS NOT NULL";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, user_id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) { //购物车有商品  清空
				String sql1 = "DELETE\r\n" + 
						"FROM orderdetail\r\n" + 
						"WHERE order_id = ?";
				PreparedStatement pst1 = conn.prepareStatement(sql1);
				pst1.execute();
				pst1.close();
			}
			
			String sql2 = "UPDATE orderform\r\n" + 
					"SET shop_id = ?\r\n" + 
					"WHERE user_id = ?";
			PreparedStatement pst2 = conn.prepareStatement(sql2);
			pst2.setInt(1, shop_id);
			pst2.setInt(2, user_id);
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

	public int getCartId(int user_id, int shop_id) throws BaseException {
		int result = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT order_id\r\n" + 
					"FROM orderform\r\n" + 
					"WHERE user_id = ?\r\n" + 
					"AND shop_id = ?\r\n" + 
					"AND order_status = -1";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, user_id);
			pst.setInt(2, shop_id);
			ResultSet rs = pst.executeQuery();
			rs.next();
			result = rs.getInt(1);
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
		return result;
	}

}
