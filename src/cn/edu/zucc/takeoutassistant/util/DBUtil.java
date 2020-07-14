package cn.edu.zucc.takeoutassistant.util;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtil {
	private static final String jdbcUrl="jdbc:mysql://localhost:3306/takeoutassistant_test?characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai";
	private static final String dbUser="root";
	private static final String dbPwd="tmrrwbBTT37";
	static{
		try {
//			Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws java.sql.SQLException{
		return java.sql.DriverManager.getConnection(jdbcUrl, dbUser, dbPwd);
	}
	
	
//	private static DBUtil dbPool;
//	private static ComboPooledDataSource dataSource;
//
//	static {
//		dbPool = new DBUtil();
//	}
//
//	public DBUtil() {
//		try {
//			dataSource = new ComboPooledDataSource();
//			dataSource.setUser("root");
//			dataSource.setPassword("tmrrwbBTT37");
//			dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/takeoutassistant?useUnicode=true&characterEncoding=UTF-8");
//			dataSource.setDriverClass("com.mysql.jdbc.Driver");
//			dataSource.setInitialPoolSize(2);
//			dataSource.setMinPoolSize(1);
//			dataSource.setMaxPoolSize(10);
//			dataSource.setMaxStatements(50);
//			dataSource.setMaxIdleTime(60);
//		} catch (PropertyVetoException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	public final static DBUtil getInstance() {
//		return dbPool;
//	}
//
//	public final static Connection getConnection() {
//		try {
//			return dataSource.getConnection();
//		} catch (SQLException e) {
//			throw new RuntimeException("failed ", e);
//		}
//	}
}
