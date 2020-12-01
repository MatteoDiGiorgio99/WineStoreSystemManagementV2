package com.rossettimonicadigiorgio.winestoremanagementv2.frontend;


import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Notification;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Person;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Wine;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class MainClient extends Application {
	static Person user;
	static ArrayList<Wine> listWineCart = new ArrayList<Wine>();
	
	static Stage SigninStage = new Stage();

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
	
	private HBox addHBox() throws FileNotFoundException {
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

	public static void RestockBottle() {
		
		Stage stageRestock = new Stage();
		BorderPane borderRestock = new BorderPane();
		borderRestock.setStyle("-fx-background-color:  #ABCDEF;");
		Scene sceneRestock = new Scene (borderRestock,500,500);
		stageRestock.setScene(sceneRestock);
		stageRestock.show();
		
	}

	public static void Ship() {
		
		Stage stageShip = new Stage();
		BorderPane borderShip = new BorderPane();
		borderShip.setStyle("-fx-background-color:  #ABCDEF;");
		Scene sceneShip = new Scene (borderShip,500,500);
		stageShip.setScene(sceneShip);
		stageShip.show();
		
	}

	public static void LastOrder() {
		if(UserPage.lastOrder == null)
			return;
		
		Stage stageMyOrder = new Stage();
		BorderPane borderMyOrder = new BorderPane();
		borderMyOrder.setStyle("-fx-background-color:  #ABCDEF;");
		Scene sceneMyOrder = new Scene (borderMyOrder,500,500);
		
		ObservableList<Wine> oblWineCart = FXCollections.observableArrayList();
		
		oblWineCart.addAll(UserPage.lastOrder.getWines());
		
		ListView<Wine>lsvWineCartOrder = new ListView<Wine>(oblWineCart);
		
		
		borderMyOrder.setCenter(lsvWineCartOrder);
		
		stageMyOrder.setScene(sceneMyOrder);
		stageMyOrder.show();
	}
		
	/**
	 * Point of entry for the application
	 * @param args the arguments for the application to run
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
