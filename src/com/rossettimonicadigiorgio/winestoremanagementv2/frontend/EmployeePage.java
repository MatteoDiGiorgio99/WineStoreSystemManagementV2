package com.rossettimonicadigiorgio.winestoremanagementv2.frontend;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EmployeePage {
	public static void SignInEmployee() {
		
		Stage SigninStage = new Stage();
		BorderPane border = new BorderPane();
		
		HBox hboxResearch = Components.HboxResearch(SigninStage);
		VBox vboxData = VBoxDataEmployee();
		GridPane gridData = EmployeePage.GridDataEmployee();
		border.setTop(hboxResearch);
		border.setLeft(vboxData);
		border.setRight(gridData);
		
		Scene sceneSignIn = new Scene(border,1000,600);
		
		SigninStage.setTitle("Welcome in your account");
		SigninStage.setScene(sceneSignIn);
		SigninStage.show();
	}
	
	public static GridPane GridDataEmployee() {
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0,10,0,10));
		
		PieChart chartWine = new PieChart(FXCollections.observableArrayList(new PieChart.Data("UX",50),new PieChart.Data("UY",20),new PieChart.Data("UZ",30)));
		chartWine.setTitle("WINES AVAILABLE");
		grid.getChildren().add(chartWine);
		return grid;
	}
	
	public static VBox VBoxDataEmployee() {
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10));
		vbox.setSpacing(8);
		
		Text title = new Text("Menu'");
		title.setFont(Font.font("Arial",FontWeight.BOLD,15));
		vbox.getChildren().add(title);
		
		Hyperlink[] options = new Hyperlink[] {new Hyperlink("Ship"),new Hyperlink("Restock Bottle")};
		for(int i = 0; i<2;i++)
		{
			vbox.getChildren().add(options[i]);
		}
		options[0].setOnAction(event -> { MainClient.Ship(); });
		options[1].setOnAction(event -> { MainClient.RestockBottle(); });
		
		return vbox;
	}
}
