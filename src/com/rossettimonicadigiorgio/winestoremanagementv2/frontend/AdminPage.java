package com.rossettimonicadigiorgio.winestoremanagementv2.frontend;

import java.util.ArrayList;

import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Employee;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Order;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Request;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.User;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Wine;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The {@code AdminPage} is a class that defines:
 * The graphic of the AdminPage
 * @author 297402
 *
 */
public class AdminPage {
	
	/**
	 * The method SignInAdmin 
	 * When pressed the button takes you to a new page 
	 */
	public static void SignInAdmin() {
		
		Stage SigninStage = new Stage();
		BorderPane border = new BorderPane();
		
		HBox hboxResearch = Components.HboxResearch(SigninStage);
		VBox vboxData = AdminPage.VBoxDataAdmin();
		GridPane gridData= AdminPage.GridDataAdmin();
		
		border.setTop(hboxResearch);
		border.setLeft(vboxData);
	    border.setRight(gridData);
		
		
		Scene sceneSignIn = new Scene(border,1000,600);
		
		SigninStage.setTitle("Welcome in your account");
		SigninStage.setScene(sceneSignIn);
		SigninStage.show();
		
	}
	
	private static GridPane GridDataClients(String string) {
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		
		TableView<User> tbvClient = new TableView<User>();
		
		TableColumn<User, String> nameCol = new TableColumn<User, String>("Name");	
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<User, String> surnameCol = new TableColumn<User, String>("Surname");
		surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
		
		TableColumn<User, Integer> emailCol = new TableColumn<User, Integer>("Email");
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		
		TableColumn<User, Integer> psswCol = new TableColumn<User, Integer>("Password");
		psswCol.setCellValueFactory(new PropertyValueFactory<>("password"));
		
		
        ArrayList<User> result = (ArrayList<User>) new Client().run(new Request("listUsers", null)).getValue();
		
		tbvClient.getColumns().addAll(nameCol,surnameCol,emailCol,psswCol);
		
		tbvClient.getItems().clear();
	    tbvClient.getItems().addAll(result);
	    tbvClient.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);	
		
	    grid.getChildren().add(tbvClient);
	    
