package cn.edu.zucc.takeoutassistant.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.takeoutassistant.itf.IPeopleManager;
import cn.edu.zucc.takeoutassistant.model.BeanPeople;
import cn.edu.zucc.takeoutassistant.model.BeanRider;
import cn.edu.zucc.takeoutassistant.model.BeanShop;
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
			String sql = "SELECT rider_logout_date\r\n" + 
					"FROM riderinfo\r\n" + 
					"WHERE rider_name = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, rider.getRider_name());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {  // 注销过
				String sql2 = "UPDATE riderinfo\r\n" + 
						"SET rider_logout_date = null\r\n" + 
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
				pst2.setInt(3, BeanRider.newcomer);  // 新注册用户一定为新人
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
			String sql = "SELECT rider_id, rider_name, rider_pwd, rider_entry_date, rider_identity, deliver_cnt, total_income, rider_logout_date\r\n" + 
					"FROM riderdetails\r\n" + 
					"WHERE rider_name = ?";
			PreparedStatement pst = conn.prepareCall(sql);
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			if (!rs.next()) {
				throw new BusinessException("骑手不存在");
			}
			if (rs.getDate(8) != null) {
				throw new BusinessException("骑手已注销");
			}
			if (!rs.getString("rider_pwd").equals(pwd)) {
				throw new BusinessException("密码错误");
			}
			// 若后期有 用户登陆后不需要读取的信息 再注释掉
			result.setRider_id(rs.getInt(1));
			result.setRider_name(name);
			result.setRider_pwd(pwd);
			result.setRider_entry_date(rs.getDate(4));
			result.setRider_identity(rs.getInt(5));
			result.setDeliver_cnt(rs.getInt(6));
			result.setRider_total_income(rs.getDouble(7));
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return result;
	}

	@Override
	public void changePwd(int id, String oldPwd, String newPwd) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT rider_pwd\r\n" + 
					"FROM riderinfo\r\n" + 
					"WHERE rider_id = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			java.sql.ResultSet rs = pst.executeQuery();
			if (!rs.next()) {
				throw new BusinessException("该骑手账号不存在");
			}  // 登陆后才可改密码 因此不存在账号不存在的情况 
			if (!oldPwd.equals(rs.getString(1))) {
				throw new BusinessException("原密码错误");
			}
			rs.close();
			pst.close();
			
			sql = "UPDATE riderinfo\r\n" + 
					"SET rider_pwd = ?\r\n" + 
					"WHERE rider_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, newPwd);
			pst.setInt(2, id);
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	@Override
	public void logout(int id) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
//			String sql = "SELECT rider_name, rider_logout_time\r\n" + 
//					"FROM riderinfo\r\n" + 
//					"WHERE rider_id = ?";
//			PreparedStatement pst = conn.prepareStatement(sql);
//			pst.setInt(1, id);
//			ResultSet rs = pst.executeQuery();
//			if (!rs.next()) {
//				throw new BusinessException("骑手不存在");
//			}
//			if (rs.getDate(2) != null) {
//				throw new BusinessException("骑手已注销");
//			}
//			rs.close();
//			pst.close();
			
			String sql = "UPDATE riderinfo\r\n" + 
					"SET rider_logout_date = NOW()\r\n" + 
					"WHERE rider_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
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
//			sm.register(rider);
			rider = (BeanRider) sm.login("testrider", "testpwd");
//			sm.changePwd("testrider", "testpwd", "testpwd2");
//			sm.logout("testrider");
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void updateInfo(BeanPeople people) throws BaseException {
		BeanRider rider = (BeanRider) people;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE riderinfo\r\n" + 
					"SET rider_name=? \r\n" + 
					"WHERE rider_id = ?\r\n";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, rider.getRider_name());
			pst.setInt(2, rider.getRider_id());
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	

	public List<BeanRider> loadAll() throws BaseException {
		List<BeanRider> result = new ArrayList<BeanRider>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT rider_id, rider_name, rider_pwd, rider_entry_date, rider_identity, deliver_cnt, total_income\r\n" + 
					"FROM riderdetails\r\n" + 
					"WHERE rider_logout_date IS NULL";
			PreparedStatement pst = conn.prepareStatement(sql);	
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanRider r = new BeanRider();
				r.setRider_id(rs.getInt(1));
				r.setRider_name(rs.getString(2));
				r.setRider_pwd(rs.getString(3));
				r.setRider_entry_date(rs.getDate(4));
				r.setRider_identity(rs.getInt(5));
				r.setDeliver_cnt(rs.getInt(6));
				r.setRider_total_income(rs.getDouble(7));
				result.add(r);
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

	public List<BeanRider> fuzzySearch(String keyWord) throws BaseException {
		List<BeanRider> result = new ArrayList<BeanRider>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT rider_id, rider_name, rider_pwd, rider_entry_date, rider_identity, deliver_cnt, total_income\r\n" + 
					"FROM riderdetails\r\n" + 
					"WHERE rider_logout_date IS NULL\r\n" + 
					"AND rider_name LIKE ?";
			PreparedStatement pst = conn.prepareStatement(sql);	
			pst.setString(1, "%"+keyWord+"%");
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanRider r = new BeanRider();
				r.setRider_id(rs.getInt(1));
				r.setRider_name(rs.getString(2));
				r.setRider_pwd(rs.getString(3));
				r.setRider_entry_date(rs.getDate(4));
				r.setRider_identity(rs.getInt(5));
				r.setDeliver_cnt(rs.getInt(6));
				r.setRider_total_income(rs.getDouble(7));
				result.add(r);
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
