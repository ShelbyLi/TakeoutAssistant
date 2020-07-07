package cn.edu.zucc.takeoutassistant.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(BeanEntity entity) throws BaseException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void load(BeanEntity entity) throws BaseException {
		// TODO Auto-generated method stub
		
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProductManager pm = new ProductManager();
		BeanProduct product = new BeanProduct();
		product.setProductcategory_id(1);
		product.setShop_id(1);
		product.setProduct_name("test");
		product.setProduct_price(27);
		product.setProduct_discounted_price(25);
		try {
			pm.add(product);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
