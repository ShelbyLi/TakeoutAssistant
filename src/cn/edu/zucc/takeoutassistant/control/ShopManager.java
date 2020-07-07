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
		// TODO Auto-generated method stub
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
			String sql = "SELECT shop_id, shop_name, shop_pwd, shop_level, shop_per_capita_consumption, shop_total_sales, shop_logout_time \r\n" + 
					"FROM shopinfo\r\n" + 
					"WHERE shop_name = ?";
			PreparedStatement pst = conn.prepareCall(sql);
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
//			if (!rs.next()) {
//				throw new BusinessException("商家不存在");
//			}  // 登陆后才可改密码 因此不存在账号不存在的情况
			rs.next();
			if (rs.getDate(7) != null) {
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
	public void changePwd(String name, String oldPwd, String newPwd) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT shop_pwd\r\n" + 
					"FROM shopinfo\r\n" + 
					"WHERE shop_name = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			java.sql.ResultSet rs = pst.executeQuery();
			if (!rs.next()) {
				throw new BusinessException("该商家不存在");
			}
			if (!oldPwd.equals(rs.getString(1))) {
				throw new BusinessException("原密码错误");
			}
			rs.close();
			pst.close();
			
			sql = "UPDATE shopinfo\r\n" + 
					"SET shop_pwd = ?\r\n" + 
					"WHERE shop_name = ?";
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
			String sql = "SELECT shop_name, shop_logout_time\r\n" + 
					"FROM shopinfo\r\n" + 
					"WHERE shop_name = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			if (!rs.next()) {
				throw new BusinessException("商家不存在");
			}
			if (rs.getDate(2) != null) {
				throw new BusinessException("商家已注销");
			}
			rs.close();
			pst.close();
			
			sql = "UPDATE shopinfo\r\n" + 
					"SET shop_logout_time = NOW()\r\n" + 
					"WHERE shop_name = ?";
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
		ShopManager sp = new ShopManager();
		BeanShop shop = new BeanShop();
		shop.setShop_name("testshop");
		shop.setShop_pwd("testpwd");
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
			sp.register(shop);
//			shop = (BeanShop)sp.login("testshop", "testpwd");
//			sp.changePwd("testshop", "testpwd", "testpwd2");
//			sp.logout("testshop");
//			sp.addProductCategory(productcategory);
//			sp.addProduct(product);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
