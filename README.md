# Attendance-Application

## The Project

The purpose of this project is to give the Computer Science Society a better way to keep
attendance at our weekly meetings. Using an Excel spreadsheet creates a long line to sign
in and is not the easiest to analyze. My program will cut down on sign in time and make it 
easier for the administrators to get information from the attendance list.
	
## Technologies Used

*Database: PostgreSQL
*GUI: JavaFX / FXML
	
## How It Works

* The user is asked to enter their first and last name. The name is check for valid
characters (a-z and A-Z)
	
* If the user is returning a new member they should select "New Member" if not they can just hit submit
	
* For returning members:
  * If the member shares a name with another member they are asked for their email since this is always
		unique
		
  * If their name is unique their attendance is recored
		
* For new members:
  * The user is asked to enter their major and email
		
  * They are then asked to confirm the information they provided
		
* The member is welcomed to this weeks meeting 