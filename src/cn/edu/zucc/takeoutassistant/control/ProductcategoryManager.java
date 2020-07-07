package cn.edu.zucc.takeoutassistant.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	public void delete(BeanEntity entity) throws BaseException {
		// TODO Auto-generated method stub
		// 选中删 因此也不会出现不存在的问题
//		BeanProductCategory productcategory = (BeanProductCategory) entity;
//		Connection conn = null;
//		try {
//			String sql = "";
//			conn = DBUtil.getConnection();
//			PreparedStatement pst = conn.prepareStatement(sql);
////			pst.setString(1, x);
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
	}

	@Override
	public void load(BeanEntity entity) throws BaseException {
		// TODO Auto-generated method stub
		
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
