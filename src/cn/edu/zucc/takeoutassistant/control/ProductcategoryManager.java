package cn.edu.zucc.takeoutassistant.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.edu.zucc.takeoutassistant.itf.IEntityManager;
import cn.edu.zucc.takeoutassistant.model.BeanEntity;
import cn.edu.zucc.takeoutassistant.model.BeanProductCategory;
import cn.edu.zucc.takeoutassistant.util.BaseException;
import cn.edu.zucc.takeoutassistant.util.DBUtil;
import cn.edu.zucc.takeoutassistant.util.DbException;

public class ProductcategoryManager implements IEntityManager {

	@Override
	public void add(BeanEntity entity) throws BaseException {
		// TODO Auto-generated method stub
		// 新建商品类别
		BeanProductCategory productcategory = (BeanProductCategory) entity;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO productcategory(shop_id, productcategory_name) \r\n" + 
					"VALUES (?, ?);";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, productcategory.getShop_id());
			pst.setString(2	, productcategory.getProductcategory_name());
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
	public void update(BeanEntity entity) throws BaseException {
		// TODO Auto-generated method stub
		// 修改商品类别参数 (目前想法 直接列出表后 在表中修改 因此一开始不需要查找判断是否存在)
		BeanProductCategory productcategory = (BeanProductCategory) entity;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			// ....
			String sql = "UPDATE productcategory\r\n" + 
					"SET productcategory_name = ?\r\n" + 
					"WHERE productcategory_id = ?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, productcategory.getProductcategory_name());
			pst.setInt(2, productcategory.getProductcategory_id());
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
	public void delete(int id) throws BaseException {
		// TODO Auto-generated method stub
		
	}



	public BeanProductCategory search(String name) throws BaseException {
		BeanProductCategory result = new BeanProductCategory();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT productcategory_id, shop_id, productcategory_name\r\n" + 
					"FROM productcategory\r\n" + 
					"WHERE productcategory_name =?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			if (!rs.next()) {
				throw new BaseException("商品类别不存在");
			}
			result.setProductcategory_id(rs.getInt(1));
			result.setShop_id(2);
			result.setProductcategory_name(name);
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProductcategoryManager pcm = new ProductcategoryManager();
		BeanProductCategory productcategory = new BeanProductCategory();
		productcategory.setProductcategory_id(1);
		productcategory.setProductcategory_name("testproductcategoryupdate");
		productcategory.setShop_id(1);
		try {
//			pcm.add(productcategory);
			pcm.update(productcategory);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




}
