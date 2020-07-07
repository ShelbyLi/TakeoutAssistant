package cn.edu.zucc.takeoutassistant.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.zucc.takeoutassistant.itf.IPeopleManager;
import cn.edu.zucc.takeoutassistant.model.BeanPeople;
import cn.edu.zucc.takeoutassistant.model.BeanRider;
import cn.edu.zucc.takeoutassistant.util.BaseException;
import cn.edu.zucc.takeoutassistant.util.BusinessException;
import cn.edu.zucc.takeoutassistant.util.DBUtil;
import cn.edu.zucc.takeoutassistant.util.DbException;

public class RiderManager implements IPeopleManager {

	@Override
	public void register(BeanPeople people) throws BaseException {
		// TODO Auto-generated method stub
		BeanRider rider = (BeanRider)people;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT rider_logout_time\r\n" + 
					"FROM riderinfo\r\n" + 
					"WHERE rider_name = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, rider.getRider_name());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {  // ע����
				String sql2 = "UPDATE riderinfo\r\n" + 
						"SET rider_logout_time = null\r\n" + 
						"WHERE rider_name = ?";
				PreparedStatement pst2 = conn.prepareStatement(sql2);
				pst2.setString(1, rider.getRider_name());
				pst2.execute();
				pst2.close();
			} else {
				String sql2 = "INSERT INTO riderinfo(rider_name, rider_pwd, rider_entry_date, rider_identity) \r\n" + 
						"VALUES (?, ?, NOW(), ?)";
				PreparedStatement pst2 = conn.prepareStatement(sql2);
				pst2.setString(1, rider.getRider_name());
				pst2.setString(2, rider.getRider_pwd());
				pst2.setInt(3, BeanRider.newcomer);  // ��ע���û�һ��Ϊ����
				pst2.execute();
				pst2.close();
			}
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
	public BeanPeople login(String name, String pwd) throws BaseException {
		// TODO Auto-generated method stub
		BeanRider result = new BeanRider();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT rider_id, rider_name, rider_pwd, rider_entry_date, rider_identity, rider_total_income, rider_logout_time\r\n" + 
					"FROM riderinfo\r\n" + 
					"WHERE rider_name = ?";
			PreparedStatement pst = conn.prepareCall(sql);
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			if (!rs.next()) {
				throw new BusinessException("���ֲ�����");
			}
			if (rs.getDate(7) != null) {
				throw new BusinessException("������ע��");
			}
			if (!rs.getString("rider_pwd").equals(pwd)) {
				throw new BusinessException("�������");
			}
			// �������� �û���½����Ҫ��ȡ����Ϣ ��ע�͵�
			result.setRider_id(rs.getInt(1));
			result.setRider_name(name);
			result.setRider_pwd(pwd);
			result.setRider_entry_date(rs.getDate(4));
			result.setRider_identity(rs.getInt(5));
			result.setRider_total_income(rs.getDouble(6));
			rs.close();
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
		
		return result;
	}

	@Override
	public void changePwd(String name, String oldPwd, String newPwd) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT rider_pwd\r\n" + 
					"FROM riderinfo\r\n" + 
					"WHERE rider_name = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			java.sql.ResultSet rs = pst.executeQuery();
			if (!rs.next()) {
				throw new BusinessException("�������˺Ų�����");
			}  // ��½��ſɸ����� ��˲������˺Ų����ڵ���� nonono ����Ա��̨����ʱ��Ҫ
			if (!oldPwd.equals(rs.getString(1))) {
				throw new BusinessException("ԭ�������");
			}
			rs.close();
			pst.close();
			
			sql = "UPDATE riderinfo\r\n" + 
					"SET rider_pwd = ?\r\n" + 
					"WHERE rider_name = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, newPwd);
			pst.setString(2, name);
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
	public void logout(String name) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT rider_name, rider_logout_time\r\n" + 
					"FROM riderinfo\r\n" + 
					"WHERE rider_name = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			if (!rs.next()) {
				throw new BusinessException("���ֲ�����");
			}
			if (rs.getDate(2) != null) {
				throw new BusinessException("������ע��");
			}
			rs.close();
			pst.close();
			
			sql = "UPDATE riderinfo\r\n" + 
					"SET rider_logout_time = NOW()\r\n" + 
					"WHERE rider_name = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RiderManager sm = new RiderManager();
		BeanRider rider = new BeanRider();
		rider.setRider_name("testrider");
		rider.setRider_pwd("testpwd");
		try {
			sm.register(rider);
//			rider = (BeanRider) sm.login("testrider", "testpwd");
//			sm.changePwd("testrider", "testpwd", "testpwd2");
//			sm.logout("testrider");
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
