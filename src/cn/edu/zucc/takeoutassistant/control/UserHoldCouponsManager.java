package cn.edu.zucc.takeoutassistant.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.zucc.takeoutassistant.itf.IEntityManager;
import cn.edu.zucc.takeoutassistant.model.BeanEntity;
import cn.edu.zucc.takeoutassistant.model.BeanUserHoldCoupons;
import cn.edu.zucc.takeoutassistant.util.BaseException;
import cn.edu.zucc.takeoutassistant.util.DBUtil;
import cn.edu.zucc.takeoutassistant.util.DbException;

public class UserHoldCouponsManager implements IEntityManager {

	@Override
	public void add(BeanEntity entity) throws BaseException {
		// TODO ���ô˺���ǰ
		BeanUserHoldCoupons userholdcouopns = (BeanUserHoldCoupons) entity;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO userholdcoupons (user_id, coupon_id, amount) \r\n" + 
					"VALUES (?, ?, 1)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, userholdcouopns.getUser_id());
			pst.setInt(2, userholdcouopns.getCoupon_id());
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
		// FIXME ÿ���µ��������
		// TODO ���ô˺���ʱ ����couponinfo�и��̼ҵ�couponҪ�����ͼ��count�����Ա�  ȷ���µ�coupon����
		BeanUserHoldCoupons userholdcouopns = (BeanUserHoldCoupons) entity;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			// �ж��Ƿ��й� �й��޸�amount(+1) û�й�add
			String sql = "SELECT *\r\n" + 
					"FROM userholdcoupons\r\n" + 
					"WHERE user_id = ? AND coupon_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, userholdcouopns.getUser_id());
			pst.setInt(2, userholdcouopns.getCoupon_id());
			ResultSet rs = pst.executeQuery();
			if (!rs.next()) {
				add(userholdcouopns);
			} else {
				String sql2 = "UPDATE userholdcoupons\r\n" + 
						"SET amount = amount + 1\r\n" + 
						"WHERE user_id = ? AND coupon_id = ?";
				PreparedStatement pst2 = conn.prepareStatement(sql2);
				pst2.setInt(1, userholdcouopns.getUser_id());
				pst2.setInt(2, userholdcouopns.getCoupon_id());
				pst2.execute();
				pst2.close();
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

	@Override
	public void delete(BeanEntity entity) throws BaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void load(BeanEntity entity) throws BaseException {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		UserHoldCouponsManager uhcm = new UserHoldCouponsManager();
		BeanUserHoldCoupons userholdcoupons = new BeanUserHoldCoupons();
		userholdcoupons.setUser_id(1);
		userholdcoupons.setCoupon_id(3);
		try {
			uhcm.update(userholdcoupons);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
