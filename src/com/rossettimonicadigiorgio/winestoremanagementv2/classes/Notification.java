package com.rossettimonicadigiorgio.winestoremanagementv2.classes;

import java.io.Serializable;

/**
 * The {@code Notification} is a class that defines:
 * Notifies the user if the wine has become available
 * @author 297402
 *
 */
public class Notification implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int idNotification;
	private User user;
	private Wine wine;
	
	/**
	 * Class constructor
	 * @param user the user
	 * @param wine that we want to be notified
	 */
	public Notification(int idNotification, User user, Wine wine) {
		this.idNotification = idNotification;
		this.user = user;
		this.wine = wine;
	}
	
	/**
	 * Fetch the id of the notification
	 * @return user
	 */
	public int getIDNotification() {
		return this.idNotification;
	}
	
	/**
	 * Fetch the user to be notified
	 * @return user
	 */
	public User getUser() {
		return this.user;
	}
	
	/**
	 * Fetch the wine we want to be notified
	 * @return wine
	 */
	public Wine getWine() {
		return this.wine;
	}
}
