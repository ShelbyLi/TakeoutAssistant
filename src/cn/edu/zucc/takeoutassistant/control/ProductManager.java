package cn.edu.zucc.takeoutassistant.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.takeoutassistant.itf.IEntityManager;
import cn.edu.zucc.takeoutassistant.model.BeanEntity;
import cn.edu.zucc.takeoutassistant.model.BeanProduct;
import cn.edu.zucc.takeoutassistant.util.BaseException;
import cn.edu.zucc.takeoutassistant.util.DBUtil;
import cn.edu.zucc.takeoutassistant.util.DbException;

public class ProductManager implements IEntityManager {

	@Override
	public void add(BeanEntity entity) throws BaseException {
		// TODO Auto-generated method stub
		// 新增商品; 商品对应类别 数量需要+1
		BeanProduct product = (BeanProduct) entity;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO productdetails(productcategory_id, shop_id, product_name, product_price, product_discounted_price) \r\n" + 
					"VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, product.getProductcategory_id());  // 现在不知道如何获取
			pst.setInt(2, product.getShop_id());  // 现在不知道如何获取当前登录账号的信息
			pst.setString(3, product.getProduct_name());
			pst.setDouble(4, product.getProduct_price());
			pst.setDouble(5, product.getProduct_discounted_price());
			pst.execute();
			pst.close();
			
			// 字段多余 已删除
//			sql = "UPDATE productcategory\r\n" + 
//					"SET productcategory_amount = productcategory_amount+1\r\n" + 
//					"WHERE productcategory_id = ?";
//			pst = conn.prepareStatement(sql);
//			pst.setInt(1, product.getProductcategory_id());
//			pst.execute();
//			pst.close();
			
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
	public void update(BeanEntity entity)  throws BaseException{
		// 商家登录后修改 因此商家id不用改
		BeanProduct product = (BeanProduct) entity;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE productdetails\r\n" + 
					"SET productcategory_id=?, product_name=?, product_price=?, product_discounted_price=?\r\n" + 
					"WHERE product_id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, product.getProductcategory_id());
			pst.setString(2, product.getProduct_name());
			pst.setDouble(3, product.getProduct_price());
			pst.setDouble(4, product.getProduct_discounted_price());
			pst.setInt(5, product.getProduct_id());
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
	public void delete(int id) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE productdetails\r\n" + 
					"SET product_delete_time = NOW()\r\n" + 
					"WHERE product_id = ?";
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


	
	public List<BeanProduct> loadAll(int shop_id) throws BaseException {
		List<BeanProduct> result = new ArrayList<BeanProduct>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT product_id, productcategory_name, product_name, shop_name, product_price, product_discounted_price\r\n" + 
					"FROM productdetails a, productcategory b, shopinfo c\r\n" + 
					"WHERE a.productcategory_id=b.productcategory_id\r\n" + 
					"AND a.shop_id = c.shop_id\r\n" + 
					"AND product_delete_time IS NULL\r\n" + 
					"AND a.shop_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);	
			pst.setInt(1, shop_id);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanProduct p = new BeanProduct();
				p.setProduct_id(rs.getInt(1));
				p.setProductcategory_name(rs.getString(2));
				p.setProduct_name(rs.getString(3));
				p.setShop_name(rs.getString(4));
				p.setProduct_price(rs.getDouble(5));
				p.setProduct_discounted_price(rs.getDouble(6));
				result.add(p);
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}

	public List<BeanProduct> fuzzySearch(String keyWord, int shop_id) throws BaseException {
		List<BeanProduct> result = new ArrayList<BeanProduct>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT product_id, productcategory_name, product_name, shop_name, product_price, product_discounted_price\r\n" + 
					"FROM productdetails a, productcategory b, shopinfo c\r\n" + 
					"WHERE a.productcategory_id=b.productcategory_id\r\n" + 
					"AND a.shop_id = c.shop_id\r\n" + 
					"AND a.shop_id = ?\r\n" + 
					"AND product_delete_time IS NULL\r\n" + 
					"AND (productcategory_name LIKE ? OR product_name LIKE ? OR shop_name LIKE ?)";
			PreparedStatement pst = conn.prepareStatement(sql);	
			pst.setInt(1, shop_id);
			pst.setString(2, "%"+keyWord+"%");
			pst.setString(3, "%"+keyWord+"%");
			pst.setString(4, "%"+keyWord+"%");
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanProduct p = new BeanProduct();
				p.setProduct_id(rs.getInt(1));
				p.setProductcategory_name(rs.getString(2));
				p.setProduct_name(rs.getString(3));
				p.setShop_name(rs.getString(4));
				p.setProduct_price(rs.getDouble(5));
				p.setProduct_discounted_price(rs.getDouble(6));
				result.add(p);
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}
	
	public static void main(String[] args) {
		ProductManager pm = new ProductManager();
		BeanProduct product = new BeanProduct();
//		product.setProduct_id(6);
		product.setProductcategory_id(6);
		product.setProduct_name("肉串");
		product.setProduct_price(5);
		product.setProduct_discounted_price(4);
		product.setShop_id(1);
		try {
			pm.add(product);
//			List<BeanProduct> products = new ArrayList<BeanProduct>();
//			products = pm.loadAll();
//			for (BeanProduct item: products) {
//				System.out.println(item.getProduct_name());
//			}
//			pm.update(product);
		} catch (BaseException e) {
			e.printStackTrace();
		}
	}

	public BeanProduct searchDetailInfo(int id) throws BaseException {
		BeanProduct result = new BeanProduct();
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT product_id, productcategory_name, product_name, shop_name, product_price, product_discounted_price\r\n" + 
					"FROM productdetails a, productcategory b, shopinfo c\r\n" + 
					"WHERE a.productcategory_id=b.productcategory_id\r\n" + 
					"AND a.shop_id = c.shop_id\r\n" + 
					"AND product_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			rs.next();
			result.setProduct_id(rs.getInt(1));
			result.setProductcategory_name(rs.getString(2));
			result.setProduct_name(rs.getString(3));
			result.setShop_name(rs.getString(4));
			result.setProduct_price(rs.getDouble(5));
			result.setProduct_discounted_price(rs.getDouble(6));
			
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
		return result;
		
	}

	
}
