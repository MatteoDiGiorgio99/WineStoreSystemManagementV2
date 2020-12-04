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
	private boolean isNotified;
	
	/**
	 * Class constructor
	 * @param user the user
	 * @param wine that we want to be notified
	 */
	public Notification(int idNotification, User user, Wine wine, boolean isNotified) {
		this.idNotification = idNotification;
		this.isNotified = isNotified;
		this.user = user;
		this.wine = wine;
	}
	
	/**
	 * The method ToString
	 * convert the class to a strings
	 */
    @Override
    public String toString() {
    	if(!this.isNotified)
    		return String.format("Notification for: %s (%d) | NOT IN STOCK", this.wine.getName(), this.wine.getYear());
    	else
            return String.format("Notification for: %s (%s) | IN STOCK", this.wine.getName(), this.wine.getYear());
    }
	
	/**
	 * Fetch the id of the notification
	 * @return user
	 */
	public int getIDNotification() {
		return this.idNotification;
	}
	
	/**
	 * Fetch if the notification has been notified to the user
	 * @return if the notification has been notified
	 */
	public boolean getIsNotified() {
		return this.isNotified;
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
