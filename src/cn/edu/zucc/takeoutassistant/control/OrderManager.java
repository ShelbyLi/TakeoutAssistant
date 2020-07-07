package cn.edu.zucc.takeoutassistant.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import cn.edu.zucc.takeoutassistant.itf.IEntityManager;
import cn.edu.zucc.takeoutassistant.model.BeanEntity;
import cn.edu.zucc.takeoutassistant.model.BeanOrderForm;
import cn.edu.zucc.takeoutassistant.util.BaseException;
import cn.edu.zucc.takeoutassistant.util.DBUtil;
import cn.edu.zucc.takeoutassistant.util.DbException;

public class OrderManager implements IEntityManager {

	@Override
	public void add(BeanEntity entity) throws BaseException {
		// TODO Auto-generated method stub
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
			pst.setDouble(6, order.getOrder_original_amout());
			pst.setDouble(7, order.getOrder_actul_amount());
			if (order.getOrder_request_delivery_time() != null) pst.setTimestamp(8, new Timestamp(order.getOrder_request_delivery_time().getTime()));
			else pst.setNull(8, Types.TIMESTAMP);
			pst.setInt(9, order.getOrder_statuse());
			if (order.getOrder_cancle_time() != null) pst.setTimestamp(9, new Timestamp(order.getOrder_cancle_time().getTime()));
			else pst.setNull(10, Types.TIMESTAMP);
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
		OrderManager om = new OrderManager();
		BeanOrderForm order = new BeanOrderForm();
		order.setShop_id(1);
		order.setAddr_id(1);
		order.setUser_id(1);
		order.setOrder_original_amout(27);
		order.setOrder_actul_amount(25);
		order.setOrder_statuse(BeanOrderForm.delivering);
		
		try {
			om.add(order);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
