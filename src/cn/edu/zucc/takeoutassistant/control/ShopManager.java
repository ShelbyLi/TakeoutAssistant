package cn.edu.zucc.takeoutassistant.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.zucc.takeoutassistant.itf.IPeopleManager;
import cn.edu.zucc.takeoutassistant.model.BeanPeople;
import cn.edu.zucc.takeoutassistant.model.BeanShop;
import cn.edu.zucc.takeoutassistant.util.BaseException;
import cn.edu.zucc.takeoutassistant.util.BusinessException;
import cn.edu.zucc.takeoutassistant.util.DBUtil;
import cn.edu.zucc.takeoutassistant.util.DbException;

public class ShopManager implements IPeopleManager {

	
	@Override
	public void register(BeanPeople people) throws BaseException {
		BeanShop shop = (BeanShop)people;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT shop_logout_time\r\n" + 
					"FROM shopinfo\r\n" + 
					"WHERE shop_name = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, shop.getShop_name());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {  // 注销过
				String sql2 = "UPDATE shopinfo\r\n" + 
						"SET shop_logout_time = null\r\n" + 
						"WHERE shop_name = ?";
				PreparedStatement pst2 = conn.prepareStatement(sql2);
				pst2.setString(1, shop.getShop_name());
				pst2.execute();
				pst2.close();
			} else {
				String sql2 = "INSERT INTO shopinfo(shop_name, shop_pwd, shop_level) \r\n" + 
						"VALUES (?, ?, ?)";
				PreparedStatement pst2 = conn.prepareStatement(sql2);
				pst2.setString(1, shop.getShop_name());
				pst2.setString(2, shop.getShop_pwd());
				pst2.setInt(3, shop.getShop_level());
				pst2.execute();
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
		BeanShop result = new BeanShop();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT shop_id, shop_name, shop_pwd, shop_level, shop_logout_time \r\n" + 
					"FROM shopinfo\r\n" + 
					"WHERE shop_name = ?";
			PreparedStatement pst = conn.prepareCall(sql);
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			if (!rs.next()) {
				throw new BusinessException("商家不存在");
			}
//			rs.next();
			if (rs.getDate(5) != null) {
				throw new BusinessException("商家已注销");
			}
			if (!rs.getString("shop_pwd").equals(pwd)) {
				throw new BusinessException("密码错误");
			}
			// 若后期有 用户登陆后不需要读取的信息 再注释掉
			result.setShop_id(rs.getInt(1));
			result.setShop_name(name);
			result.setShop_pwd(pwd);
			result.setShop_level(rs.getInt(4));
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
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT shop_pwd\r\n" + 
					"FROM shopinfo\r\n" + 
					"WHERE shop_id = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			java.sql.ResultSet rs = pst.executeQuery();
//			if (!rs.next()) {
//				throw new BusinessException("该商家不存在");
//			}
			rs.next();
			if (!oldPwd.equals(rs.getString(1))) {
				throw new BusinessException("原密码错误");
			}
			rs.close();
			pst.close();
			
			sql = "UPDATE shopinfo\r\n" + 
					"SET shop_pwd = ?\r\n" + 
					"WHERE shop_id = ?";
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
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
//			String sql = "SELECT shop_name, shop_logout_time\r\n" + 
//					"FROM shopinfo\r\n" + 
//					"WHERE shop_id = ?";
//			PreparedStatement pst = conn.prepareStatement(sql);
//			pst.setInt(1, id);
//			ResultSet rs = pst.executeQuery();
//			// XXX 已注销就无法登录 因此冗余...
//			if (!rs.next()) {
//				throw new BusinessException("商家不存在");
//			}
//			if (rs.getDate(2) != null) {
//				throw new BusinessException("商家已注销");
//			}
//			rs.close();
//			pst.close();
			
			String sql = "UPDATE shopinfo\r\n" + 
					"SET shop_logout_time = NOW()\r\n" + 
					"WHERE shop_id = ?";
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ShopManager sp = new ShopManager();
		BeanShop shop = new BeanShop();
		shop.setShop_id(2);
		shop.setShop_name("qwe");
		shop.setShop_pwd("w");
		shop.setShop_level(1);
//		
//		BeanProductCategory productcategory = new BeanProductCategory();
//		productcategory.setShop_id(2);
//		productcategory.setProductcategory_column_name("testproductcategory");
//		productcategory.setProductcategory_amount(0);
		
//		BeanProduct product = new BeanProduct();
//		product.setProductcategory_id(1);
//		product.setShop_id(2);
//		product.setProduct_name("testproduct");
//		product.setProduct_price(27.1);
		try {
//			sp.register(shop);
//			shop = (BeanShop)sp.login("q", "w");
			sp.changePwd(2, "w", "e");
//			sp.logout("testshop");
//			sp.addProductCategory(productcategory);
//			sp.addProduct(product);
			sp.updateInfo(shop);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateInfo(BeanPeople people) throws BaseException {
		BeanShop shop = (BeanShop)people;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			// ....
			String sql = "UPDATE shopinfo\r\n" + 
					"SET shop_name=?, shop_level=? \r\n" + 
					"WHERE shop_id = ?\r\n";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, shop.getShop_name());
			pst.setInt(2, shop.getShop_level());
			pst.setInt(3, shop.getShop_id());
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

}
