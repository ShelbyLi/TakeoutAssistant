package cn.edu.zucc.takeoutassistant.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.zucc.takeoutassistant.util.BaseException;
import cn.edu.zucc.takeoutassistant.util.DBUtil;
import cn.edu.zucc.takeoutassistant.util.DbException;

public class PrepareMethod {
	public static ResultSet prepareLoadAll(String sql) throws BaseException{
		Connection conn=null;
		ResultSet rs = null;
		try {
			conn=DBUtil.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);				
			rs = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
//		finally{
//			if(conn!=null)
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//		}
		return rs;
	}
}
