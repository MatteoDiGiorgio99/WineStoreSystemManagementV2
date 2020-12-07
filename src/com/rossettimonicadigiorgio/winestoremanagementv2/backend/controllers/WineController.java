package com.rossettimonicadigiorgio.winestoremanagementv2.backend.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.rossettimonicadigiorgio.winestoremanagementv2.backend.MySQLConnection;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Wine;

/**
 * The {@code WineController} is a class that defines:
 * a controller for the wines
 * 
 * @author 296666
 *
 */
public class WineController {
	
	/**
	 * The method getAllWines
	 * allows to show a list of all wines
	 * 
	 * @return return the list of all wines
	 */
	public static ArrayList<Wine> getAllWines() {
		try {
			Statement stmt =  MySQLConnection.establishConnection().createStatement();
			
			String query = "SELECT * FROM orders";
			
			ResultSet rset = stmt.executeQuery(query);
			
			ArrayList<Wine> result = new ArrayList<Wine>();
			
			while (rset.next()) {
				int idWine = rset.getInt("IDWine");
				String name = rset.getString("Name");
				String producer = rset.getString("Producer");
				int year = rset.getInt("Year");
				double price = rset.getDouble("Price");
				String notes = rset.getString("Notes");
				int bottles = rset.getInt("BottlesNumber");
				
				query = "SELECT v.* FROM vines v INNER JOIN wineproducers wp ON v.IDVine = wp.Vine WHERE wp.Wine = " + idWine;
				
				Statement stmtVines = MySQLConnection.establishConnection().createStatement();
				ResultSet rsetVines = stmtVines.executeQuery(query);
				
				ArrayList<String> vines = new ArrayList<String>();
				
				while(rsetVines.next()) {
					String vine = rsetVines.getString("Description");
					
					vines.add(vine);
				}
				
				Wine wine = new Wine(idWine, name, producer, year, price, notes, bottles, vines);	
				
				result.add(wine);
			}
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * The method getFilteredWines
	 * allows to search a specific wine
	 * 
	 * @param filter research parameter
	 * @return result of the research
	 */
	public static ArrayList<Wine> getFilteredWines(String filter) {
		try {
			Statement stmt =  MySQLConnection.establishConnection().createStatement();
			
			String query = "SELECT * FROM wines WHERE name LIKE '%" + filter + "%'";
			
			ResultSet rset = stmt.executeQuery(query);
			
			ArrayList<Wine> result = new ArrayList<Wine>();
			
			while (rset.next()) {
				int idWine = rset.getInt("IDWine");
				String name = rset.getString("Name");
				String producer = rset.getString("Producer");
				int year = rset.getInt("Year");
				double price = rset.getDouble("Price");
				String notes = rset.getString("Notes");
				int bottles = rset.getInt("BottlesNumber");
				
				query = "SELECT v.* FROM vines v INNER JOIN wineproducers wp ON v.IDVine = wp.Vine WHERE wp.Wine = " + idWine;
				
				Statement stmtVines = MySQLConnection.establishConnection().createStatement();
				ResultSet rsetVines = stmtVines.executeQuery(query);
				
				ArrayList<String> vines = new ArrayList<String>();
				
				while(rsetVines.next()) {
					String vine = rsetVines.getString("Description");
					
					vines.add(vine);
				}
				
				Wine wine = new Wine(idWine, name, producer, year, price, notes, bottles, vines);	
				
				result.add(wine);
			}
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * The method getWineByID
	 * allows to search a wine by ID
	 * 
	 * @param idWine the id of the wine we are searching for
	 * @return the selected wine
	 */
	public static Wine getWineByID(int idWine) {
		try {
			Statement stmt =  MySQLConnection.establishConnection().createStatement();
			
			String query = "SELECT * FROM wines WHERE IDWine = " + idWine;
			
			ResultSet rset = stmt.executeQuery(query);
			
			rset.next();
			
			String name = rset.getString("Name");
			String producer = rset.getString("Producer");
			int year = rset.getInt("Year");
			double price = rset.getDouble("Price");
			String notes = rset.getString("Notes");
			int bottles = rset.getInt("BottlesNumber");
			
			query = "SELECT v.* FROM vines v INNER JOIN wineproducers wp ON v.IDVine = wp.Vine WHERE wp.Wine = " + idWine;
			
			Statement stmtVines = MySQLConnection.establishConnection().createStatement();
			ResultSet rsetVines = stmtVines.executeQuery(query);
			
			ArrayList<String> vines = new ArrayList<String>();
			
			while(rsetVines.next()) {
				String vine = rsetVines.getString("Description");
				
				vines.add(vine);
			}
			
			Wine wine = new Wine(idWine, name, producer, year, price, notes, bottles, vines);	
			
			
			return wine;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * The method updateWine
	 * allow to update the number of bottle that are been sold
	 * 
	 * @param wine to update
	 * @return if the wine has been updated
	 */
	public static boolean updateWine(Wine wine) {
		try {
			String updateQuery = "UPDATE wines SET BottlesNumber = " + wine.getBottlesNumber() + " WHERE IDWine = " + wine.getIDWine();
			
			Statement statement = MySQLConnection.establishConnection().createStatement();
			
			int rows = statement.executeUpdate(updateQuery);
			
			return rows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * The method restockWine
	 * allows to update the number of bottle of a specific wine
	 * 
	 * @param wine to restock
	 * @return if the wine has been updated
	 */
	public static boolean restockWine(Wine wine) {
		try {
			String updateQuery = "UPDATE wines SET BottlesNumber = BottlesNumber + " + wine.getBottlesNumber() + " WHERE IDWine = " + wine.getIDWine();
			
			Statement statement = MySQLConnection.establishConnection().createStatement();
			
			NotificationController.updateNotificationByWine(wine.getIDWine());
			
			int rows = statement.executeUpdate(updateQuery);
			
			return rows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
