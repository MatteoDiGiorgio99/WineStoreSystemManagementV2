package com.rossettimonicadigiorgio.winestoremanagementv2.frontend;

import java.util.ArrayList;

import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Order;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Request;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Response;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Wine;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The {@code EmployeePage} is a class that defines:
 * The graphic of the EmployeePage
 * @author 297398
 *
 */
public class EmployeePage {
	
	/**
	 * The method SignInEmployee
	 * When pressed the button takes you to a new page 
	 */
	public static void SignInEmployee() {
		
		Stage SigninStage= new Stage();
		BorderPane border = new BorderPane();
		
		HBox hboxResearch = Components.HboxResearch(SigninStage);
		VBox vboxData = VBoxDataEmployee();
		
		border.setTop(hboxResearch);
		border.setCenter(vboxData);
		
		Scene sceneSignIn = new Scene(border,500,500);
		
		SigninStage.setTitle("Welcome in your account");
		SigninStage.setScene(sceneSignIn);
		SigninStage.show();
	}
	
	/**
	 * The method VBoxDataEmployee
	 * allows the Employee to ship or restock bottle of wines
	 */
	public static VBox VBoxDataEmployee() {
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(100,200,200,200));
		vbox.setSpacing(8);
		
		Text title = new Text("Menu'");
		title.setFont(Font.font("Arial",FontWeight.BOLD,25));
		vbox.getChildren().add(title);
		
		Hyperlink[] options = new Hyperlink[] {new Hyperlink("Ship"),new Hyperlink("Restock Bottle")};
		for(int i = 0; i<2;i++)
		{
			vbox.getChildren().add(options[i]);
		}
		
	    options[0].setFont(Font.font("Arial",13));
	    options[1].setFont(Font.font("Arial",13));
	    
		options[0].setOnAction(event -> { EmployeePage.Ship(); });
		options[1].setOnAction(event -> { EmployeePage.RestockBottle(); });
		
		return vbox;
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
		ComboBox<Wine> cbxWine = new ComboBox<Wine>();
		cbxWine.getItems().addAll(result);
		
		Label lblRestock = new Label("Select Wine");
		lblRestock.setFont(Font.font("Arial",25));
		
		TextField txtNumbRestock = new TextField();
		txtNumbRestock.setPromptText("Insert number of bottle");
		
		Button btnRestock = new Button("Restock");
		btnRestock.setOnAction(event->{
			params.clear();
			
			Wine wine = cbxWine.getSelectionModel().getSelectedItem();
			wine.setBottlesNumber(Integer.parseInt(txtNumbRestock.getText()));
			
			params.add(wine);
			
			if((boolean) new Client().run(new Request("restockWine", params)).getValue()) {
				Alert addEmployee = new Alert(AlertType.INFORMATION);
				addEmployee.setTitle("Ship Order");
				addEmployee.setHeaderText("SUCCESS");
				addEmployee.setContentText("The operation has completed successfully");
				addEmployee.showAndWait();
        	} else {
        		Alert addEmployee = new Alert(AlertType.ERROR);
				addEmployee.setTitle("Ship Order");
				addEmployee.setHeaderText("ERROR");
				addEmployee.setContentText("The operation is failed!");
				addEmployee.showAndWait();
        	}
			
			stageRestock.close();
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
		
		ArrayList<Order> orders = (ArrayList<Order>) new Client().run(new Request("listOrdersToShip", null)).getValue();
		
		ComboBox<Order> cbxOrder = new ComboBox<Order>();
		cbxOrder.getItems().addAll(orders);
		
		Label lblRestock = new Label("Select Order");
		lblRestock.setFont(Font.font("Arial",25));
		
		Button btnRestock = new Button("Ship");
		btnRestock.setOnAction(event->{
			ArrayList<Object> params = new ArrayList<Object>();
			params.add(cbxOrder.getSelectionModel().getSelectedItem().getIDOrder());
			
			if((boolean) new Client().run(new Request("shipOrder", params)).getValue()) {
				Alert addEmployee = new Alert(AlertType.INFORMATION);
				addEmployee.setTitle("Ship Order");
				addEmployee.setHeaderText("SUCCESS");
				addEmployee.setContentText("The operation has completed successfully");
				addEmployee.showAndWait();
        	} else {
        		Alert addEmployee = new Alert(AlertType.ERROR);
				addEmployee.setTitle("Ship Order");
				addEmployee.setHeaderText("ERROR");
				addEmployee.setContentText("The operation is failed!");
				addEmployee.showAndWait();
        	}
			
			stageShip.close();
		});
		
		VBox vbxRestock = new VBox();
		vbxRestock.setSpacing(20);
		vbxRestock.setPadding(new Insets(150,150,0,150));	
		vbxRestock.getChildren().addAll(lblRestock,cbxOrder,btnRestock);
		
		borderShip.setCenter(vbxRestock);
		
		stageShip.setScene(sceneShip);
		stageShip.show();
	}
}
