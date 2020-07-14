package cn.edu.zucc.takeoutassistant.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.zucc.takeoutassistant.itf.IPeopleManager;
import cn.edu.zucc.takeoutassistant.model.BeanAdmin;
import cn.edu.zucc.takeoutassistant.model.BeanPeople;
import cn.edu.zucc.takeoutassistant.util.BaseException;
import cn.edu.zucc.takeoutassistant.util.BusinessException;
import cn.edu.zucc.takeoutassistant.util.DBUtil;
import cn.edu.zucc.takeoutassistant.util.DbException;

public class AdminManager implements IPeopleManager {

	@Override
	public void register(BeanPeople people) throws BaseException {
		// TODO Auto-generated method stub
		BeanAdmin admin = (BeanAdmin)people;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			// 查找是否是注销过的账号
			String sql = "SELECT admin_logout_time\r\n" + 
					"FROM admininfo\r\n" + 
					"WHERE admin_name = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, admin.getAdmin_name());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {  // 注销过
				String sql2 = "UPDATE admininfo\r\n" + 
						"SET admin_logout_time = null\r\n" + 
						"WHERE admin_name = ?";
				PreparedStatement pst2 = conn.prepareStatement(sql2);
				pst2.setString(1, admin.getAdmin_name());
				pst2.execute();
				pst2.close();
			} else {
				String sql2 = "INSERT INTO admininfo(admin_name, admin_pwd) \r\n" + 
						"VALUES (?, ?)";
				PreparedStatement pst2  = conn.prepareStatement(sql2);
				pst2.setString(1, admin.getAdmin_name());
				pst2.setString(2, admin.getAdmin_pwd());
				pst2.execute();
				pst2.close();
			}
			
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
	}

	@Override
	public BeanPeople login(String name, String pwd) throws BaseException {
		// TODO Auto-generated method stub
		BeanAdmin result = new BeanAdmin();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT admin_id, admin_name, admin_pwd, admin_logout_date\r\n" + 
					"FROM admininfo\r\n" + 
					"WHERE admin_name = ?";
			PreparedStatement pst = conn.prepareCall(sql);
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			if (!rs.next()) {
//				throw new BusinessException("管理员不存在");
				// 注册初始管理员
				String sql1 = "INSERT INTO admininfo(admin_name, admin_pwd) \r\n" + 
						"VALUES (?, ?)";
				PreparedStatement pst1 = conn.prepareStatement(sql1);
				pst1.setString(1, name);
				pst1.setString(2, pwd);
				pst1.execute();
				login(name, pwd);
			} else {
				if (rs.getDate(4) != null) {
					throw new BusinessException("管理员已注销");
				}
				if (!rs.getString("admin_pwd").equals(pwd)) {
					throw new BusinessException("密码错误");
				}
			}

			// 若后期有 用户登陆后不需要读取的信息 再注释掉
			result.setAdmin_id(rs.getInt(1));
			result.setAdmin_name(name);
			result.setAdmin_pwd(pwd);
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
	public void changePwd(int id, String oldPwd, String newPwd) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT admin_pwd\r\n" + 
					"FROM admininfo\r\n" + 
					"WHERE admin_id = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			java.sql.ResultSet rs = pst.executeQuery();
//			if (!rs.next()) {
//				throw new BusinessException("该管理员账号不存在");
//			}
			rs.next();
			if (!oldPwd.equals(rs.getString(1))) {
				throw new BusinessException("原密码错误");
			}
			rs.close();
			pst.close();
			
			sql = "UPDATE admininfo\r\n" + 
					"SET admin_pwd = ?\r\n" + 
					"WHERE admin_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, newPwd);
			pst.setInt(2, id);
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
	public void logout(int id) throws BaseException {
		// TODO Auto-generated method stub
//		Connection conn = null;
//		try {
//			conn = DBUtil.getConnection();
//			String sql = "SELECT admin_name, admin_logout_time\r\n" + 
//					"FROM admininfo\r\n" + 
//					"WHERE admin_name = ?";
//			PreparedStatement pst = conn.prepareStatement(sql);
//			pst.setString(1, name);
//			ResultSet rs = pst.executeQuery();
//			if (!rs.next()) {
//				throw new BusinessException("管理员不存在");
//			}
//			if (rs.getDate(2) != null) {
//				throw new BusinessException("管理员已注销");
//			}
//			rs.close();
//			pst.close();
//			
//			sql = "UPDATE admininfo\r\n" + 
//					"SET admin_logout_time = NOW()\r\n" + 
//					"WHERE admin_name = ?";
//			pst = conn.prepareStatement(sql);
//			pst.setString(1, name);
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AdminManager am = new AdminManager();
		BeanAdmin admin = new BeanAdmin();
		admin.setAdmin_name("testadmin");
		admin.setAdmin_pwd("testpwd");
		try {
//			am.register(admin);
			am.login("w", "w");
//			am.changePwd("testadmin", "testpwd", "testpwd2");
//			am.logout("testadmin");
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	


	@Override
	public void updateInfo(BeanPeople people) throws BaseException {
		// TODO Auto-generated method stub
		
	}

}
