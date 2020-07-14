package cn.edu.zucc.takeoutassistant.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.zucc.takeoutassistant.itf.IEntityManager;
import cn.edu.zucc.takeoutassistant.model.BeanEntity;
import cn.edu.zucc.takeoutassistant.model.BeanEvaluate;
import cn.edu.zucc.takeoutassistant.util.BaseException;
import cn.edu.zucc.takeoutassistant.util.DBUtil;
import cn.edu.zucc.takeoutassistant.util.DbException;

public class EvaluateManager implements IEntityManager {

	@Override
	public void add(BeanEntity entity) throws BaseException {
		BeanEvaluate evaluate = (BeanEvaluate) entity;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO evaluate (user_id, order_id, evaluate_content, evaluate_score, evaluate_photo) \r\n" + 
					"VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, evaluate.getUser_id());
			pst.setInt(2, evaluate.getOrder_id());
			pst.setString(3, evaluate.getEvaluate_content());
			pst.setInt(4, evaluate.getScore());
			pst.setBytes(5, evaluate.getEvaluate_photo());
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
	public void update(BeanEntity entity) throws BaseException {
		BeanEvaluate evaluate = (BeanEvaluate) entity;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			// 判断表中是否已经有
			String sql = "SELECT *\r\n" + 
					"FROM evaluate\r\n" + 
					"WHERE user_id = ?\r\n" + 
					"AND order_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, evaluate.getUser_id());
			pst.setInt(2, evaluate.getOrder_id());
			ResultSet rs = pst.executeQuery();
			if (!rs.next()) {
				add(evaluate);
			} else {
				String sql2 = "UPDATE evaluate\r\n" + 
						"SET evaluate_content=?, evaluate_date=NOW(), evaluate_score=?, evaluate_photo=?\r\n" + 
						"WHERE user_id = ? AND order_id = ?";
				PreparedStatement pst2 = conn.prepareStatement(sql2);
				pst2.setString(1, evaluate.getEvaluate_content());
				pst2.setInt(2, evaluate.getScore());
				pst2.setBytes(3, evaluate.getEvaluate_photo());
				pst2.setInt(4, evaluate.getUser_id());
				pst2.setInt(5, evaluate.getOrder_id());
				pst2.execute();
				pst2.close();
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
					e.printStackTrace();
				}
		}
	}

	@Override
	public void delete(int id) throws BaseException {
		// TODO Auto-generated method stub

	}

	
	public static void main(String[] args) {
		EvaluateManager em = new EvaluateManager();
		BeanEvaluate evaluate = new BeanEvaluate();
		evaluate.setUser_id(1);
		evaluate.setOrder_id(2);
		evaluate.setEvaluate_content("bbb");
		evaluate.setScore(3);
		try {
//			em.add(evaluate);
			em.update(evaluate);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		
	}

	public BeanEvaluate search(int order_id) throws BaseException {
		BeanEvaluate result = new BeanEvaluate();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			// 判断表中是否已经有
			String sql = "SELECT user_id, order_id, evaluate_content, evaluate_date, evaluate_score\r\n" + 
					"FROM evaluate\r\n" + 
					"WHERE order_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, order_id);
			ResultSet rs = pst.executeQuery();
			rs.next();
			result.setUser_id(rs.getInt(1));
			result.setOrder_id(rs.getInt(2));
			result.setEvaluate_content(rs.getString(3));
			result.setEvaluate_date(rs.getTimestamp(4));
			result.setScore(rs.getInt(5));
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
