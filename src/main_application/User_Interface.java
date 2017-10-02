package main_application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;

public class User_Interface extends Application {
	
	Attendance_App_Main backEndConnection = new Attendance_App_Main();
	private static String studentNameString;

	public void start(Stage primaryStage) {
		try {
			
			GridPane pane = new GridPane();
			pane.setVgap(5);
			pane.setHgap(5);
			pane.setAlignment(Pos.CENTER);
			
			Text introduction = new Text("Welcome To the Wentworth Computer Science Club!");		
			GridPane.setConstraints(introduction, 0, 0);
					
			TextField studentName = new TextField("Enter your name here");
			GridPane.setConstraints(studentName, 0, 1);
			
			Button submit_button = new Button("Submit");
			GridPane.setConstraints(submit_button, 1, 1); 
			
			submit_button.setOnAction(e->{
				
				studentNameString = studentName.getText();
				backEndConnection.nameEntered(studentNameString);
			});
			
			pane.getChildren().addAll(introduction, submit_button, studentName);
			
			Scene scene = new Scene(pane, 450, 250);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}
}
