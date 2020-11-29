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
	
	Person user;

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
			Login();	
		}
		);
		 
		Button btnRegister = new Button();
		btnRegister.setText("Register");
		btnRegister.setPrefSize(100, 20);
		btnRegister.setOnAction(event->
		{
			Register();	
		}
		);

		hbox.getChildren().addAll(btnLogin,btnRegister);
		return hbox;
	}
	
	public void Login()
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
        			
        			user = (User) response.getValue();
        			
        			
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
        			
        			user = (Administrator) response.getValue();
        			
        			SignInAdmin();
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
        			
        			user = (Employee) response.getValue();
        			
        			SignInEmployee();
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
	
    private void SignInUser() {
		
		Stage SigninStage = new Stage();
		BorderPane border = new BorderPane();
		
		HBox hboxResearch = HboxResearch(SigninStage);
		VBox vboxData = VBoxData();
		GridPane gridData = GridData();
		border.setTop(hboxResearch);
		border.setLeft(vboxData);
		border.setCenter(gridData);
		addStackPaneResearch(hboxResearch);
		
		Scene sceneSignIn = new Scene(border,1000,600);
		
		SigninStage.setTitle("Welcome in your account");
		SigninStage.setScene(sceneSignIn);
		SigninStage.show();
		
	}

	private void SignInEmployee() {
		
		Stage SigninStage = new Stage();
		BorderPane border = new BorderPane();
		
		HBox hboxResearch = HboxResearch(SigninStage);
		VBox vboxData = VBoxDataEmployee();
		GridPane gridData = GridDataEmployee();
		border.setTop(hboxResearch);
		border.setLeft(vboxData);
		border.setRight(gridData);
		
		Scene sceneSignIn = new Scene(border,1000,600);
		
		SigninStage.setTitle("Welcome in your account");
		SigninStage.setScene(sceneSignIn);
		SigninStage.show();
	}

	private GridPane GridDataEmployee() {
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0,10,0,10));
		
		PieChart chartWine = new PieChart(FXCollections.observableArrayList(new PieChart.Data("UX",50),new PieChart.Data("UY",20),new PieChart.Data("UZ",30)));
		chartWine.setTitle("WINES AVAILABLE");
		grid.getChildren().add(chartWine);
		return grid;
	}

	private void SignInAdmin() {
		
		Stage SigninStage = new Stage();
		BorderPane border = new BorderPane();
		
		HBox hboxResearch = HboxResearch(SigninStage);
		VBox vboxData = VBoxData();
		
		border.setTop(hboxResearch);
		border.setLeft(vboxData);
	
		addStackPaneResearch(hboxResearch);
		
		Scene sceneSignIn = new Scene(border,1000,600);
		
		SigninStage.setTitle("Welcome in your account");
		SigninStage.setScene(sceneSignIn);
		SigninStage.show();
		
	}

	private void ControlRdb(RadioButton rdbUser, RadioButton rdbAdmin, RadioButton rdbEmployee)  {
			
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

	
	private GridPane GridData() {
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(30,100,100,100));
		
		TableView<Wine> tbvWine = new TableView<Wine>();
		TableColumn<Wine, String> nameCol = new TableColumn<Wine, String>("Name");	
		nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
		
		TableColumn<Wine, String> producerCol = new TableColumn<Wine, String>("Producer");
		producerCol.setCellValueFactory(new PropertyValueFactory<>("Producer"));
		
		TableColumn<Wine, Integer> yearCol = new TableColumn<Wine, Integer>("Year");
		yearCol.setCellValueFactory(new PropertyValueFactory<>("Year"));
		
		TableColumn<Wine, Double> priceCol = new TableColumn<Wine, Double>("Price");
		priceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
		
		TableColumn<Wine, Integer> numberCol = new TableColumn<Wine, Integer>("Bottles Number");
		numberCol.setCellValueFactory(new PropertyValueFactory<>("BottlesNumber"));
		
		ArrayList<Object> params = new ArrayList<Object>();
		params.add("");
		
		ArrayList<Wine> result = (ArrayList<Wine>) new Client().run(new Request("filterWines", params)).getValue();
		
		tbvWine.getColumns().addAll(nameCol,producerCol,yearCol,priceCol,numberCol);

	    tbvWine.getItems().addAll(result);
	    tbvWine.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);	
		
		grid.getChildren().add(tbvWine);
		return grid;
	}

	private void addStackPaneResearch(HBox hboxResearch) {
		StackPane stack = new StackPane();
		TextField txtResearch = new TextField();
		Button btnResearch = new Button("Research");
		txtResearch.setPrefSize(350, 20);
		stack.getChildren().addAll(txtResearch,btnResearch);
		stack.setAlignment(Pos.TOP_RIGHT);
		hboxResearch.getChildren().add(stack);
	}

	private VBox VBoxData() {
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10));
		vbox.setSpacing(8);
		
		Text title = new Text("Menu'");
		title.setFont(Font.font("Arial",FontWeight.BOLD,15));
		vbox.getChildren().add(title);
		
		Hyperlink[] options = new Hyperlink[] {new Hyperlink("My Orders"),new Hyperlink("My Account"),new Hyperlink("My Notifications") };
		for(int i = 0; i<3;i++)
		{
			vbox.getChildren().add(options[i]);
		}
		options[0].setOnAction(event -> { MyOrder();});
		options[1].setOnAction(event -> {MyAccount();});
		options[2].setOnAction(event -> {MyNot();});
		
		return vbox ;
	}
	
	private VBox VBoxDataEmployee() {
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
		options[0].setOnAction(event -> {Ship();});
		options[1].setOnAction(event -> {RestockBottle();});
		
		return vbox ;
	}


	private void RestockBottle() {
		
		Stage stageRestock = new Stage();
		BorderPane borderRestock = new BorderPane();
		borderRestock.setStyle("-fx-background-color:  #ABCDEF;");
		Scene sceneRestock = new Scene (borderRestock,500,500);
		stageRestock.setScene(sceneRestock);
		stageRestock.show();
		
	}

	private void Ship() {
		
		Stage stageShip = new Stage();
		BorderPane borderShip = new BorderPane();
		borderShip.setStyle("-fx-background-color:  #ABCDEF;");
		Scene sceneShip = new Scene (borderShip,500,500);
		stageShip.setScene(sceneShip);
		stageShip.show();
		
	}

	private void MyNot() {
		
		Stage stageMyNot = new Stage();
		BorderPane borderMyNot = new BorderPane();
		borderMyNot.setStyle("-fx-background-color:  #ABCDEF;");
		Scene sceneMyNot = new Scene (borderMyNot,500,500);
		stageMyNot.setScene(sceneMyNot);
		stageMyNot.show();
	}

	private void MyAccount() {
		
		Stage stageMyAccount = new Stage();
		BorderPane borderMyAccount = new BorderPane();
		borderMyAccount.setStyle("-fx-background-color:  #ABCDEF;");
		Scene sceneMyAccount = new Scene (borderMyAccount,500,500);
		stageMyAccount.setScene(sceneMyAccount);
		stageMyAccount.show();
		
	}

	private void MyOrder() {
		
		Stage stageMyOrder = new Stage();
		BorderPane borderMyOrder = new BorderPane();
		borderMyOrder.setStyle("-fx-background-color:  #ABCDEF;");
		Scene sceneMyOrder = new Scene (borderMyOrder,500,500);
		stageMyOrder.setScene(sceneMyOrder);
		stageMyOrder.show();
	}

	private HBox HboxResearch(Stage logoutStage) {
		
		HBox hbox = new HBox();
		
		Label lblName = new Label(user.getName() + " " + user.getSurname());
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
	
	public void Register()
	{
		Stage registerStage = new Stage();
		GridPane grid = new GridPane();
		Scene sceneRegister = new Scene (grid,300,300);
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
        		
        		this.user = (User) response.getValue();
        		
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
	 * Point of entry for the application
	 * @param args the arguments for the application to run
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
