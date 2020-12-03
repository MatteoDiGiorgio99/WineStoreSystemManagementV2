package com.rossettimonicadigiorgio.winestoremanagementv2.classes;

import java.io.Serializable;
import java.util.*;

/**
 * The {@code Order} is a class that defines:
 * an order made by a user 
 * @author 297402
 *
 */
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	private int IDOrder;

	private StatusEnum Status; 
	private User User;
	private ArrayList<Wine> Wines;
	
	/**
	 * Class constructor
	 * @param IDOrder id of the order
	 * @param status that made the order
	 * @param user that made the order
	 * @param wines that is been ordered
	 */
	public Order(int IDOrder, StatusEnum status, User user, ArrayList<Wine> wines)
	{
		this.Status = status;
		this.IDOrder = IDOrder;
		this.User = user;
		this.Wines = wines;
	}

	/**
	 * Fetch the current order's ID
	 * @return the order's ID
	 */
	public int getIDOrder() { return this.IDOrder; }
	
	/**
	 * Fetch the current order's status
	 * @return the status
	 */
	public StatusEnum getStatus() { return this.Status; }
	
	/**
	 * Process made by an  employee when ships the order
	 * @return if the procedure has succeeded
	 */
	protected boolean ship() { this.Status = StatusEnum.Shipped; return true; }
	
	/**
	 * Fetch user's data
	 * @return user's data
	 */
	public User getUser() { return this.User; }
	
	/**
	 * Fetch wines that has been ordered 
	 * @return wines that has been ordered
	 */
	public List<Wine> getWines() { return this.Wines; }
	
	/**
	 * The method ToString
	 * convert the class to a string 
	 */
    @Override
    public String toString() { 
        return String.format("Order %d | Status: %s | %d Products", this.IDOrder, this.Status, this.Wines.size()); 
    } 

}
