package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;

public class Launch extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{


        Pane userWindow = (Pane) FXMLLoader.load(getClass().getResource("WelcomeWindow.fxml"));
        Scene userScene = new Scene(userWindow);

        primaryStage.setTitle("Wentworth Computer Science Society");
        primaryStage.setScene(userScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void blankFields(){

        Alert blankFields = new Alert(Alert.AlertType.ERROR);

        blankFields.setTitle("Missing Information");
        blankFields.setHeaderText("Blank fields are not allowed");
        blankFields.setContentText("Either one or both of the fields were left blank. Please make" +
                "sure to enter the required information before submitting.");

        blankFields.showAndWait();
    }

    public boolean confirmInfo(Person user){


        Alert confirmData = new Alert(Alert.AlertType.CONFIRMATION);
        String confirmMessage;

        confirmMessage = String.format("Please make sure the information is correct before " +
                "we add it to the members list. Notice the first letter of your first and last name " +
                "have been capitalized. Is the info below correct?\n\n"+
                "Name: %s %s\n" +
                "Major: %s\n"+
                "Email: %s", user.getFirstName(), user.getLastName(), user.getMajor(), user.getEmail());

        confirmData.setTitle("Confirm Your Information");
        confirmData.setHeaderText("Review this information");
        confirmData.setContentText(confirmMessage);

        ButtonType isCorrect = new ButtonType("Correct");
        ButtonType isIncorrect = new ButtonType("Incorrect");

        confirmData.getButtonTypes().setAll(isCorrect, isIncorrect);

        Optional<ButtonType> result = confirmData.showAndWait();
        if(result.get() == isCorrect){

            return true;
        }

        else{

            return false;
        }
    }

    public void confirmSignIn(Person user, int meetingsAttended){

        Alert confirmUser = new Alert(Alert.AlertType.INFORMATION);
        String userMessage;

        userMessage = String.format("%s, welcome to this weeks meeting! You have\n" +
                "been to %d %s. Enjoy.", user.getFirstName(), meetingsAttended,
                (meetingsAttended == 1 ? "meeting" : "meetings"));

        confirmUser.setTitle("Confirm Sign In");
        confirmUser.setHeaderText("Review the information below");
        confirmUser.setContentText(userMessage);
        confirmUser.showAndWait();
    }

    public boolean nameNotOnList(Person user){

        Alert confirmUser = new Alert(Alert.AlertType.CONFIRMATION);
        String userMessage;
        ButtonType reEnter, addName;

        reEnter = new ButtonType("Re-Enter Name");
        addName = new ButtonType("Add Name to List");

        userMessage = String.format("Hello %s, after looking at the members list we\n" +
                "cannot find your name on the member list. Either re-enter\n" +
                "your name or add yourself to the members list.", user.getFirstName());

        confirmUser.setTitle("Name not Found");
        confirmUser.setHeaderText("There was a problem when signing you in");
        confirmUser.setContentText(userMessage);
        confirmUser.getButtonTypes().setAll(reEnter, addName);

        Optional<ButtonType> result = confirmUser.showAndWait();
        if(result.get() == reEnter){

            // If the user chooses to re-enter their name the method return the constant 0
            return false;
        }

        else{

            // If the user chooses to add their name the method return the constant 1
            return true;
        }
    }

    public void nameInvalid(){

        Alert invalidName = new Alert(Alert.AlertType.ERROR);

        invalidName.setTitle("Invalid Name");
        invalidName.setHeaderText("The name entered is invalid");
        invalidName.setContentText("Please re-enter your name. You should enter your\n" +
                "first name and then your last name. The requirements are:\n\n" +
                "All letters upper and lowercase (a-z and A-Z)\n" +
                "Hyphen (-)\n" +
                "Both entries should be 50 characters or less");

        invalidName.showAndWait();
    }

    public void extraInfoInvalid(){

        Alert invalidExtraInfo = new Alert(Alert.AlertType.ERROR);

        invalidExtraInfo.setTitle("Invalid Major or Email");
        invalidExtraInfo.setHeaderText("The major or email entered is invalid");
        invalidExtraInfo.setContentText("Please re-enter your info. You should enter your\n" +
                "major and or your email. The requirements are:\n\n" +
                "Major must be all letters upper and lowercase or a space (a-z A-Z ' ')\n" +
                "Email can contain letter, numbers, periods and must have an @ sign " +
                "Major must be 50 characters or less\n" +
                "Email must be 100 characters of less");

        invalidExtraInfo.showAndWait();
    }

    public void showEmailNotFound(Person user){

        Alert emailNotFound = new Alert(Alert.AlertType.ERROR);

        emailNotFound.setTitle("Email not Found");
        emailNotFound.setHeaderText("The entered email was not found");
        emailNotFound.setContentText(String.format("Hello %s, the email you entered %s was not found " +
                "on the members list. Please re-enter your email.", user.getFirstName(), user.getEmail()));

        emailNotFound.showAndWait();
    }
    
    public void showEmailTaken(Person user){
    	
    	Alert emailTaken = new Alert(AlertType.ERROR);
    	
    	emailTaken.setTitle("Email In Use");
    	emailTaken.setHeaderText("The entered email is already in use");
    	emailTaken.setContentText(String.format("Hello %s %s, the email you entered %s has already been "
    			+ "added to the members list. Please make sure you entered your "
    			+ "email correct or sign in.", user.getFirstName(), user.getLastName(), user.getEmail()));
    	
    	emailTaken.showAndWait();
    }
    
    public void showWelcomeWindow(Button submitButton) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        Stage stage;
        Parent root;

        loader.setLocation(getClass().getResource("WelcomeWindow.fxml"));
        stage = (Stage) submitButton.getScene().getWindow();
        root = loader.load();

        Scene newScene = new Scene(root);
        stage.setScene(newScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}