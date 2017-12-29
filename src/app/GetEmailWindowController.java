package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GetEmailWindowController implements Initializable{

    Person user;

    @FXML
    private TextField enterEmailField;

    @FXML
    private Button submitButton;

    @FXML
    void submitEmail(ActionEvent event) throws IOException{

        int meetingsAttended;
        PSQL_Database database = new PSQL_Database();
        Launch GUI = new Launch();

        if(enterEmailField.getText().isEmpty()){

            GUI.blankFields();
        }

        else {

            user.setEmail(enterEmailField.getText());

            if(database.checkEmail(user)) {

                /*
                 Calls the overloaded method getUserId
                 Since the same name appears more than one time the email is used in
                 the SQL query to get the correct id
                 */
                user.setId(database.getUserId(user, user.getEmail()));

                database.recordAttendance(user);
                meetingsAttended = database.updateMeetingsAttended(user);
                GUI.confirmSignIn(user, meetingsAttended);
                GUI.showWelcomeWindow(submitButton);
            }

            else{

            	// The entered email was not found in the database therefore the id
            	// could not be found. The user is asked to re-enter their email
                GUI.showEmailNotFound(user);
            }
        }
    }

    public void getUser(Person user){

        this.user = user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}