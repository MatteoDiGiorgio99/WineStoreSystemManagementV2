package com.rossettimonicadigiorgio.winestoremanagementv2.backend.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.rossettimonicadigiorgio.winestoremanagementv2.backend.MySQLConnection;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Employee;

/**
 * The {@code EmployeeController} is a class that defines:
 * a controller for the employee
 * 
 * @author 296666
 *
 */
public class EmployeeController {
	
	/**
	 * The method login
	 * allows an employee to login
	 * @param username of the employee
	 * @param password of the employee
	 * @return the logged employee
	 */
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
	
	/**
	 * The method register
	 * allows to register a employee
	 * @param employee data of the new employee
	 * @return a new employee
	 */
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
	
	/**
	 * Fetch data of employees
	 * @return employees
	 */
	public static ArrayList<Employee> getEmployees() {
		try {
			Statement stmt =  MySQLConnection.establishConnection().createStatement();
			
			String query = "SELECT * FROM employees";
			
			ResultSet rset = stmt.executeQuery(query);
			
			ArrayList<Employee> employees = new ArrayList<Employee>();
			
			while(rset.next()) {
				int idEmployee = rset.getInt("IDEmployee");
				String name = rset.getString("Name");
				String surname = rset.getString("Surname");
				String mailUser = rset.getString("Email");
				String passwordUser = rset.getString("Password");
				
				Employee employee = new Employee(idEmployee, name, surname, mailUser, passwordUser);
				
				employees.add(employee);
			}
						
			return employees;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
