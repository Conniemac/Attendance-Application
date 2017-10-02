package main_application;

/* This class creates each object needed to run the application. The main method is found in Attendance_App_Main */

public class API {
	
	PSQL_Database database1;

	
	public API(){
		
		database1 = new PSQL_Database();
	}
	
	public void connectToDB(){
		
		database1.connectToDB();
	}
}
