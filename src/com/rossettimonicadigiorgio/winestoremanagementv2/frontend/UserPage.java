package com.rossettimonicadigiorgio.winestoremanagementv2.frontend;

import java.util.ArrayList;

import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Request;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Wine;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class UserPage {
	static 	ArrayList<Wine> listRecorderOrder =new ArrayList<Wine>();
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
						Wine toAdd = row.getItem();
						toAdd.setBottlesNumber(1);
						MainClient.listWineCart.add(toAdd);
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
		
	public static FlowPane ShoppingCart() {			

		FlowPane flowWine = new FlowPane();
		flowWine.setPadding(new Insets(15,8,0,25));
		
		if(MainClient.listWineCart.size() <= 0)
			return flowWine;
		ObservableList<Wine> oblWineCart = FXCollections.observableArrayList();;
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
			
			Alert ReviewOrder = new Alert(AlertType.INFORMATION);
			ReviewOrder.setTitle("Status Order");
			ReviewOrder.setHeaderText("THANK YOU");
			ReviewOrder.setContentText("your order was successful!");
			ReviewOrder.showAndWait();
			lsvWineCart.getItems().clear();
			listRecorderOrder.addAll(MainClient.listWineCart);
			MainClient.listWineCart.clear();
			
			  
		});
		
		
		return flowWine;
	}
		
}
