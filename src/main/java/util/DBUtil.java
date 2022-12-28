package util;

import java.sql.*;

public class DBUtil {
	public static Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","gdj58","java1234");
		conn.setAutoCommit(false);
		return conn;
	}
	
	public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) throws Exception{
		// 각 매개변수값이 없는 경우도 있을 수 있으므로
		if(rs != null) {rs.close();}
		if(stmt != null) {stmt.close();}
		if(conn != null) {conn.close();}
	}
}