package com.rossettimonicadigiorgio.winestoremanagementv2.frontend;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
	    
		options[0].setOnAction(event -> { MainClient.Ship(); });
		options[1].setOnAction(event -> { MainClient.RestockBottle(); });
		
		return vbox;
	}
}
