package com.rossettimonicadigiorgio.winestoremanagementv2.backend.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.rossettimonicadigiorgio.winestoremanagementv2.backend.MySQLConnection;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Order;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.StatusEnum;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.User;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Wine;

public class OrderController {
	public static ArrayList<Order> getAllOrders() {
		try {
			Statement stmt =  MySQLConnection.establishConnection().createStatement();
			
			String query = "SELECT * FROM orders";
			
			ResultSet rset = stmt.executeQuery(query);
			
			ArrayList<Order> result = new ArrayList<Order>();
			
			while (rset.next()) {
				int idOrder = rset.getInt("IDOrder");
				int idStatus = rset.getInt("Status");
				int idUser = rset.getInt("User");
				
				User user = UserController.getUserByID(idUser);
				StatusEnum status = StatusEnum.values()[idStatus - 1];
				
				query = "SELECT * FROM orderwines WHERE OrderNumber = " + idOrder;
				
				Statement stmtWines = MySQLConnection.establishConnection().createStatement();
				ResultSet rsetWines = stmtWines.executeQuery(query);
				
				ArrayList<Wine> wines = new ArrayList<Wine>();
				
				while(rsetWines.next()) {
					int idWine = rsetWines.getInt("Wine");
					int orderBottles = rsetWines.getInt("BottlesNumber");
					
					Wine wine = WineController.getWineByID(idWine);
					
					wine.setBottlesNumber(orderBottles);
					
					wines.add(wine);
				}
				
				Order order = new Order(idOrder, status, user, wines);	
				
				result.add(order);
			}
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
