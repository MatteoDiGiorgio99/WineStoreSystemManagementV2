package com.rossettimonicadigiorgio.winestoremanagementv2.frontend;

import java.util.ArrayList;

import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Notification;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Order;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Request;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Response;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.StatusEnum;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.User;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Wine;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The {@code UserPage} is a class that defines:
 * User page
 * 
 * @author 297402
 *
 */
public class UserPage {
	
	/**
	 * Global variable
	 */
	static Order lastOrder;
	
	/**
	 * Global ArrayList
	 */
	static ArrayList<Notification> userNotifications = new ArrayList<Notification>();
	
	/**
	 * The method SignInUser
	 * allows the user to handle orders, search and buy new wines 
	 */
    public static void SignInUser() {
		
		MainClient.SigninStage = new Stage();
		BorderPane border = new BorderPane();
		
		HBox hboxResearch = Components.HboxResearch(MainClient.SigninStage);
		VBox vboxData = UserPage.VBoxData();
		FlowPane flowData = UserPage.ShoppingCart();
		
		
		GridPane gridData = UserPage.GridData("", flowData);
		gridData.setPadding(new Insets(30,0,100,100));
		
		
		border.setTop(hboxResearch);
		border.setLeft(vboxData);
		border.setCenter(gridData);
		border.setRight(flowData);
		
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(MainClient.user.getIDPerson());
		
		UserPage.userNotifications = (ArrayList<Notification>) new Client().run(new Request("listNotification", params)).getValue();
		
		params = new ArrayList<Object>();
		params.add(MainClient.user.getIDPerson());
		UserPage.lastOrder = (Order) new Client().run(new Request("listOrdersForUser", params)).getValue();
		
		Components.addStackPaneResearch(hboxResearch, gridData, flowData);
				
		Scene sceneSignIn = new Scene(border,1000,600);
		
		MainClient.SigninStage.setTitle("Welcome in your account");
		MainClient.SigninStage.setScene(sceneSignIn);
		MainClient.SigninStage.show();
	}
	
