package app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class WelcomeWindowController implements Initializable{

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private RadioButton returningMember;

    @FXML
    private ToggleGroup isNewMember;

    @FXML
    private RadioButton newMember;

    @FXML
    protected Button submitButton;

    @FXML
    private void submitName() throws IOException, SQLException{

        PSQL_Database database = new PSQL_Database();
        Launch GUI = new Launch();


        // If either text field is empty show error
        if(firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty()){

            GUI.blankFields();
        }

        else {

            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            Person user;

            // Validates the entered name characters must be a-z, A-Z, the first letter is also capitalized
            if (!checkName(firstName, lastName) || firstName.length() > 50 || lastName.length() > 50) {

                GUI.nameInvalid();
                return;
            }

            user = new Person(firstName, lastName, returningMember.isSelected());

            // Checks to see if there are multiple members with the same name
            user.setMatchingNames(database.numOfMatchingNames(user));

            /* This block checks if the user is a new member that needs to add their name 
             * to the database (false) or they are an existing member who needs to be
             * singed in (true)
             */
            if (user.getReturning()) {

            	// If the name is not found in the database the user gets the option to re-enter
            	// their name or add it to the database true = add to database false = re-enter            	 
                if(user.getMatchingNames() == 0){

                    if(GUI.nameNotOnList(user)){

                        showNewMemberWindow(user);
                    }
                }

                // If there is only 1 matching name the user is unique, record their attendance
                else if (user.getMatchingNames() == 1) {

                    int meetingsAttended;

                    /* To record attendance:
                     * 1. Get the current users id
                     * 2. Using their id make and entry to the attendance table
                     * 3. Update the number of meetings they have attended
                     * 4. Welcome the user and show how many meetings they have been to
                     */
                    user.setId(database.getUserId(user));
                    database.recordAttendance(user);
                    meetingsAttended = database.updateMeetingsAttended(user);
                    GUI.confirmSignIn(user, meetingsAttended);
                }

                /* If there are multiples of the same name in the database the user is asked to enter
                 * their email (because it is always unique) in order to sign in
                 */
                else if(user.getMatchingNames() > 1){

                	showGetEmailWindow(user);
                }
            }

            // The user is new and wants to add their name to the database
            else {

                showNewMemberWindow(user);
            }
        }
    }

    // Checks each of the entered names valid chars are a-z and A-Z
    private boolean checkName(String firstName, String lastName){

        char letter;

        for(int i = 0; i < firstName.length(); i++){

            letter = firstName.charAt(i);

            if(!((letter >= 'a' && letter <= 'z') || (letter >= 'A' && letter <= 'Z') || letter == '-')){

                return false;
            }
        }

        for(int i = 0; i < lastName.length(); i++){

            letter = lastName.charAt(i);

            if(!((letter >= 'a' && letter <= 'z') || (letter >= 'A' && letter <= 'Z')) || letter == '-'){

               return false;
            }
        }

        return true;
    }

    private void showNewMemberWindow(Person user) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        Stage stage;
        Parent root;

        loader.setLocation(getClass().getResource("NewMemberWindow.fxml"));
        stage = (Stage) submitButton.getScene().getWindow();
        root = loader.load();

        // This is a getter method in NewMemberWindowController. When called it passes the user object
        // to the window so that it has the name the user entered 
        NewMemberWindowController controller = loader.getController();
        controller.getUser(user);

        Scene newScene = new Scene(root);
        stage.setScene(newScene);
        stage.show();
    }

    // A method to show the window prompting the user to enter just their email if there are multiple entries
    // with the same name as them
    private void showGetEmailWindow(Person user) throws IOException{

        FXMLLoader loader = new FXMLLoader();
        Stage stage;
        Parent root;

        loader.setLocation(getClass().getResource("GetEmailWindow.fxml"));
        stage = (Stage) submitButton.getScene().getWindow();
        root = loader.load();

        GetEmailWindowController controller = loader.getController();
        controller.getUser(user);

        Scene newScene = new Scene(root);
        stage.setScene(newScene);
        stage.show();
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

