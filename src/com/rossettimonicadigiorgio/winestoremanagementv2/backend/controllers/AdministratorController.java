package com.rossettimonicadigiorgio.winestoremanagementv2.backend.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.rossettimonicadigiorgio.winestoremanagementv2.backend.MySQLConnection;

//import java.util.ArrayList;

import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Administrator;

/**
 * The {@code AdministratorController} is a class that defines:
 * a controller for the admin
 * 
 * @author 296666
 *
 */
public class AdministratorController {
	
	/**
	 * The method login
	 * allows the admin to access to the database using their credentials
	 * @param username of the admin
	 * @param password of the admin
	 * @return an admin
	 */
	public static Administrator login(String username, String password) {
		try {
			Statement stmt =  MySQLConnection.establishConnection().createStatement();
			
			String query = "SELECT * FROM administrators WHERE email = '" + username + "' AND password = '" + password + "'";
			
			ResultSet rset = stmt.executeQuery(query);
			
			rset.next();
			
			int idAdmin = rset.getInt("IDAdministrator");
			String name = rset.getString("Name");
			String surname = rset.getString("Surname");
			String mailUser = rset.getString("Email");
			String passwordUser = rset.getString("Password");
			
			Administrator admin = new Administrator(idAdmin, name, surname, mailUser, passwordUser);
			
			return admin;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
