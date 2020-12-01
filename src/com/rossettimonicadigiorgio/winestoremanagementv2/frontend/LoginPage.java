package com.rossettimonicadigiorgio.winestoremanagementv2.frontend;

import java.util.ArrayList;

import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Administrator;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Employee;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Request;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Response;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.User;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The {@code LoginPage} is a class that defines:
 * The graphic of the LoginPage
 * @author 297398
 *
 */
public class LoginPage {
	
	/**
	 * The method ControlRdb
	 * controls the type of user tries to Login
	 */
	private static void ControlRdb(RadioButton rdbUser, RadioButton rdbAdmin, RadioButton rdbEmployee)  {
		
		if(rdbUser.isSelected()==true)
		{
			rdbAdmin.setSelected(false);
			rdbEmployee.setSelected(false);
		}
		else if (rdbAdmin.isSelected()==true)
		{
			rdbUser.setSelected(false);
			rdbEmployee.setSelected(false);
		}
		else
		{
			rdbUser.setSelected(false);
			rdbAdmin.setSelected(false);
		}
		
	}
	
	/**
	 * The method Login
	 * When pressed the button take you to a new page 
	 */
	public static void Login()
	{
		Stage loginStage = new Stage();
		GridPane grid = new GridPane();
		Scene sceneLogin = new Scene (grid,350,350);
		grid.setStyle("-fx-background-color: #ABCDEF;");
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(10);
		grid.setPadding(new Insets(25,25,25,25));
        loginStage.setTitle("Login");
        
        Label lblUser = new Label("User Name: ");
        grid.add(lblUser, 0, 1);
        TextField txtUser = new TextField();
        grid.add(txtUser,1,1);
       
        Label lblPassword = new Label("Password: ");
        grid.add(lblPassword, 0, 2);
        PasswordField txtPassword = new PasswordField();
        grid.add(txtPassword,1,2);
        
        
        RadioButton rdbUser = new RadioButton("User");
        RadioButton rdbAdmin = new RadioButton("Admin");
        RadioButton rdbEmployee = new RadioButton("Employee");
        rdbUser.setSelected(true);
        
        ToggleGroup grouprdb = new ToggleGroup();
        rdbUser.setToggleGroup(grouprdb);
        rdbAdmin.setToggleGroup(grouprdb);
        rdbEmployee.setToggleGroup(grouprdb);
        grouprdb.selectedToggleProperty().addListener(event -> {ControlRdb(rdbUser,rdbAdmin,rdbEmployee);});
       	
        HBox hboxRdb = new HBox(10);
        hboxRdb.setStyle("-fx-padding:10;"+"-fx-border-color:black;"+"-fx-border-style: solid inside;");
        hboxRdb.getChildren().addAll(rdbUser,rdbAdmin,rdbEmployee);
        grid.add(hboxRdb,1,4);
         
        Button btnSignIn = new Button("Sign In");
        HBox hbtn = new HBox(10);
        hbtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbtn.getChildren().add(btnSignIn);
        grid.add(hbtn,1,5);
         
        btnSignIn.setOnAction(event ->{
        	
        	if(txtUser.getText().length() != 0 && txtPassword.getText().length() != 0)
        	{
        		if(rdbUser.isSelected()==true && rdbAdmin.isSelected()==false && rdbEmployee.isSelected()==false)
        		{
        			ArrayList<Object> params = new ArrayList<Object>();
        			params.add(txtUser.getText());
        			params.add(txtPassword.getText());
        			Request request = new Request("userLogin",params);
        			
        			Response response = new Client().run(request);
        			
        			MainClient.user = (User) response.getValue();
        			
        			
            		SignInUser();
            		txtUser.clear();
            		txtPassword.clear();
        		}
        		else if(rdbUser.isSelected()==false && rdbAdmin.isSelected()==true && rdbEmployee.isSelected()==false)
        		{
        			ArrayList<Object> params = new ArrayList<Object>();
        			params.add(txtUser.getText());
        			params.add(txtPassword.getText());
        			Request request = new Request("administratorLogin",params);
        			
        			Response response = new Client().run(request);
        			
        			MainClient.user = (Administrator) response.getValue();
        			
        			AdminPage.SignInAdmin();
        			txtUser.clear();
            		txtPassword.clear();
        		}
        		else
        		{
        			ArrayList<Object> params = new ArrayList<Object>();
        			params.add(txtUser.getText());
        			params.add(txtPassword.getText());
        			Request request = new Request("employeeLogin",params);
        			
        			Response response = new Client().run(request);
        			
        			MainClient.user = (Employee) response.getValue();
        			
        			EmployeePage.SignInEmployee();
        			txtUser.clear();
            		txtPassword.clear();
        		}
        	}
        	else
        	{
        		Text actiontarget = new Text();
        		grid.add(actiontarget, 1, 6);
        		actiontarget.setFill(Color.FIREBRICK);
        		actiontarget.setText("FILL ALL FIELD BEFORE SIGN IN!!!!");
        	}
        	
        	});
      
        
        Button btnBack =new Button("Back");
        HBox hboxBack = new HBox(10);
        hboxBack.setAlignment(Pos.BOTTOM_RIGHT);
        hboxBack.getChildren().add(btnBack);
        grid.add(hboxBack,0,5);
        btnBack.setOnAction(event -> loginStage.close());
        
        loginStage.setScene(sceneLogin);
        loginStage.show();
        
	}
	