		return grid;
	}
	private static GridPane GridDataEmployee(String string) {
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		
		TableView<Employee> tbvEmployee = new TableView<Employee>();
		
		TableColumn<Employee, String> nameCol = new TableColumn<Employee, String>("Name");	
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<Employee, String> surnameCol = new TableColumn<Employee, String>("Surname");
		surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
		
		TableColumn<Employee, Integer> emailCol = new TableColumn<Employee, Integer>("Email");
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		
		TableColumn<Employee, Integer> psswCol = new TableColumn<Employee, Integer>("Password");
		psswCol.setCellValueFactory(new PropertyValueFactory<>("password"));
		
		
        ArrayList<Employee> result = (ArrayList<Employee>) new Client().run(new Request("listEmployees", null)).getValue();
		
		tbvEmployee.getColumns().addAll(nameCol,surnameCol,emailCol,psswCol);
		
		tbvEmployee.getItems().clear();
	    tbvEmployee.getItems().addAll(result);
	    tbvEmployee.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);	
		
	    grid.getChildren().add(tbvEmployee);
	    
		return grid;
	}
	private static GridPane GridDataOrders(String string) {
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		
		TableView<Order> tbvOrder = new TableView<Order>();
		
		TableColumn<Order, String> numordCol = new TableColumn<Order, String>("Number Order");	
		numordCol.setCellValueFactory(new PropertyValueFactory<>("IDOrder"));
		
		TableColumn<Order, String> statusCol = new TableColumn<Order, String>("Status");
		statusCol.setCellValueFactory(new PropertyValueFactory<>("Status"));
		
		TableColumn<Order, Integer> itemsCol = new TableColumn<Order, Integer>("Number Items");
		itemsCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getWines().size()).asObject());
		
        ArrayList<Order> result = (ArrayList<Order>) new Client().run(new Request("listOrders", null)).getValue();
		
		tbvOrder.getColumns().addAll(numordCol,statusCol, itemsCol);
		
		tbvOrder.getItems().clear();
	    tbvOrder.getItems().addAll(result);
	    tbvOrder.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);	
		
	    grid.getChildren().add(tbvOrder);
	    
		return grid;
	}
    private static GridPane GridDataWines(String filter) {
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		
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
		params.add(filter);
		
		ArrayList<Wine> result = (ArrayList<Wine>) new Client().run(new Request("filterWines", params)).getValue();
		
		tbvWine.getColumns().addAll(nameCol,producerCol,yearCol,priceCol,numberCol);
		
		tbvWine.getItems().clear();
	    tbvWine.getItems().addAll(result);
	    tbvWine.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);	
		
		grid.getChildren().add(tbvWine);
		return grid;
	}
    
	public static GridPane GridDataAdmin() {
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0,200,50,50));
		
		PieChart chartWine = new PieChart(FXCollections.observableArrayList(new PieChart.Data("UX",50),new PieChart.Data("UY",20),new PieChart.Data("UZ",30)));
		//Elenco vini e quantità nella observable List
		chartWine.setTitle("WINES AVAILABLE");
		grid.getChildren().add(chartWine);
		return grid;
	}
	
	public static VBox VBoxDataAdmin() {
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10));
		vbox.setSpacing(8);
		
		Text title = new Text("Menu'");
		title.setFont(Font.font("Arial",FontWeight.BOLD,15));
		vbox.getChildren().add(title);
		
		Hyperlink[] options = new Hyperlink[] {new Hyperlink("Add Employee"),new Hyperlink("Info Clients"),new Hyperlink("Info Employees"),new Hyperlink("Info Wines"),new Hyperlink("Info Orders")};
		for(int i = 0; i<5;i++)
		{
			vbox.getChildren().add(options[i]);
		}
		options[0].setOnAction(event -> { AdminPage.AddEmployee(); });
		options[1].setOnAction(event -> { AdminPage.InfoClients(); });
		options[2].setOnAction(event -> { AdminPage.InfoEmployees(); });
		options[3].setOnAction(event -> { AdminPage.InfoWines(); });
		options[4].setOnAction(event -> { AdminPage.InfoOrders(); });
		
		return vbox;
	}

	private static void InfoOrders() {
		
		Stage stageInfOrd = new Stage();
		BorderPane borderInfOrd = new BorderPane();
		borderInfOrd.setStyle("-fx-background-color:  #ABCDEF;");
		Scene sceneInfOrd = new Scene (borderInfOrd,500,500);
		stageInfOrd.setTitle("Summary Orders");
		
		borderInfOrd.setPadding(new Insets(50,50,0,100));
		borderInfOrd.setCenter(AdminPage.GridDataOrders(""));
		
		stageInfOrd.setScene(sceneInfOrd);
		stageInfOrd.show();
	}

	private static void InfoWines() {
		
		Stage stageInfWin = new Stage();
		BorderPane borderInfWin = new BorderPane();
		borderInfWin.setStyle("-fx-background-color:  #ABCDEF;");
		Scene sceneInfWin = new Scene (borderInfWin,500,500);
		stageInfWin.setTitle("Summary Wines");
		
		borderInfWin.setPadding(new Insets(50,50,50,50));
		borderInfWin.setCenter(AdminPage.GridDataWines(""));
		
		stageInfWin.setScene(sceneInfWin);
		stageInfWin.show();
	}

	private static void InfoEmployees() {
		
		Stage stageInfEmp = new Stage();
		BorderPane borderInfEmp = new BorderPane();
		borderInfEmp.setStyle("-fx-background-color:  #ABCDEF;");
		Scene sceneInfEmp = new Scene (borderInfEmp,500,500);
		
		borderInfEmp.setPadding(new Insets(50,50,50,50));
		borderInfEmp.setCenter(AdminPage.GridDataEmployee(""));
		
		stageInfEmp.setScene(sceneInfEmp);
		stageInfEmp.show();
		
	}

	private static void InfoClients() {
		Stage stageInfCli = new Stage();
		BorderPane borderInfCli = new BorderPane();
		borderInfCli.setStyle("-fx-background-color:  #ABCDEF;");
		Scene sceneInfCli = new Scene (borderInfCli,500,500);
		
		borderInfCli.setPadding(new Insets(50,50,50,50));
		borderInfCli.setCenter(AdminPage.GridDataClients(""));
		
		stageInfCli.setScene(sceneInfCli);
		stageInfCli.show();
		
	}

	private static void AddEmployee() {
		Stage stageAddEmp = new Stage();
		stageAddEmp.setTitle("Set Account Employee");
		BorderPane borderAddEmp = new BorderPane();
		borderAddEmp.setStyle("-fx-background-color:  #ABCDEF;");
		Scene sceneAddEmp = new Scene (borderAddEmp,500,500);
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(10);
		grid.setPadding(new Insets(25,25,25,25));
		 
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
        
        Button btnAddEmp = new Button("Submit");
        grid.add(btnAddEmp, 1, 5);
        
        btnAddEmp.setOnAction(event -> {
        	Employee employee = new Employee(-1, txtUser.getText(), txtSurname.getText(), txtEmail.getText(), txtPassword.getText());
        	
    		ArrayList<Object> params = new ArrayList<Object>();
    		params.add(employee);
        	
        	if((boolean) new Client().run(new Request("employeeRegister", params)).getValue()) {
				Alert addEmployee = new Alert(AlertType.INFORMATION);
				addEmployee.setTitle("Employee Added");
				addEmployee.setHeaderText("SUCCESS");
				addEmployee.setContentText("The operation has completed successfully");
				addEmployee.showAndWait();
        	} else {
        		Alert addEmployee = new Alert(AlertType.ERROR);
				addEmployee.setTitle("Employee Added");
				addEmployee.setHeaderText("ERROR");
				addEmployee.setContentText("The operation is failed!");
				addEmployee.showAndWait();
        	}
        	
        	stageAddEmp.close();
        });
        
        borderAddEmp.setCenter(grid);
		
		stageAddEmp.setScene(sceneAddEmp);
		stageAddEmp.show();
	}
}
