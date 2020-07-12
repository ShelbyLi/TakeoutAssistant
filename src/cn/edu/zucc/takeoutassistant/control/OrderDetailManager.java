package cn.edu.zucc.takeoutassistant.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.takeoutassistant.itf.IEntityManager;
import cn.edu.zucc.takeoutassistant.model.BeanEntity;
import cn.edu.zucc.takeoutassistant.model.BeanOrderDetail;
import cn.edu.zucc.takeoutassistant.model.BeanOrderForm;
import cn.edu.zucc.takeoutassistant.util.BaseException;
import cn.edu.zucc.takeoutassistant.util.DBUtil;
import cn.edu.zucc.takeoutassistant.util.DbException;

public class OrderDetailManager implements IEntityManager {

	@Override
	public void add(BeanEntity entity) throws BaseException {
		// TODO Auto-generated method stub
		BeanOrderDetail orderdetail = (BeanOrderDetail) entity;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO orderdetail (order_id, product_id, amount, price) \r\n" + 
					"VALUES (?, ?, ?, ?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, orderdetail.getOrder_id());
			pst.setInt(2, orderdetail.getProduct_id());
			pst.setInt(3, orderdetail.getAmount());
			pst.setDouble(4, orderdetail.getPrice());
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
	public void delete(int id) throws BaseException {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OrderDetailManager odm = new OrderDetailManager();
		BeanOrderDetail orderdetail = new BeanOrderDetail();
		orderdetail.setOrder_id(2);
		orderdetail.setProduct_id(2);
		orderdetail.setAmount(2);
		orderdetail.setPrice(27);
		try {
			odm.add(orderdetail);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<BeanOrderDetail> loadAll(int order_id) throws BaseException {
		List<BeanOrderDetail> result = new ArrayList<BeanOrderDetail>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT a.product_id, product_name, amount, price\r\n" + 
					"FROM orderdetail a, productdetails b\r\n" + 
					"WHERE a.product_id = b.product_id\r\n" + 
					"AND order_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);	
			pst.setInt(1, order_id);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanOrderDetail od = new BeanOrderDetail();
				od.setProduct_id(rs.getInt(1));
				od.setProduct_name(rs.getString(2));
				od.setAmount(rs.getInt(3));
				od.setPrice(rs.getDouble(4));
				result.add(od);
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

	public List<BeanOrderDetail> loadAllByUser(int order_id) throws BaseException {
		List<BeanOrderDetail> result = new ArrayList<BeanOrderDetail>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT a.product_id, product_name, amount, price\r\n" + 
					"FROM orderdetail a, productdetails b\r\n" + 
					"WHERE a.product_id = b.product_id\r\n" + 
					"AND order_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);	
			pst.setInt(1, order_id);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanOrderDetail od = new BeanOrderDetail();
				od.setProduct_id(rs.getInt(1));
				od.setProduct_name(rs.getString(2));
				od.setAmount(rs.getInt(3));
				od.setPrice(rs.getDouble(4));
				result.add(od);
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
