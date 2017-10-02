package main_application;

public class Attendance_App_Main {
	
	public boolean nameEntered(String memberName){
		
		String[] nameSplit;
		boolean addingSuccess = false;
		
		nameSplit = memberName.split(" ", 2);
		System.out.printf("%s\n", nameSplit[0]);
		
		return addingSuccess;
	}
	
}
