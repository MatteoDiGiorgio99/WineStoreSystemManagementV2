package com.rossettimonicadigiorgio.winestoremanagementv2.frontend;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminPage {
	public static void SignInAdmin() {
		
		Stage SigninStage = new Stage();
		BorderPane border = new BorderPane();
		
		HBox hboxResearch = Components.HboxResearch(SigninStage);
		VBox vboxData = Components.VBoxData();
		
		border.setTop(hboxResearch);
		border.setLeft(vboxData);
	    
		Components.addStackPaneResearch(hboxResearch, null, null);
		
		Scene sceneSignIn = new Scene(border,1000,600);
		
		SigninStage.setTitle("Welcome in your account");
		SigninStage.setScene(sceneSignIn);
		SigninStage.show();
		
	}
}
