package main_application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import gui.Gui_API;

public class attendance_app_main {

	public static void main(String[] args){
		
		Gui_API memberInterface = new Gui_API();
		
		String memberName = memberInterface.getStudentName();
		System.out.printf("@@@@@@ %s", memberName);
		
		try{
		
			String url = "jdbc:postgresql://localhost:5432/cs_club_attendance";
			String username = "postgres";
			String password = "conniemac";
			
			Statement stmt = null;
			ResultSet rs = null;
			String sql = null;
			Connection conn = null;
			
			// Opens connection
			conn = DriverManager.getConnection(url, username, password);
			stmt = conn.createStatement();
			
			//Eexcute query
			sql = "SELECT * FROM members;";
			rs = stmt.executeQuery(sql);
			
			//Getting data from rs
			while(rs.next()){
				
				int id = rs.getInt("mem_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				
				System.out.printf("%d %s %s\n", id, firstName, lastName);
			}
				
			if(rs.next()){
				
				System.out.println(rs.getString(1));
			}
		} catch (Exception ex){
			
			System.out.println(ex.getMessage());
		}
	}
}
