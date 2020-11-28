package com.rossettimonicadigiorgio.winestoremanagementv2.backend.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.rossettimonicadigiorgio.winestoremanagementv2.backend.MySQLConnection;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Wine;

public class WineController {
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
	
	public static Wine getWineByID(int idWine) {
		try {
			Statement stmt =  MySQLConnection.establishConnection().createStatement();
			
			String query = "SELECT * FROM wines";
			
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
}
