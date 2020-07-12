package cn.edu.zucc.takeoutassistant.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
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
					"AND shop_id=?\r\n";
//					"AND product_id IS NOT NULL";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, order.getUser_id());
			pst.setInt(2, order.getShop_id());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				rs.close();
				pst.close();
//				throw new BusinessException("该店的购物车已存在");
				System.out.println("该店的购物车已存在");  //异常在外调用的函数较麻烦..
			} else {
				String sql1 = "INSERT INTO orderform (shop_id, user_id, order_status)\r\n" + 
						"VALUES (?, ?, ?)";
				PreparedStatement pst1 = conn.prepareStatement(sql1);
				pst1.setInt(1, order.getShop_id());
				pst1.setInt(2, order.getUser_id());
				pst1.setInt(3, order.getOrder_status());
				pst1.execute();
				pst1.close();
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
//		order.setAddr_id(1);
		order.setUser_id(12);
//		order.setOrder_original_amount(27);
//		order.setOrder_actual_amount(11);
		order.setOrder_status(BeanOrderForm.ordering);
		
		try {
//			om.add(order);
//			List<BeanOrderForm> orders = new ArrayList<BeanOrderForm>();
//			orders = om.loadAll(1);
//			for (BeanOrderForm item: orders) {
//				System.out.println(item.getOrder_actual_amount());
//				System.out.println(item.getOrder_original_amount());
//				System.out.println(item.getOrder_time());
//			}
//			om.recieved(3);
//			om.arrived(3);
			om.SearchCheckOutInfo(12, 1);
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

	
//	public void updateShopId(int user_id, int shop_id) throws BaseException {
//		Connection conn = null;
//		try {
//			conn = DBUtil.getConnection();
//			// 先查看购物车(order_status=-1) 中是否有商品存在 有则清空
//			String sql = "SELECT *\r\n" + 
//					"FROM usercart\r\n" + 
//					"WHERE user_id=?\r\n" + 
//					"AND product_id IS NOT NULL";
//			PreparedStatement pst = conn.prepareStatement(sql);
//			pst.setInt(1, user_id);
//			ResultSet rs = pst.executeQuery();
//			if (rs.next()) { //购物车有商品  清空
//				String sql1 = "DELETE\r\n" + 
//						"FROM orderdetail\r\n" + 
//						"WHERE order_id = ?";
//				PreparedStatement pst1 = conn.prepareStatement(sql1);
//				pst1.execute();
//				pst1.close();
//			}
//			
//			String sql2 = "UPDATE orderform\r\n" + 
//					"SET shop_id = ?\r\n" + 
//					"WHERE user_id = ?";
//			PreparedStatement pst2 = conn.prepareStatement(sql2);
//			pst2.setInt(1, shop_id);
//			pst2.setInt(2, user_id);
//			pst2.execute();
//			pst2.close();
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
//		
//	}

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

	public double searchOriginalAmount(int user_id, int shop_id) throws BaseException {
		double result = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT original_amount\r\n" + 
					"FROM usercartcalculate1\r\n" + 
					"WHERE user_id = ?\r\n" + 
					"AND shop_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, user_id);
			pst.setInt(2, shop_id);
			ResultSet rs = pst.executeQuery();
			rs.next();
			result = rs.getDouble(1);
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

	public void updateFullreductionID(int user_id, int shop_id, int fullreduction_id) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE orderform\r\n" + 
					"SET fullreduction_id = ?\r\n" + 
					"WHERE user_id = ?\r\n" + 
					"AND shop_id = ?\r\n" + 
					"AND order_status = -1";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, fullreduction_id);
			pst.setInt(2, user_id);
			pst.setInt(3, shop_id);
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

	public void updateAddrID(int user_id, int shop_id, int addr_id) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE orderform\r\n" + 
					"SET addr_id = ?\r\n" + 
					"WHERE user_id = ?\r\n" + 
					"AND shop_id = ?\r\n" + 
					"AND order_status = -1";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, addr_id);
			pst.setInt(2, user_id);
			pst.setInt(3, shop_id);
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

	public void updateCouponId(int user_id, int shop_id, int coupon_id) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE orderform\r\n" + 
					"SET coupon_id = ?\r\n" + 
					"WHERE user_id = ?\r\n" + 
					"AND shop_id = ?\r\n" + 
					"AND order_status = -1";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, coupon_id);
			pst.setInt(2, user_id);
			pst.setInt(3, shop_id);
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

	public void updateRequestDeliveryTime(int user_id, int shop_id, Date order_request_delivery_time) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE orderform\r\n" + 
					"SET order_request_delivery_time = ?\r\n" + 
					"WHERE user_id = ?\r\n" + 
					"AND shop_id = ?\r\n" + 
					"AND order_status = -1";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setTimestamp(1, new java.sql.Timestamp(order_request_delivery_time.getTime()));
			pst.setInt(2, user_id);
			pst.setInt(3, shop_id);
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

	public void ordered(int user_id, int shop_id) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE orderform\r\n" + 
					"SET order_status = 0\r\n" + 
					"WHERE user_id = ?\r\n" + 
					"AND shop_id = ?\r\n" + 
					"AND order_status = -1";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, user_id);
			pst.setInt(2, shop_id);
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

	
	public BeanOrderForm SearchCheckOutInfo(int user_id, int shop_id) throws BaseException {
		BeanOrderForm order = new BeanOrderForm();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT order_id, user_id, shop_id, fullreduction_discounted_price, coupon_amount, original_amount, actual_amount, actual_vip_amount, final_amount\r\n" + 
					"FROM usercartcalculate4\r\n" + 
					"WHERE user_id = ?\r\n" + 
					"AND shop_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, user_id);
			pst.setInt(2, shop_id);
			ResultSet rs = pst.executeQuery();
			rs.next();
			order.setOrder_id(rs.getInt(1));
			order.setUser_id(rs.getInt(2));
			order.setShop_id(rs.getInt(3));
			order.setFullreduction_discounted_price(rs.getDouble(4));
			order.setCoupon_amount(rs.getDouble(5));
			order.setOrder_original_amount(rs.getDouble(6));
			order.setOrder_actual_amount(rs.getDouble(7));
			order.setOrder_actual_vip_amount(rs.getDouble(8));
			order.setFinal_amount(rs.getDouble(9));
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
		return order;
	}

	
}
