package cn.edu.zucc.takeoutassistant.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.takeoutassistant.itf.IEntityManager;
import cn.edu.zucc.takeoutassistant.model.BeanCoupon;
import cn.edu.zucc.takeoutassistant.model.BeanEntity;
import cn.edu.zucc.takeoutassistant.model.BeanFullReductionScheme;
import cn.edu.zucc.takeoutassistant.util.BaseException;
import cn.edu.zucc.takeoutassistant.util.DBUtil;
import cn.edu.zucc.takeoutassistant.util.DbException;

public class CouponManager implements IEntityManager {

	@Override
	public void add(BeanEntity entity) throws BaseException {
		BeanCoupon coupon = (BeanCoupon) entity;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO couponinfo(shop_id, coupon_amount, coupon_ordered_number_requirement, coupon_start_time, coupon_end_time) \r\n" + 
					"VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, coupon.getShop_id());
			pst.setDouble(2, coupon.getCoupon_amount());
			pst.setInt(3, coupon.getCoupon_ordered_number_requirement());
			pst.setDate(4, new java.sql.Date(coupon.getCoupon_start_time().getTime()));
			System.out.println(coupon.getCoupon_start_time().getTime());
			pst.setDate(5, new java.sql.Date(coupon.getCoupon_end_time().getTime()));
			System.out.println(coupon.getCoupon_end_time().getTime());
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
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE couponinfo\r\n" + 
					"SET coupon_delete_time = NOW()\r\n" + 
					"WHERE coupon_id = ?";
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
		CouponManager cm = new CouponManager();
		BeanCoupon coupon = new BeanCoupon();
		coupon.setCoupon_id(4);
		coupon.setShop_id(1);
		coupon.setCoupon_amount(10);
		coupon.setCoupon_ordered_number_requirement(5);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			coupon.setCoupon_start_time(sdf.parse("2020-07-14"));
			coupon.setCoupon_end_time(sdf.parse("2020-07-15"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			cm.add(coupon);
//			cm.update(coupon);
		} catch (BaseException e) {
			e.printStackTrace();
		}
	}

	public List<BeanCoupon> loadAll(int shop_id) throws BaseException {
		List<BeanCoupon> result = new ArrayList<BeanCoupon>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT coupon_id, coupon_amount, coupon_ordered_number_requirement, coupon_start_time, coupon_end_time\r\n" + 
					"FROM couponinfo\r\n" + 
					"WHERE shop_id = ?\r\n" + 
					"AND coupon_delete_time IS NULL";
			PreparedStatement pst = conn.prepareStatement(sql);	
			pst.setInt(1, shop_id);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanCoupon c = new BeanCoupon();
				c.setCoupon_id(rs.getInt(1));
				c.setCoupon_amount(rs.getDouble(2));
				c.setCoupon_ordered_number_requirement(rs.getInt(3));
				c.setCoupon_start_time(rs.getDate(4));
				c.setCoupon_end_time(rs.getDate(5));
				result.add(c);
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

	public List<BeanCoupon> loadAllAvailable(int user_id, int shop_id) throws BaseException {
		List<BeanCoupon> result = new ArrayList<BeanCoupon>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT user_id, shop_id, coupon_id, coupon_count, discounted_amount\r\n" + 
					"FROM userholdcouponsdetails\r\n" + 
					"WHERE user_id = ?\r\n" + 
					"AND shop_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);	
			pst.setInt(1, user_id);
			pst.setInt(2, shop_id);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanCoupon c = new BeanCoupon();
				c.setUser_id(1);
				c.setShop_id(2);
				c.setCoupon_id(rs.getInt(3));
				c.setCoupon_count(rs.getInt(4));
				c.setCoupon_amount(rs.getDouble(5));
				result.add(c);
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
