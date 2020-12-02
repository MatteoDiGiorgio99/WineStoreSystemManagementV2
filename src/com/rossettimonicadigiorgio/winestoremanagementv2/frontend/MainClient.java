package com.rossettimonicadigiorgio.winestoremanagementv2.frontend;


import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Person;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Request;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Wine;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class MainClient extends Application {
	static Person user;
	static ArrayList<Wine> listWineCart = new ArrayList<Wine>();
	
	static Stage SigninStage = new Stage();

	@Override
	
	/**
	 * The method Start
	 * The starting page
	 */
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

	/*
	 * The method RestockBottle
	 * allows the Employee to restock wines bottle
	 */
	public static void RestockBottle() {
		
		Stage stageRestock = new Stage();
		stageRestock.setTitle("Restock Wine");
		BorderPane borderRestock = new BorderPane();
		borderRestock.setStyle("-fx-background-color:  #ABCDEF;");
		Scene sceneRestock = new Scene (borderRestock,500,500);
		
		ArrayList<Object> params = new ArrayList<Object>();
		params.add("");
		
		ArrayList<Wine> result = (ArrayList<Wine>) new Client().run(new Request("filterWines", params)).getValue();
		ComboBox cbxWine = new ComboBox();
		cbxWine.getItems().addAll(result);
		
		Label lblRestock = new Label("Select Wine");
		lblRestock.setFont(Font.font("Arial",25));
		
		TextField txtNumbRestock = new TextField();
		txtNumbRestock.setPromptText("Insert number of bottle");
		
		Button btnRestock = new Button("Restock");
		btnRestock.setOnAction(event->{
			//Rimettere vini nel database
		});
		
		VBox vbxRestock = new VBox();
		vbxRestock.setSpacing(20);
		vbxRestock.setPadding(new Insets(150,150,0,150));	
		vbxRestock.getChildren().addAll(lblRestock,cbxWine,txtNumbRestock,btnRestock);
		
		borderRestock.setCenter(vbxRestock);
		
		stageRestock.setScene(sceneRestock);
		stageRestock.show();
		
	}

	/**
	 * The method Ship
	 * allows the Employee to ship orders
	 */
	public static void Ship() {
		
		Stage stageShip = new Stage();
		stageShip.setTitle("Ship Order");
		BorderPane borderShip = new BorderPane();
		borderShip.setStyle("-fx-background-color:  #ABCDEF;");
		Scene sceneShip = new Scene (borderShip,500,500);
		
		ComboBox cbxOrder = new ComboBox();
		//cbxWine.getItems().addAll(result);
		
		Label lblRestock = new Label("Select Order");
		lblRestock.setFont(Font.font("Arial",25));
		
		Button btnRestock = new Button("Ship");
		btnRestock.setOnAction(event->{
			//Togliere l'ordine dal database poichè spedito
		});
		
		VBox vbxRestock = new VBox();
		vbxRestock.setSpacing(20);
		vbxRestock.setPadding(new Insets(150,150,0,150));	
		vbxRestock.getChildren().addAll(lblRestock,cbxOrder,btnRestock);
		
		borderShip.setCenter(vbxRestock);
		
		stageShip.setScene(sceneShip);
		stageShip.show();
		
	}

	/**
	 * The method LastOrder 
	 * allows user to see last orders 
	 */
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
