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
		// ������Ʒ; ��Ʒ��Ӧ��� ������Ҫ+1
		BeanProduct product = (BeanProduct) entity;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO productdetails(productcategory_id, shop_id, product_name, product_price, product_discounted_price) \r\n" + 
					"VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, product.getProductcategory_id());  // ���ڲ�֪����λ�ȡ
			pst.setInt(2, product.getShop_id());  // ���ڲ�֪����λ�ȡ��ǰ��¼�˺ŵ���Ϣ
			pst.setString(3, product.getProduct_name());
			pst.setDouble(4, product.getProduct_price());
			pst.setDouble(5, product.getProduct_discounted_price());
			pst.execute();
			pst.close();
			
			// �ֶζ��� ��ɾ��
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


	
	public List<BeanProduct> loadAll() throws BaseException {
		List<BeanProduct> result = new ArrayList<BeanProduct>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT product_id, productcategory_name, product_name, shop_name, product_price, product_discounted_price\r\n" + 
					"FROM productdetails a, productcategory b, shopinfo c\r\n" + 
					"WHERE a.productcategory_id=b.productcategory_id\r\n" + 
					"AND a.shop_id = c.shop_id";
			PreparedStatement pst = conn.prepareStatement(sql);				
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
		// TODO Auto-generated method stub
		ProductManager pm = new ProductManager();
		BeanProduct product = new BeanProduct();
		product.setProductcategory_id(1);
		product.setShop_id(1);
		product.setProduct_name("test");
		product.setProduct_price(27);
		product.setProduct_discounted_price(25);
		try {
//			pm.add(product);
			List<BeanProduct> products = new ArrayList<BeanProduct>();
			products = pm.loadAll();
			for (BeanProduct item: products) {
				System.out.println(item.getProduct_name());
			}
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public List<BeanEntity> load(BeanEntity entity) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

}
