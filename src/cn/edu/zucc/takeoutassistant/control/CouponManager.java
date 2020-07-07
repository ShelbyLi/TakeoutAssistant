package cn.edu.zucc.takeoutassistant.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.edu.zucc.takeoutassistant.itf.IEntityManager;
import cn.edu.zucc.takeoutassistant.model.BeanCoupon;
import cn.edu.zucc.takeoutassistant.model.BeanEntity;
import cn.edu.zucc.takeoutassistant.util.BaseException;
import cn.edu.zucc.takeoutassistant.util.DBUtil;
import cn.edu.zucc.takeoutassistant.util.DbException;

public class CouponManager implements IEntityManager {

	@Override
	public void add(BeanEntity entity) throws BaseException {
		// TODO Auto-generated method stub
		BeanCoupon coupon = (BeanCoupon) entity;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO couponinfo(shop_id, coupon_amount, coupon_ordered_number_requirement, coupon_start_date, coupon_end_date) \r\n" + 
					"VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, coupon.getShop_id());
			pst.setDouble(2, coupon.getCoupon_amount());
			pst.setInt(3, coupon.getCoupon_ordered_number_requirement());
			pst.setDate(4, new java.sql.Date(coupon.getCoupon_start_date().getTime()));
			pst.setDate(5, new java.sql.Date(coupon.getCoupon_end_date().getTime()));
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
		BeanCoupon coupon = (BeanCoupon) entity;
		add(coupon);
//		Connection conn = null;
//		try {
//			conn = DBUtil.getConnection();
//			String sql = "UPDATE couponinfo\r\n" + 
//					"SET coupon_amount = ?, coupon_ordered_number_requirement = ?, coupon_start_date = ?, coupon_end_date = ?\r\n" + 
//					"WHERE coupon_id = ?";
//			PreparedStatement pst = conn.prepareStatement(sql);
//			pst.setDouble(1, coupon.getCoupon_amount());
//			pst.setInt(2, coupon.getCoupon_ordered_number_requirement());
//			pst.setDate(3, new java.sql.Date(coupon.getCoupon_start_date().getTime()));
//			pst.setDate(4, new java.sql.Date(coupon.getCoupon_end_date().getTime()));
//			pst.setInt(5, coupon.getCoupon_id());
//			pst.execute();
//			pst.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			throw new DbException(e);
//		}
//		finally{
//			if(conn!=null)
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		}
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
		CouponManager cm = new CouponManager();
		BeanCoupon coupon = new BeanCoupon();
		coupon.setCoupon_id(4);
		coupon.setShop_id(1);
		coupon.setCoupon_amount(10);
		coupon.setCoupon_ordered_number_requirement(5);
		coupon.setCoupon_start_date(new java.util.Date());
		coupon.setCoupon_end_date(new java.util.Date());
		try {
//			cm.add(coupon);
			cm.update(coupon);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
