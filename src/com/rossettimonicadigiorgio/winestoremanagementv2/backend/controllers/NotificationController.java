package com.rossettimonicadigiorgio.winestoremanagementv2.backend.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.rossettimonicadigiorgio.winestoremanagementv2.backend.MySQLConnection;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Notification;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.User;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Wine;

public class NotificationController {
	public static ArrayList<Notification> getNotificationByUser(int idUser) {
		try {
			Statement stmt =  MySQLConnection.establishConnection().createStatement();
			
			String query = "SELECT * FROM notifications WHERE User = " + idUser;
			
			ResultSet rset = stmt.executeQuery(query);
			
			ArrayList<Notification> result = new ArrayList<Notification>();
			
			while (rset.next()) {
				int IDNotification = rset.getInt("IDNotification");
				int IDUser = rset.getInt("User");
				int IDWine = rset.getInt("Wine");
				boolean IsNotified = rset.getInt("IsNotified") == 0 ? false : true;
				
				Wine wine = WineController.getWineByID(IDWine);
				User user = UserController.getUserByID(IDUser);
				
				Notification notification = new Notification(IDNotification, user, wine, IsNotified);	
				
				result.add(notification);
			}
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Notification insertNotification(Notification notification) {
		try {
			String query = "INSERT INTO notifications (User, Wine, IsNotified) VALUES (?, ?, ?)";
			
			PreparedStatement pstmt =  MySQLConnection.establishConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setInt(1, notification.getUser().getIDPerson());
			pstmt.setInt(2, notification.getWine().getIDWine());
			pstmt.setInt(3, notification.getIsNotified() ? 1 : 0);
			
			pstmt.execute();
			
			int idNotification = 0;
			
	        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	idNotification = generatedKeys.getInt(1);
	            }
	        }
	        
	        Notification insertedNotification = new Notification(idNotification, notification.getUser(), notification.getWine(), notification.getIsNotified());
			
			return insertedNotification;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