	/**
	 * The method Register
	 * When pressed the button take you to a new page 
	 */
	public static void Register()
	{
		Stage registerStage = new Stage();
		GridPane grid = new GridPane();
		Scene sceneRegister = new Scene (grid,350,350);
		grid.setStyle("-fx-background-color: #ABCDEF;");
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(10);
		grid.setPadding(new Insets(25,25,25,25));
        registerStage.setTitle("Register");
        
        Label lblUser = new Label("Name: ");
        grid.add(lblUser, 0, 1);
        TextField txtUser = new TextField();
        grid.add(txtUser,1,1);
        
        Label lblSurname = new Label("Surname: ");
        grid.add(lblSurname, 0, 2);
        TextField txtSurname = new TextField();
        grid.add(txtSurname,1,2);
        
        Label lblEmail = new Label("Email: ");
        grid.add(lblEmail, 0, 3);
        TextField txtEmail = new TextField();
        grid.add(txtEmail,1,3);
        
        Label lblPassword = new Label("Password: ");
        grid.add(lblPassword, 0,4);
        TextField txtPassword = new TextField();
        grid.add(txtPassword,1,4);
        
        Button btnRegister = new Button("Click to register");
        HBox hboxRegister = new HBox(10);
        hboxRegister.setAlignment(Pos.BOTTOM_RIGHT);
        hboxRegister.getChildren().add(btnRegister);
        grid.add(hboxRegister,1,5);
        btnRegister.setOnAction(event -> {
        	if(txtUser.getText().length() != 0 && txtSurname.getText().length() != 0 && txtEmail.getText().length()!=0 && txtPassword.getText().length()!=0)
        	{
        		User user = new User(-1, txtUser.getText(), txtSurname.getText(), txtEmail.getText(), txtPassword.getText());       		
        		
        		ArrayList<Object> params = new ArrayList<Object>();
        		params.add(user);
        		
        		Request request = new Request("userRegister", params);
        		     		
        		Response response = new Client().run(request);
        		
        		MainClient.user = (User) response.getValue();
        		
        		SignInUser();
        		
        		txtUser.clear();
        		txtSurname.clear();
        		txtEmail.clear();
        		txtPassword.clear();
        	}
        	else
        	{
        		Text actiontarget = new Text();
        		grid.add(actiontarget, 1, 6);
        		actiontarget.setFill(Color.FIREBRICK);
        		actiontarget.setText("FILL ALL FIELD BEFORE SIGN IN!!!!");
        	}
        });
        
        Button btnBack =new Button("Back");
        HBox hboxBack = new HBox(10);
        hboxBack.setAlignment(Pos.BOTTOM_RIGHT);
        hboxBack.getChildren().add(btnBack);
        grid.add(hboxBack,0,5);
        btnBack.setOnAction(event -> registerStage.close());
        
        registerStage.setScene(sceneRegister);
        registerStage.show();
	}
	
	/**
	 * The method SignInUser
	 * allows the user to handle orders, search and buy new wines 
	 */
    public static void SignInUser() {
		
		MainClient.SigninStage = new Stage();
		BorderPane border = new BorderPane();
		
		HBox hboxResearch = Components.HboxResearch(MainClient.SigninStage);
		VBox vboxData = Components.VBoxData();
		FlowPane flowData = UserPage.ShoppingCart();
		
		
		GridPane gridData = UserPage.GridData("", flowData);
		gridData.setPadding(new Insets(30,0,100,100));
		
		
		border.setTop(hboxResearch);
		border.setLeft(vboxData);
		border.setCenter(gridData);
		border.setRight(flowData);
	
		
		Components.addStackPaneResearch(hboxResearch, gridData, flowData);
				
		Scene sceneSignIn = new Scene(border,1000,600);
		
		MainClient.SigninStage.setTitle("Welcome in your account");
		MainClient.SigninStage.setScene(sceneSignIn);
		MainClient.SigninStage.show();
	}
}
