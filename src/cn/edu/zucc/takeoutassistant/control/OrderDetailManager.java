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
import cn.edu.zucc.takeoutassistant.model.BeanProduct;
import cn.edu.zucc.takeoutassistant.util.BaseException;
import cn.edu.zucc.takeoutassistant.util.BusinessException;
import cn.edu.zucc.takeoutassistant.util.DBUtil;
import cn.edu.zucc.takeoutassistant.util.DbException;

public class OrderDetailManager implements IEntityManager {

	@Override
	public void add(BeanEntity entity) throws BaseException {
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

	}

	@Override
	public void delete(int id) throws BaseException {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OrderDetailManager odm = new OrderDetailManager();
		BeanOrderDetail orderdetail = new BeanOrderDetail();
		orderdetail.setOrder_id(12);
		orderdetail.setProduct_id(7);
//		orderdetail.setAmount(2);
//		orderdetail.setPrice(27);
		try {
//			odm.add(orderdetail);
			odm.plusOne(orderdetail);
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

	
	public void plusOne(BeanOrderDetail orderdetail) throws BaseException {
		Connection conn = null;
		// ���Ƿ��������ļ�¼ �������Ƚ���
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM orderdetail\r\n" + 
					"WHERE order_id = ?\r\n" + 
					"AND product_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, orderdetail.getOrder_id());
			pst.setInt(2, orderdetail.getProduct_id());
			ResultSet rs = pst.executeQuery();
			if (!rs.next()) { // û��
				String sql1 = "INSERT INTO orderdetail (order_id, product_id, amount)\r\n" + 
						"VALUES (?, ?, 1)";
				PreparedStatement pst1 = conn.prepareStatement(sql1);
				pst1.setInt(1, orderdetail.getOrder_id());
				pst1.setInt(2, orderdetail.getProduct_id());
				pst1.execute();
				pst1.close();
			} else {
				String sql2 = "UPDATE orderdetail\r\n" + 
						"SET amount = amount+1\r\n" + 
						"WHERE order_id = ?\r\n" + 
						"AND product_id = ?";
				PreparedStatement pst2 = conn.prepareStatement(sql2);
				pst2.setInt(1, orderdetail.getOrder_id());
				pst2.setInt(2, orderdetail.getProduct_id());
				pst2.execute();
				pst2.close();
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
	}

	public void minusOne(BeanOrderDetail orderdetail) throws BaseException {
		Connection conn = null;
		// �ж϶��������Ƿ�Ϊ0 ���Ƿ��ɾ��
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT amount\r\n" + 
					"FROM usercart\r\n" + 
					"WHERE order_id = ?\r\n" + 
					"AND product_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, orderdetail.getOrder_id());
			pst.setInt(2, orderdetail.getProduct_id());
			ResultSet rs = pst.executeQuery();
			if (!rs.next()) { // û�м�¼1
				throw new BusinessException("���ﳵ��δ��� ����ɾ��!");
//				String sql1 = "INSERT INTO orderdetail (order_id, product_id, amount)\r\n" + 
//						"VALUES (?, ?, 1)";
//				PreparedStatement pst1 = conn.prepareStatement(sql1);
//				pst1.setInt(1, orderdetail.getOrder_id());
//				pst1.setInt(2, orderdetail.getProduct_id());
//				pst1.execute();
//				pst1.close();
			} else { //���Լ���
				if (rs.getInt(1) == 0) {
					throw new BusinessException("���ﳵ��δ��� ����ɾ��!");
				}
				String sql2 = "UPDATE orderdetail\r\n" + 
						"SET amount = amount-1\r\n" + 
						"WHERE order_id = ?\r\n" + 
						"AND product_id = ?";
				PreparedStatement pst2 = conn.prepareStatement(sql2);
				pst2.setInt(1, orderdetail.getOrder_id());
				pst2.setInt(2, orderdetail.getProduct_id());
				pst2.execute();
				pst2.close();
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
	}

	public int searchAmount(int user_id, int product_id) throws BaseException {
		int result = 0;
		Connection conn = null;
		// �ж϶��������Ƿ�Ϊ0 ���Ƿ��ɾ��
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT amount\r\n" + 
					"FROM usercart\r\n" + 
					"WHERE user_id = ?\r\n" + 
					"AND product_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, user_id);
			pst.setInt(2, product_id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
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

	public List<BeanOrderDetail> loadCart(int user_id, int shop_id) throws BaseException {
		List<BeanOrderDetail> result = new ArrayList<BeanOrderDetail>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT order_id, user_id, shop_id, product_id, product_name, amount, product_price, product_discounted_price\r\n" + 
					"FROM usercart\r\n" + 
					"WHERE user_id = ?\r\n" + 
					"AND shop_id = ?\r\n" + 
					"AND amount > 0";
			PreparedStatement pst = conn.prepareStatement(sql);	
			pst.setInt(1, user_id);
			pst.setInt(2, shop_id);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanOrderDetail od = new BeanOrderDetail();
				od.setOrder_id(rs.getInt(1));
				od.setProduct_id(rs.getInt(4));
				od.setProduct_name(rs.getString(5));
				od.setAmount(rs.getInt(6));
				od.setPrice(rs.getDouble(7));
				od.setDiscounted_price(rs.getDouble(8));
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
