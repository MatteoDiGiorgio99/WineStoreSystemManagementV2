package com.rossettimonicadigiorgio.winestoremanagementv2.backend.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.rossettimonicadigiorgio.winestoremanagementv2.backend.MySQLConnection;

//import java.util.ArrayList;

import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Administrator;

public class AdministratorController {
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
