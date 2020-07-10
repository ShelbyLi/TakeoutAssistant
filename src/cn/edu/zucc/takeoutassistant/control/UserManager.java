package cn.edu.zucc.takeoutassistant.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import cn.edu.zucc.takeoutassistant.itf.IPeopleManager;
import cn.edu.zucc.takeoutassistant.model.BeanPeople;
import cn.edu.zucc.takeoutassistant.model.BeanShop;
import cn.edu.zucc.takeoutassistant.model.BeanUser;
import cn.edu.zucc.takeoutassistant.util.BaseException;
import cn.edu.zucc.takeoutassistant.util.BusinessException;
import cn.edu.zucc.takeoutassistant.util.DBUtil;
import cn.edu.zucc.takeoutassistant.util.DbException;

public class UserManager implements IPeopleManager {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserManager um = new UserManager();
		BeanUser user = new BeanUser();
		user.setUser_name("test");
		user.setUser_pwd("testpwd");
		user.setUser_phone_number("12345678901");
		user.setRegistration_time(new java.util.Date());
		user.setUser_is_vip(BeanUser.ISNOTVIP);
		try {
			um.register(user);
//			user = (BeanUser)um.login("test", "testpwd3");
//			System.out.println(user);
//			um.logout("test");
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void register(BeanPeople people) throws BaseException {
		// TODO Auto-generated method stub
		// 先默认输入的数据都符合要求: user_name 唯一; pwd符合规定 等
		BeanUser user = (BeanUser)people;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT user_logout_time\r\n" + 
					"FROM userinfo\r\n" + 
					"WHERE user_name = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUser_name());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {  // 注销过
				String sql2 = "UPDATE userinfo\r\n" + 
						"SET user_logout_time = null\r\n" + 
						"WHERE user_name = ?";
				PreparedStatement pst2 = conn.prepareStatement(sql2);
				pst2.setString(1, user.getUser_name());
				pst2.execute();
				pst2.close();
			} else {
				String sql2 = "INSERT INTO userinfo (user_name, user_gender, user_pwd, user_phone_number, user_mail, user_city, user_registration_time, user_is_vip) \r\n" + 
						"VALUES (?, ?, ?, ?, ?, ?, NOW(), ?);";
				PreparedStatement pst2 = conn.prepareStatement(sql2);
				pst2.setString(1, user.getUser_name());
				pst2.setInt(2, user.getUser_gender());
				pst2.setString(3, user.getUser_pwd());
				pst2.setString(4, user.getUser_phone_number());
				pst2.setString(5, user.getUser_mail());
				pst2.setString(6, user.getUser_city());
//				pst2.setDate(7, new java.sql.Date(user.getRegistration_time().getTime()));
				// vip的功能单独实现  若要在注册时直接注册 也直接调用注册vip的函数
				pst2.setInt(7, user.getUser_is_vip());  //
//				if (user.getUser_is_vip() == 1) {
//					//注册vip
//				}
	//					pst.setInt(8, user.getUser_is_vip());
	//					System.out.println(user.getUser_vip_ddl());
	//					if (user.getUser_vip_ddl() != null) 
	//						pst.setDate(9, new java.sql.Date(user.getUser_vip_ddl().getTime()));
	//					else
					
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
		BeanUser result = new BeanUser();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT user_id, user_name, user_gender, user_pwd, user_phone_number, user_mail, user_city, user_registration_time, user_is_vip, user_vip_ddl, user_logout_time\r\n" + 
					"FROM userinfo\r\n" + 
					"WHERE user_name = ?";
			PreparedStatement pst = conn.prepareCall(sql);
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			if (!rs.next()) {
				throw new BusinessException("用户不存在");
			}
			if (rs.getDate(11) != null) {
				throw new BusinessException("用户已注销");
			}
			if (!rs.getString("user_pwd").equals(pwd)) {
				result = null;
				throw new BusinessException("密码错误");
			}
			// 若后期有 用户登陆后不需要读取的信息 再注释掉
			result.setUser_id(rs.getInt(1));
			result.setUser_name(name);
			result.setUser_gender(rs.getInt(3));
			result.setUser_pwd(pwd);
			result.setUser_phone_number(rs.getString(5));
			result.setUser_mail(rs.getString(6));
			result.setUser_city(rs.getString(7));
			result.setRegistration_time(rs.getDate(8));
			result.setUser_is_vip(rs.getInt(9));
			result.setUser_vip_ddl(rs.getDate(10));
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

//	@Override
	public void changePwd(String name, String oldPwd, String newPwd) throws BaseException {
		// TODO Auto-generated method stub
		// 默认输入符合要求
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT user_pwd\r\n" + 
					"FROM userinfo\r\n" + 
					"WHERE user_name = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			java.sql.ResultSet rs = pst.executeQuery();
			if (!rs.next()) {
				throw new BusinessException("该登录账号不存在");
			}
			if (!oldPwd.equals(rs.getString(1))) {
				throw new BusinessException("原密码错误");
			}
			rs.close();
			pst.close();
			
			sql = "UPDATE userinfo\r\n" + 
					"SET user_pwd = ?\r\n" + 
					"WHERE user_name = ?";
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

//	@Override
	public void logout(String name) throws BaseException{
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT user_name, user_logout_time\r\n" + 
					"FROM userinfo\r\n" + 
					"WHERE user_name = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			if (!rs.next()) {
				throw new BusinessException("用户不存在");
			}
			if (rs.getDate(2) != null) {
				throw new BusinessException("用户已注销");
			}
			rs.close();
			pst.close();
			
			sql = "UPDATE userinfo\r\n" + 
					"SET user_logout_time = NOW()\r\n" + 
					"WHERE user_name = ?";
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

	@Override
	public void changePwd(int id, String oldPwd, String newPwd) throws BaseException {
		Connection conn = null;
		try {
//			conn = DBUtil.getConnection();
//			String sql = "SELECT shop_pwd\r\n" + 
//					"FROM shopinfo\r\n" + 
//					"WHERE shop_id = ?";
//			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
//			pst.setInt(1, id);
//			java.sql.ResultSet rs = pst.executeQuery();
////			if (!rs.next()) {
////				throw new BusinessException("该商家不存在");
////			}
//			rs.next();
//			if (!oldPwd.equals(rs.getString(1))) {
//				throw new BusinessException("原密码错误");
//			}
//			rs.close();
//			pst.close();
//			
//			sql = "UPDATE shopinfo\r\n" + 
//					"SET shop_pwd = ?\r\n" + 
//					"WHERE shop_id = ?";
//			pst = conn.prepareStatement(sql);
//			pst.setString(1, newPwd);
//			pst.setInt(2, id);
//			pst.execute();
//			pst.close();
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
	public void logout(int id) throws BaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateInfo(BeanPeople people) throws BaseException {
		BeanUser user = (BeanUser) people;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE userinfo\r\n" + 
					"SET user_name=?, user_gender=?, user_phone_number=?, user_mail=?, user_city=?, user_is_vip=?, user_vip_ddl=?\r\n" + 
					"WHERE user_id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUser_name());
			pst.setInt(2, user.getUser_gender());
			pst.setString(3, user.getUser_phone_number());
			pst.setString(4, user.getUser_mail());
			pst.setString(5, user.getUser_city());
			pst.setInt(6, user.getUser_is_vip());
			if (user.getUser_vip_ddl() != null) 
				pst.setDate(7, new java.sql.Date(user.getUser_vip_ddl().getTime()));
			else
				pst.setNull(7, Types.DATE);
			pst.setInt(8, user.getUser_id());
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

}
