package com.rossettimonicadigiorgio.winestoremanagementv2.frontend;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The {@code Component} is a class that defines:
 * The component of user interface
 * @author 297398
 *
 */
public class Components {
	
	/**
	 * The method HboxResearch
	 * add the textbox for the research 
	 * @param logoutStage takes you back to the login stage
	 * @return hbox which contains selected things
	 */
	public static HBox HboxResearch(Stage logoutStage) {
		
		HBox hbox = new HBox();
		
		Label lblName = new Label(MainClient.user.getName() + " " + MainClient.user.getSurname());
		lblName.setFont(Font.font("Arial",FontWeight.BOLD,20));
		Button btnLogout = new Button("Logout");
		
		hbox.setPadding(new Insets(15,12,15,12));
		hbox.setSpacing(150);
		hbox.setStyle("-fx-background-color: #336699;");
		
		//hbox.setAlignment(Pos.TOP_LEFT);
		hbox.getChildren().addAll(lblName,btnLogout);
		btnLogout.setOnAction(event -> logoutStage.close() );
		
		return hbox;
	}
	
	/**
	 * The method addStackPaneResearch
	 * add a button for the textbox research 
	 * @param hboxResearch textbox research
	 * @param grid the table of wine
	 * @param cartFlow the shopping cart
	 */
	public static void addStackPaneResearch(HBox hboxResearch, GridPane grid, FlowPane cartFlow) {
		StackPane stack = new StackPane();
		TextField txtResearch = new TextField();
		Button btnResearch = new Button("Research");
		txtResearch.setPrefSize(350, 20);
		stack.getChildren().addAll(txtResearch,btnResearch);
		stack.setAlignment(Pos.TOP_RIGHT);
		
		btnResearch.setOnAction(event -> {
			grid.getChildren().clear();
			grid.getChildren().add(UserPage.GridData(txtResearch.getText(), cartFlow));
			
		});
		
		hboxResearch.getChildren().add(stack);
	}
}
