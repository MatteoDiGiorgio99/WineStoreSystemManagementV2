package com.rossettimonicadigiorgio.winestoremanagementv2.frontend;


import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Person;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Wine;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * The {@code MainClient} is a class that defines:
 * the start page that appears as soon as you run the program
 * 
 * @author 297398
 *
 */
public class MainClient extends Application {
	static Person user;
	static ArrayList<Wine> listWineCart = new ArrayList<Wine>();
	
	static Stage SigninStage = new Stage();

	/**
	 * The method Start
	 * The starting page
	 */
	@Override
	public void start(final Stage primaryStage) throws FileNotFoundException {	
		
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 300, 400);
		HBox hbox = addHBox();	
		GridPane gridimage = new GridPane();
		
		Image imageStart = new Image("wineLogo1.jpg");
		ImageView imageviewStart = new ImageView();
		imageviewStart.setFitHeight(270);
		imageviewStart.setFitWidth(300);
		imageviewStart.setImage(imageStart);
		gridimage.add(imageviewStart, 0, 1);
		
		root.setTop(imageviewStart);
		root.setCenter(hbox);
		
		primaryStage.setTitle("Wine Store Management");
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
	}
	
	/**
	 * The method addHbox
	 * allows to put button in the first page
	 * @return a horizontal sequence of buttons
	 */
	private HBox addHBox() {
		HBox hbox = new HBox();
		
		hbox.setStyle("-fx-background-color:  #ABCDEF;");
		
		hbox.setPadding(new Insets(40,0,0,50));
		hbox.setSpacing(5);
			    
		Button btnLogin = new Button();
		btnLogin.setText("Login");
		btnLogin.setPrefSize(100, 20);
		btnLogin.setOnAction(event->
		{
			LoginPage.Login();
		}
		);
		 
		Button btnRegister = new Button();
		btnRegister.setText("Register");
		btnRegister.setPrefSize(100, 20);
		btnRegister.setOnAction(event->
		{
			LoginPage.Register();	
		}
		);

		hbox.getChildren().addAll(btnLogin,btnRegister);
		return hbox;
	}
		
	/**
	 * Point of entry for the application
	 * @param args the arguments for the application to run
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
