package com.rossettimonicadigiorgio.winestoremanagementv2.frontend;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Administrator;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Employee;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Person;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Request;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Response;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.User;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Wine;


import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;


public class MainClient extends Application {
	static Person user;
	static ArrayList<Wine> listWineCart = new ArrayList<Wine>();

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

	public static void MyNot() {
		
		Stage stageMyNot = new Stage();
		BorderPane borderMyNot = new BorderPane();
		borderMyNot.setStyle("-fx-background-color:  #ABCDEF;");
		Scene sceneMyNot = new Scene (borderMyNot,500,500);
		stageMyNot.setScene(sceneMyNot);
		stageMyNot.show();
	}

	public static void MyAccount() {
		
		Stage stageMyAccount = new Stage();
		BorderPane borderMyAccount = new BorderPane();
		borderMyAccount.setStyle("-fx-background-color:  #ABCDEF;");
		Scene sceneMyAccount = new Scene (borderMyAccount,500,500);
		stageMyAccount.setScene(sceneMyAccount);
		stageMyAccount.show();
		
	}

	public static void MyOrder() {
		
		Stage stageMyOrder = new Stage();
		BorderPane borderMyOrder = new BorderPane();
		borderMyOrder.setStyle("-fx-background-color:  #ABCDEF;");
		Scene sceneMyOrder = new Scene (borderMyOrder,500,500);
		
		ObservableList<Wine> oblWineCart = FXCollections.observableArrayList();;
		oblWineCart.addAll(UserPage.listRecorderOrder);
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
