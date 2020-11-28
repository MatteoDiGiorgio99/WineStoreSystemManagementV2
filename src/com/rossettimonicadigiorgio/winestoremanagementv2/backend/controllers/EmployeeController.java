package com.rossettimonicadigiorgio.winestoremanagementv2.backend.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.rossettimonicadigiorgio.winestoremanagementv2.backend.MySQLConnection;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Employee;

public class EmployeeController {
	public static Employee login(String username, String password) {
		try {
			Statement stmt =  MySQLConnection.establishConnection().createStatement();
			
			String query = "SELECT * FROM employees WHERE email = '" + username + "' AND password = '" + password + "'";
			
			ResultSet rset = stmt.executeQuery(query);
			
			rset.next();
			
			int idAdmin = rset.getInt("IDEmployee");
			String name = rset.getString("Name");
			String surname = rset.getString("Surname");
			String mailUser = rset.getString("Email");
			String passwordUser = rset.getString("Password");
			
			Employee employee = new Employee(idAdmin, name, surname, mailUser, passwordUser);
			
			return employee;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean register(Employee employee) {
		try {
			String query = "INSERT INTO employees (Name, Surname, Email, Password) VALUES (?, ?, ?, ?)";
			
			PreparedStatement pstmt =  MySQLConnection.establishConnection().prepareStatement(query);
			
			pstmt.setString(1, employee.getName());
			pstmt.setString(2, employee.getSurname());
			pstmt.setString(3, employee.getEmail());
			pstmt.setString(4, employee.getPassword());
			
			pstmt.execute();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
