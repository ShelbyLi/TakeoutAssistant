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
import cn.edu.zucc.takeoutassistant.util.DBUtil;
import cn.edu.zucc.takeoutassistant.util.DbException;

public class OrderManager implements IEntityManager {

	@Override
	public void add(BeanEntity entity) throws BaseException {
		BeanOrderForm order = (BeanOrderForm) entity;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO orderform (shop_id, fullreduction_id, addr_id, user_id, coupon_id, order_original_amount, order_actual_amount, order_time, order_request_delivery_time, order_status, order_cancle_time) \r\n" + 
					"VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), ?, ?, ?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, order.getShop_id());
			if (order.getFullreduction_id() != 0) pst.setInt(2, order.getFullreduction_id());
			else pst.setNull(2, Types.INTEGER);
			pst.setInt(3, order.getAddr_id());
			pst.setInt(4, order.getUser_id());
			if (order.getCoupon_id() != 0) pst.setInt(5, order.getCoupon_id());
			else pst.setNull(5, Types.INTEGER);
			pst.setDouble(6, order.getOrder_original_amount());
			pst.setDouble(7, order.getOrder_actual_amount());
			if (order.getOrder_request_delivery_time() != null) pst.setTimestamp(8, new Timestamp(order.getOrder_request_delivery_time().getTime()));
			else pst.setNull(8, Types.TIMESTAMP);
			pst.setInt(9, order.getOrder_status());
			if (order.getOrder_cancle_time() != null) pst.setTimestamp(9, new Timestamp(order.getOrder_cancle_time().getTime()));
			else pst.setNull(10, Types.TIMESTAMP);
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
		
		//  TODO ¸üÐÂuserholdcoupons±í
		
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
		order.setOrder_actual_amount(25);
		order.setOrder_status(BeanOrderForm.delivering);
		
		try {
//			om.add(order);
			List<BeanOrderForm> orders = new ArrayList<BeanOrderForm>();
			orders = om.loadAll(1);
			for (BeanOrderForm item: orders) {
				System.out.println(item.getOrder_actual_amount());
				System.out.println(item.getOrder_original_amount());
				System.out.println(item.getOrder_time());
			}
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

}