    /**
     * The method GridData
     * @param filter the filter to search for a specific type of wine
     * @param cartContainer the contents of the cart
     * @return Updated table
     */
	public static GridPane GridData(String filter, FlowPane cartContainer) {
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
		
		tbvWine.setRowFactory(tv -> {
			TableRow<Wine> row = new TableRow<>();
			
			row.setOnMouseClicked(event -> {
				if(event.getClickCount() == 2 && (!row.isEmpty())) {
					boolean isInList = false;
					for (Wine wine : MainClient.listWineCart) {
						if(wine.EqualTo(row.getItem())) {
							isInList = true;
							wine.setBottlesNumber(wine.getBottlesNumber() + 1);
							break;
						}
					}
					
					if(!isInList) {
						if(row.getItem().getBottlesNumber() == 0) {
							Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
							alert.setTitle("Notification Request");
							alert.setHeaderText("There are no more bottles of this wine in stock.");
							alert.setContentText("Do you want to get a notification?");
							ButtonType okButton = new ButtonType("YES", ButtonBar.ButtonData.YES);
							ButtonType noButton = new ButtonType("NO", ButtonBar.ButtonData.NO);
							alert.getButtonTypes().setAll(okButton, noButton);
							alert.showAndWait().ifPresent(type -> {
								if(type.getText() == "YES") {
									Notification notification = new Notification(-1, (User) MainClient.user, row.getItem(), false);
									ArrayList<Object> params = new ArrayList<Object>();
									params.add(notification);
																		
									Response response = new Client().run(new Request("insertNotification", params));
									
									UserPage.userNotifications.add((Notification) response.getValue());
								}
							});
						} else {
							Wine toAdd = row.getItem();
							toAdd.setBottlesNumber(1);
							MainClient.listWineCart.add(toAdd);	
						}
					}
					
					cartContainer.getChildren().clear();
					cartContainer.getChildren().add(ShoppingCart());
				}
			});
			
			return row;
		});		
		
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
	
	/**
	 * The method ShoppingCart
	 * allows the user to buy wines selected 
	 * @return the cart updated 
	 */
	public static FlowPane ShoppingCart() {			

		FlowPane flowWine = new FlowPane();
		flowWine.setPadding(new Insets(15,8,0,25));
		
		if(MainClient.listWineCart.size() <= 0)
			return flowWine;
		
		ObservableList<Wine> oblWineCart = FXCollections.observableArrayList();
		oblWineCart.addAll(MainClient.listWineCart);
		ListView<Wine> lsvWineCart = new ListView<Wine>(oblWineCart);
		
		Button btnBuyNow = new Button("Buy Now");
		Button btnCancel = new Button("Cancel");
		VBox vbxBuyCancel = new VBox();
		vbxBuyCancel.getChildren().addAll(btnBuyNow,btnCancel);
		flowWine.getChildren().addAll(lsvWineCart,vbxBuyCancel);
		
		btnCancel.setOnAction(event -> {
			
			lsvWineCart.getItems().clear();
			MainClient.listWineCart.clear();
			
		});
		
		btnBuyNow.setOnAction(event->{
			Order order = new Order(-1, StatusEnum.Confirmed, (User) MainClient.user, MainClient.listWineCart);
			
			ArrayList<Object> params = new ArrayList<Object>();
			params.add(order);
			
			UserPage.lastOrder = (Order) new Client().run(new Request("insertOrder", params)).getValue(); 
			if(UserPage.lastOrder.getWines().size() == MainClient.listWineCart.size()) {
				Alert ReviewOrder = new Alert(AlertType.INFORMATION);
				ReviewOrder.setTitle("Status Order");
				ReviewOrder.setHeaderText("THANK YOU");
				ReviewOrder.setContentText("your order was successful!");
				ReviewOrder.showAndWait();	
			} else {
				Alert ReviewOrder = new Alert(AlertType.ERROR);
				ReviewOrder.setTitle("Status Order");
				ReviewOrder.setHeaderText("SORRY");
				ReviewOrder.setContentText("your order was not successfull!");
				ReviewOrder.showAndWait();
			}
			
			lsvWineCart.getItems().clear();
			MainClient.listWineCart.clear();
			MainClient.SigninStage.close();
			UserPage.SignInUser();	
		});
			
		return flowWine;
	}
	
	/**
	 * The method MyAccount 
	 * allows the users to see their accounts
	 */
	public static void MyAccount() {
		Stage stageMyAccount = new Stage();
		stageMyAccount.setTitle("Summary Account");
		BorderPane borderMyAccount = new BorderPane();
		borderMyAccount.setStyle("-fx-background-color:  #ABCDEF;");
		Scene sceneMyAccount = new Scene (borderMyAccount,500,500);
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(10);
		grid.setPadding(new Insets(25,25,25,25));
		 
        Label lblUser = new Label("Name: ");
        grid.add(lblUser, 0, 1);
        TextField txtUser = new TextField();
        txtUser.setEditable(false);
        txtUser.setText(MainClient.user.getName());;
        grid.add(txtUser,1,1);
        
        Label lblSurname = new Label("Surname: ");
        grid.add(lblSurname, 0, 2);
        TextField txtSurname = new TextField();
        txtSurname.setEditable(false);
        txtSurname.setText(MainClient.user.getSurname());
        grid.add(txtSurname,1,2);
        
        Label lblEmail = new Label("Email: ");
        grid.add(lblEmail, 0, 3);
        TextField txtEmail = new TextField();
        txtEmail.setEditable(false);
        txtEmail.setText(MainClient.user.getEmail());
        grid.add(txtEmail,1,3);
        
        Label lblPassword = new Label("Password: ");
        grid.add(lblPassword, 0,4);
        TextField txtPassword = new TextField();
        txtPassword.setEditable(false);
        txtPassword.setText(MainClient.user.getPassword());
        grid.add(txtPassword,1,4);
        
        borderMyAccount.setCenter(grid);
		
		stageMyAccount.setScene(sceneMyAccount);
		stageMyAccount.show();
	}
	/**
	 * The method MyNot
	 * allows users to see their notification
	 */
	public static void MyNot() {
		Stage stageMyNot = new Stage();
		BorderPane borderMyNot = new BorderPane();
		borderMyNot.setStyle("-fx-background-color:  #ABCDEF;");
		Scene sceneMyNot = new Scene (borderMyNot,500,500);
		
		ObservableList<Notification> oblUserNotification = FXCollections.observableArrayList();
		oblUserNotification.addAll(UserPage.userNotifications);
		
		ListView<Notification> listNot = new ListView<Notification>(oblUserNotification);
		borderMyNot.setCenter(listNot);
		
		stageMyNot.setScene(sceneMyNot);
		stageMyNot.show();
	}
	
	/**
	 * The method LastOrder 
	 * allows user to see last orders 
	 */
	public static void LastOrder() {
		if(UserPage.lastOrder == null)
			return;
		
		Stage stageMyOrder = new Stage();
		BorderPane borderMyOrder = new BorderPane();
		borderMyOrder.setStyle("-fx-background-color:  #ABCDEF;");
		Scene sceneMyOrder = new Scene (borderMyOrder,500,500);
		
		ObservableList<Wine> oblWineCart = FXCollections.observableArrayList();
		
		oblWineCart.addAll(UserPage.lastOrder.getWines());
		
		ListView<Wine>lsvWineCartOrder = new ListView<Wine>(oblWineCart);
		
		
		borderMyOrder.setCenter(lsvWineCartOrder);
		
		stageMyOrder.setScene(sceneMyOrder);
		stageMyOrder.show();
	}
	
	/**
	 * The method VBoxData
	 * allows the user to see the latest orders,notifications and his account 
	 */
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
		
		options[0].setOnAction(event -> { UserPage.LastOrder(); });
		options[1].setOnAction(event -> { UserPage.MyAccount(); });
		options[2].setOnAction(event -> { UserPage.MyNot(); });
	
		return vbox ;
	}
}
