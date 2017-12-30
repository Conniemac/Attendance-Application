# Attendance-Application

## The Project

The purpose of this project is to give the Computer Science Society a better way to keep
attendance at our weekly meetings. Using an Excel spreadsheet creates a long line to sign
in and is not the easiest to analyze. My program will cut down on sign in time and make it 
easier for the administrators to get information from the attendance list.
	
## Technologies Used

Database: PostgreSQL
GUI: JavaFX / FXML
	
## How It Works

1. The user is asked to enter their first and last name. The name is check for valid
characters (a-z and A-Z)
	
2. If the user is returning a new member they should select "New Member" if not they can just hit submit
	
3. For returning members:
a. If the member shares a name with another member they are asked for their email since this is always
		unique
		
b. If their name is unique their attendance is recored
		
3. For new members:
a. The user is asked to enter their major and email
		
b. They are then asked to confirm the information they provided
		
4. The member is welcomed to this weeks meeting 