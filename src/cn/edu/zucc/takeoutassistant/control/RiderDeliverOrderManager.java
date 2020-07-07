package cn.edu.zucc.takeoutassistant.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.edu.zucc.takeoutassistant.itf.IEntityManager;
import cn.edu.zucc.takeoutassistant.model.BeanEntity;
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
		// TODO 更新后面3个字段. 送达后即更新 未评价默认好评  用户评价后再更新
		BeanRiderDeliverOrder riderdeliverorder = (BeanRiderDeliverOrder) entity;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE riderdeliverorder\r\n" + 
					"SET deliver_time=NOW(), deliver_user_rate=?, deliver_single_income=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, riderdeliverorder.getDeliver_user_rate());
			pst.setDouble(2, riderdeliverorder.getDeliver_single_income());
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
	public void delete(BeanEntity entity) throws BaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void load(BeanEntity entity) throws BaseException {
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
			rdom.add(riderdeliverorder);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
