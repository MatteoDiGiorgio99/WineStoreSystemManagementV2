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
	protected int getIDOrder() { return this.IDOrder; }
	
	/**
	 * Fetch the current order's status
	 * @return the status
	 */
	protected StatusEnum getStatus() { return this.Status; }
	
	/**
	 * Process made by an  employee when ships the order
	 * @return if the procedure has succeeded
	 */
	protected boolean ship() { this.Status = StatusEnum.Shipped; return true; }
	
	/**
	 * Fetch user's data
	 * @return user's data
	 */
	protected User getUser() { return this.User; }
	
	/**
	 * Fetch wines that has been ordered 
	 * @return wines that has been ordered
	 */
	public List<Wine> getWines() { return this.Wines; }
}
