package com.rossettimonicadigiorgio.winestoremanagementv2.backend.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.rossettimonicadigiorgio.winestoremanagementv2.backend.MySQLConnection;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Notification;

public class NotificationController {
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
