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

public class Components {
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
		
	public static VBox VBoxData() {
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10));
		vbox.setSpacing(8);
		
		
		Text title = new Text("Menu'");
		title.setFont(Font.font("Arial",FontWeight.BOLD,15));
		vbox.getChildren().add(title);
		
		Hyperlink[] options = new Hyperlink[] {new Hyperlink("Last Order"),new Hyperlink("My Account"),new Hyperlink("My Notifications") };
		for(int i = 0; i<3;i++)
		{
			vbox.getChildren().add(options[i]);
		}
		options[0].setOnAction(event -> { MainClient.LastOrder(); });
		options[1].setOnAction(event -> { UserPage.MyAccount(); });
		options[2].setOnAction(event -> { UserPage.MyNot(); });
	
		return vbox ;
	}
}
