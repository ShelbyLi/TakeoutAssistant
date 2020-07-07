package cn.edu.zucc.takeoutassistant.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
			String sql = "UPDATE evaluate\r\n" + 
					"SET evaluate_content=?, evaluate_date=NOW(), evaluate_score=?, evaluate_photo=?\r\n" + 
					"WHERE user_id = ? AND order_id";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, evaluate.getEvaluate_content());
			pst.setInt(2, evaluate.getScore());
			pst.setBytes(3, evaluate.getEvaluate_photo());
			pst.setInt(5, evaluate.getUser_id());
			pst.setInt(6, evaluate.getOrder_id());
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
	public void delete(BeanEntity entity) throws BaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void load(BeanEntity entity) throws BaseException {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		EvaluateManager em = new EvaluateManager();
		BeanEvaluate evaluate = new BeanEvaluate();
		evaluate.setUser_id(1);
		evaluate.setOrder_id(2);
		evaluate.setEvaluate_content("good");
		evaluate.setScore(5);
		try {
			em.add(evaluate);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		
	}

}
