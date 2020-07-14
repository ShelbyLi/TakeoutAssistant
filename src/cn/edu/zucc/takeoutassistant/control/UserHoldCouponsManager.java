package cn.edu.zucc.takeoutassistant.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.takeoutassistant.itf.IEntityManager;
import cn.edu.zucc.takeoutassistant.model.BeanCoupon;
import cn.edu.zucc.takeoutassistant.model.BeanEntity;
import cn.edu.zucc.takeoutassistant.model.BeanProductCategory;
import cn.edu.zucc.takeoutassistant.model.BeanUserHoldCoupons;
import cn.edu.zucc.takeoutassistant.util.BaseException;
import cn.edu.zucc.takeoutassistant.util.DBUtil;
import cn.edu.zucc.takeoutassistant.util.DbException;

public class UserHoldCouponsManager implements IEntityManager {

	@Override
	public void add(BeanEntity entity) throws BaseException {
		// TODO 调用此函数前
//		BeanUserHoldCoupons userholdcouopns = (BeanUserHoldCoupons) entity;
//		Connection conn = null;
//		try {
//			conn = DBUtil.getConnection();
//			String sql = "INSERT INTO userholdcoupons (user_id, coupon_id, amount) \r\n" + 
//					"VALUES (?, ?, 1)";
//			PreparedStatement pst = conn.prepareStatement(sql);
//			pst.setInt(1, userholdcouopns.getUser_id());
//			pst.setInt(2, userholdcouopns.getCoupon_id());
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
	public void update(BeanEntity entity) throws BaseException {
		// FIXME 每次下单后需更新
		// TODO 调用此函数时 先让couponinfo中该商家的coupon要求和视图中count数量对比  确定新的coupon类型
//		BeanUserHoldCoupons userholdcouopns = (BeanUserHoldCoupons) entity;
//		Connection conn = null;
//		try {
//			conn = DBUtil.getConnection();
//			// 判断是否有过 有过修改amount(+1) 没有过add
//			String sql = "SELECT *\r\n" + 
//					"FROM userholdcoupons\r\n" + 
//					"WHERE user_id = ? AND coupon_id = ?";
//			PreparedStatement pst = conn.prepareStatement(sql);
//			pst.setInt(1, userholdcouopns.getUser_id());
//			pst.setInt(2, userholdcouopns.getCoupon_id());
//			ResultSet rs = pst.executeQuery();
//			if (!rs.next()) {
//				add(userholdcouopns);
//			} else {
//				String sql2 = "UPDATE userholdcoupons\r\n" + 
//						"SET amount = amount + 1\r\n" + 
//						"WHERE user_id = ? AND coupon_id = ?";
//				PreparedStatement pst2 = conn.prepareStatement(sql2);
//				pst2.setInt(1, userholdcouopns.getUser_id());
//				pst2.setInt(2, userholdcouopns.getCoupon_id());
//				pst2.execute();
//				pst2.close();
//			}
//			rs.close();
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
		UserHoldCouponsManager uhcm = new UserHoldCouponsManager();
		BeanUserHoldCoupons userholdcoupons = new BeanUserHoldCoupons();
//		userholdcoupons.setUser_id(1);
//		userholdcoupons.setCoupon_id(3);
		try {
			uhcm.update(12, 1);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public List<BeanUserHoldCoupons> loadAll(int user_id) throws BaseException {
		List<BeanUserHoldCoupons> result = new ArrayList<BeanUserHoldCoupons>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT a.coupon_id, amount, shop_name, coupon_amount, coupon_ordered_number_requirement, coupon_start_time, coupon_end_time\r\n" + 
					"FROM userholdcoupons a, couponinfo b, shopinfo c\r\n" + 
					"WHERE a.coupon_id = b.coupon_id\r\n" + 
					"AND b.shop_id = c.shop_id\r\n" + 
					"AND user_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);	
			pst.setInt(1, user_id);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanUserHoldCoupons uhc = new BeanUserHoldCoupons();
				uhc.setCoupon_id(rs.getInt(1));
				uhc.setAmount(rs.getInt(2));
				uhc.setShop_name(rs.getString(3));
				uhc.setCoupon_amount(rs.getDouble(4));
				uhc.setCoupon_ordered_number_requirement(rs.getInt(5));
				uhc.setCoupon_start_time(rs.getDate(6));
				uhc.setCoupon_end_time(rs.getDate(7));
				result.add(uhc);
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}

	public void update(int user_id, int shop_id) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			// 选择可以加优惠券的coupon_id
			String sql = "SELECT coupon_id\r\n" + 
					"FROM usercanhavacoupon\r\n" + 
					"WHERE user_id = ?\r\n" + 
					"AND shop_id = ?\r\n" + 
					"AND can_have_coupon";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, user_id);
			pst.setInt(2, shop_id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// 判断是否有过 有过修改amount(+1) 没有过add
				String sql1 = "SELECT *\r\n" + 
						"FROM userholdcoupons\r\n" + 
						"WHERE user_id = ? AND coupon_id = ?";
				PreparedStatement pst1 = conn.prepareStatement(sql1);
				pst1.setInt(1, user_id);
				pst1.setInt(2, rs.getInt(1));
				ResultSet rs1 = pst1.executeQuery();
				if (!rs1.next()) {
					String sql3 = "INSERT INTO userholdcoupons (user_id, coupon_id, amount) \r\n" + 
							"VALUES (?, ?, 1)";
					PreparedStatement pst3 = conn.prepareStatement(sql3);
					pst3.setInt(1, user_id);
					pst3.setInt(2, rs.getInt(1));
					pst3.execute();
				} else {
					String sql2 = "UPDATE userholdcoupons\r\n" + 
					"SET amount = amount + 1\r\n" + 
					"WHERE coupon_id = ?\r\n" + 
					"AND user_id = ?";
					PreparedStatement pst2 = conn.prepareStatement(sql2);
					pst2.setInt(1, rs.getInt(1));
					pst2.setInt(2, user_id);
					pst2.execute();
					pst2.close();
				}
				
				
			}
			rs.close();
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
