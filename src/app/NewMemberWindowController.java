package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewMemberWindowController implements Initializable{

    private Person user;

    @FXML
    private TextField enterEmailField;

    @FXML
    private TextField enterMajorField;

    @FXML
    private Button submitButton;

    @FXML
    void submitExtraInfo(ActionEvent event) throws IOException{

        PSQL_Database database = new PSQL_Database();
        Launch GUI = new Launch();

        // Checks if the either of the text fields are empty
        if(enterEmailField.getText().isEmpty() || enterMajorField.getText().isEmpty()){

            GUI.blankFields();
        }

        else {

            // Gets the text entered in the text fields
            user.setEmail(enterEmailField.getText());
            user.setMajor(enterMajorField.getText());

            // Checks to make sure the string major is not over 50 chars and the email 
            // is not over 100 due to rules set by the database
            if(!checkExtraInfo(user.getMajor(), user.getEmail())){

                GUI.extraInfoInvalid();
            }
            
            // If the email already appears in the database the user mis-typed or
            // they have already entered their name into the database
            if(database.checkEmail(user)){
            	
            	GUI.showEmailTaken(user);
            }

            // Show the user the info they have entered. The user has the option to enter
            // the info as is or restart
            else if(GUI.confirmInfo(user)) {

                database.addToList(user);
                GUI.showWelcomeWindow(submitButton);

                // 1 is supplied as the number of meetings attended because this is users first meeting
                GUI.confirmSignIn(user, 1);
            }

            else{

                GUI.showWelcomeWindow(submitButton);
            }
        }
    }

    // A method to pass the user data from the welcome window scene to the new member scene
    public void getUser(Person user){

        this.user = user;
    }

    private boolean checkExtraInfo(String major, String email){

        char letter;
        int atCount = 0;

        // Checks the length of each string. email must be 50 or less and 
        // email must be 100 or less characters
        if(user.getEmail().length() > 100 || user.getMajor().length() > 50){
        	
        	return false;
        }
        
        // This loop validates the email it  must contain 1 '@' symbol, 
        // can have '.' and numbers 0-9 but spaces ' ' are not allowed
        for(int i = 0; i < email.length(); i++){

            letter = email.charAt(i);

            if(letter == '@'){

                atCount++;
            }

            if(!((letter >= 'a' && letter <= 'z') || (letter >= 'A' && letter <= 'Z') ||
                    (letter >= '0' && letter <= '9') || letter == ' ' || letter == '.' || letter == '@')){

                return false;
            }
        }

        if(atCount < 1){

            return false;
        }

        // This loop validates the entered major. It can only contain a-z, A-Z and space ' '
        for(int i = 0; i < major.length(); i++){

            letter = major.charAt(i);

            if(!((letter >= 'a' && letter <= 'z') || (letter >= 'A' && letter <= 'Z') || letter == ' ')){

                return false;
            }
        }

        return true;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}