package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/FortuneDB?" + "useSSL=false&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "rootpw";

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new SQLException("MySQL JDBC 드라이버를 찾을 수 없습니다.", e);
		}
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
