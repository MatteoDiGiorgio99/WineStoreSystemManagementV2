package com.rossettimonicadigiorgio.winestoremanagementv2.backend.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.rossettimonicadigiorgio.winestoremanagementv2.backend.MySQLConnection;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.User;

/**
 * The {@code UserController} is a class that defines:
 * a controller for the user
 * 
 * @author 296666
 *
 */
public class UserController {
	
	/**
	 * The method login 
	 * allows the user to login
	 * 
	 * @param username of the user
	 * @param password of the user
	 * @return logged user
	 */
	public static User login(String username, String password) {
		try {
			Statement stmt =  MySQLConnection.establishConnection().createStatement();
			
			String query = "SELECT * FROM users WHERE email = '" + username + "' AND password = '" + password + "'";
			
			ResultSet rset = stmt.executeQuery(query);
			
			rset.next();
			
			int idAdmin = rset.getInt("IDUser");
			String name = rset.getString("Name");
			String surname = rset.getString("Surname");
			String mailUser = rset.getString("Email");
			String passwordUser = rset.getString("Password");
			
			User user = new User(idAdmin, name, surname, mailUser, passwordUser);
			
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * The method register 
	 * allows to register a new user
	 * 
	 * @param user the data of new user
	 * @return a new user
	 */
	public static User register(User user) {
		try {
			String query = "INSERT INTO users (Name, Surname, Email, Password) VALUES (?, ?, ?, ?)";
			
			PreparedStatement pstmt =  MySQLConnection.establishConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getSurname());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getPassword());
			
			pstmt.execute();
			
			int idUser = 0;
			
	        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                idUser = generatedKeys.getInt(1);
	            }
	        }
	        
	        User insertedUser = new User(idUser, user.getName(), user.getSurname(), user.getEmail(), user.getPassword());
			
			return insertedUser;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Fetch users 
	 * 
	 * @return list of users
	 */
	public static ArrayList<User> getUsers() {
		try {
			Statement stmt =  MySQLConnection.establishConnection().createStatement();
			
			String query = "SELECT * FROM users ";
			
			ResultSet rset = stmt.executeQuery(query);
			
			ArrayList<User> users = new ArrayList<User>();
			
			while(rset.next()) {
				int idAdmin = rset.getInt("IDUser");
				String name = rset.getString("Name");
				String surname = rset.getString("Surname");
				String mailUser = rset.getString("Email");
				String passwordUser = rset.getString("Password");
				
				User user = new User(idAdmin, name, surname, mailUser, passwordUser);
				
				users.add(user);
			}
						
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * The method getUserByID
	 * allows to get all data of a selected user
	 * 
	 * @param idUser id of the user
	 * @return the user searched
	 */
	public static User getUserByID(int idUser) {
		try {
			Statement stmt =  MySQLConnection.establishConnection().createStatement();
			
			String query = "SELECT * FROM users WHERE IDUser = " + idUser;
			
			ResultSet rset = stmt.executeQuery(query);
			
			rset.next();
			
			int idAdmin = rset.getInt("IDUser");
			String name = rset.getString("Name");
			String surname = rset.getString("Surname");
			String mailUser = rset.getString("Email");
			String passwordUser = rset.getString("Password");
			
			User user = new User(idAdmin, name, surname, mailUser, passwordUser);
			
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
