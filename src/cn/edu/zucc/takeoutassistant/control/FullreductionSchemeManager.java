 package cn.edu.zucc.takeoutassistant.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.takeoutassistant.itf.IEntityManager;
import cn.edu.zucc.takeoutassistant.model.BeanEntity;
import cn.edu.zucc.takeoutassistant.model.BeanFullReductionScheme;
import cn.edu.zucc.takeoutassistant.model.BeanProductCategory;
import cn.edu.zucc.takeoutassistant.util.BaseException;
import cn.edu.zucc.takeoutassistant.util.DBUtil;
import cn.edu.zucc.takeoutassistant.util.DbException;

public class FullreductionSchemeManager implements IEntityManager {

	@Override
	public void add(BeanEntity entity) throws BaseException {
		// TODO Auto-generated method stub
		BeanFullReductionScheme fullreduction = (BeanFullReductionScheme) entity;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO fullreductionscheme(shop_id, fullreduction_amount, fullreduction_discounted_price, fullreduction_can_superimposed_with_coupons) \r\n" + 
					"VALUES (?, ?, ?, ?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, fullreduction.getShop_id());
			pst.setDouble(2, fullreduction.getFullreduction_amount());
			pst.setDouble(3, fullreduction.getFullreduction_discounted_price());
			pst.setInt(4, fullreduction.getFullreduction_can_superimposed_with_coupons());
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
		BeanFullReductionScheme fullreduction = (BeanFullReductionScheme) entity;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE fullreductionscheme\r\n" + 
					"SET fullreduction_amount = ?, fullreduction_discounted_price = ?, fullreduction_can_superimposed_with_coupons = ?\r\n" + 
					"WHERE fullreduction_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setDouble(1, fullreduction.getFullreduction_amount());
			pst.setDouble(2, fullreduction.getFullreduction_discounted_price());
			pst.setInt(3, fullreduction.getFullreduction_can_superimposed_with_coupons());
			pst.setInt(4, fullreduction.getFullreduction_id());
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
	public void delete(int id) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE fullreductionscheme\r\n" + 
					"SET fullreduction_delete_time = NOW()\r\n" + 
					"WHERE fullreduction_id = ?";
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

	public List<BeanFullReductionScheme> loadAll(int shop_id) throws BaseException {
		List<BeanFullReductionScheme> result = new ArrayList<BeanFullReductionScheme>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT fullreduction_id, fullreduction_amount, fullreduction_discounted_price, fullreduction_can_superimposed_with_coupons\r\n" + 
					"FROM fullreductionscheme\r\n" + 
					"WHERE shop_id = ?\r\n" + 
					"AND fullreduction_delete_time IS NULL";
			PreparedStatement pst = conn.prepareStatement(sql);	
			pst.setInt(1, shop_id);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanFullReductionScheme fr = new BeanFullReductionScheme();
				fr.setFullreduction_id(rs.getInt(1));
				fr.setFullreduction_amount(rs.getDouble(2));
				fr.setFullreduction_discounted_price(rs.getDouble(3));
				fr.setFullreduction_can_superimposed_with_coupons(rs.getInt(4));
				result.add(fr);
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


	public static void main(String[] args) {
		BeanFullReductionScheme fullreduction = new BeanFullReductionScheme();
		FullreductionSchemeManager fsm = new FullreductionSchemeManager();
		fullreduction.setFullreduction_id(1);
		fullreduction.setFullreduction_amount(5);
		fullreduction.setShop_id(1);
		fullreduction.setFullreduction_discounted_price(2);
		fullreduction.setFullreduction_can_superimposed_with_coupons(1);
		
		try {
//			fsm.add(fullreduction);
//			fsm.update(fullreduction);
			List<BeanFullReductionScheme> fullreductions = new ArrayList<BeanFullReductionScheme>();
			fullreductions = fsm.loadAll(1);
			for (BeanFullReductionScheme item: fullreductions) {
				System.out.println(item.getFullreduction_amount());
			}
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
