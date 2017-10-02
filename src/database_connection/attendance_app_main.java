package database_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class attendance_app_main {

	public static void main(String[] args){
		
		String sql = null;
		Statement stmt = null;
		
		try{
		
			Class.forName("org.postgresql.Driver");
			String url = "postgres://localhost:5423/cs_club_attendance";
			Connection conn1 = DriverManager.getConnection(url, "postgres", "conniemac");
			
			
		} catch (Exception ex){
			
			System.out.println(ex.getMessage());
		}
	}
}
