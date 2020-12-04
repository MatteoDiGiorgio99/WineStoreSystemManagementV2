package com.rossettimonicadigiorgio.winestoremanagementv2.classes;
import java.util.*;
import java.lang.Math;


/**
 * The {@code System} is a class that defines:
 * The wine store behaviors
 * @author 296666
 *
 */
public class System {
	private ArrayList<Wine> wines;
	private ArrayList<User> users;
	private ArrayList<Employee> employees; 
	private ArrayList<Order> orders;
	private ArrayList<Notification> notifications;
	
	/**
	 * Class constructor
	 * @param wines the first set of wines
	 * @param users the first set of users
	 * @param emplyees the first set of employees
	 */
	public System (ArrayList<Wine> wines, ArrayList<User> users, ArrayList<Employee> emplyees)
	{
		this.wines = wines;
		this.users = users;
		this.employees = emplyees;
		this.orders = new ArrayList<Order>();
		this.notifications = new ArrayList<Notification>();
	}
	
	/**
	 * Fetch the list of wines
	 * @return a list of wines
	 */
	public ArrayList<Wine> getWines() {
		return this.wines;
	}
	
	/**
	 * Fetch the list of order
	 * @return the list of order
	 */
	public ArrayList<Order> getOrder() {
		return this.orders;
	}
	
	/**
	 * The method Login 
	 * @param email of the user
	 * @param password of the user
	 * @return person
	 * @throws IllegalAccessException if the credentials are wrong
	 */
	public Person Login(String email,String password) throws IllegalAccessException {
		for (Person person : this.users) {
			if(person.getEmail() == email && person.getPassword() == password)
			{
				return person;
			}	
		}
		
		for (Person person : this.employees) {
			if(person.getEmail() == email && person.getPassword() == password)
			{
				return person;
			}	
		}
		
		throw new IllegalAccessException();
	}
	
	/**
	 * The method Register
	 * @param person
	 * @return the user that has just been created
	 */
	public boolean RegisterUser(User person) {
		if(person == null) 
			return false;
		
		return this.users.add(person);
	}
	
	/**
	 * The method FindWine
	 * @param name of the wine
	 * @param producer of the wine
	 * @param year of production the wine 
	 * @return the wine we have just searched
	 */
	public Wine FindWine(String name, String producer, int year) { 
		for (Wine wine : this.wines) {
			if(wine.EqualTo(name, producer, year))
				return wine.clone();
		}
		
		return null;
	}
	
	/**
	 * The method BuyOrder
	 * @param user want to buy 
	 * @param wines the user want to buy
	 * @param getNotified if the wine user wants is finished
	 * @return the created order
	 */
	public Order BuyOrder(User user, ArrayList<Wine> wines, boolean getNotified) {
		ArrayList<Wine> notAvailable = new ArrayList<Wine>();
		
		for (Wine wine : wines) {
			if(!wine.CheckAvailability()) {
				return null;
			}
			
			for (Wine globalwine: this.wines)  {
			   if(globalwine.EqualTo(wine))
			   {
				   if(!globalwine.ProcessOrder(wine.getBottlesNumber())) {
					   notAvailable.add(wine);
				   
					   if(getNotified)
						   this.subscribeNotification(user, globalwine);
				   }
			   }
			}
		}
		
		for (Wine wine : notAvailable) {
			wines.remove(wines.indexOf(wine));
		}
		
		if(wines.size() > 0) {
			Order order = null; //new Order(user, wines); 
			this.orders.add(order);
			
			return order;
		}
		
		return null;
	}
	
	/**
	 * The method ShipOrder
	 * @param order we want to ship
	 * @return order to ship
	 */
	public boolean ShipOrder(Order order) {
		if(order == null) {
			return false;
		}
		
		Order orderToShip = this.orders.get(this.orders.indexOf(order));
		
		if(orderToShip == null) 
			return false;
		
		return orderToShip.ship();
	}
	
	/**
	 * The method StockWine
	 * @param user the employee want to make the action
	 * @param type of the wine to stock
	 * @param numberOfBottles we want to add
	 * @return the number of user to be notify about the new stock
	 */
	public ArrayList<Notification> StockWine(Employee user, Wine type, int numberOfBottles) {
		if(user == null)		
			return null;
		
		if(this.employees.indexOf(user) < 0)
			return null;
		
		int winenum = this.FindWinePosition(type);
		
		if(winenum < 0)
			return null;
		
		if(!type.Restock(numberOfBottles))
			return null;
		
		this.wines.remove(winenum);
		this.wines.add(winenum, type);
		
		return notifyUsers(type);
	}
	
	/**
	 * The method SubscribeNotification
	 * @param user to be notified
	 * @param wine that the user wants to be notified about 
	 * @return if the request of notification has been inserted successfully
	 */
	public boolean subscribeNotification(User user, Wine wine) {
		if(user == null)
			return false;
		
		if(FindWine(wine) == null)
			return false;
		
		Notification notification = null; //new Notification(user, wine);
		
		this.notifications.add(notification);
		
		return true;
	}
	
	/**
	 * The method CreateEmployee
	 * @param admin the administrator account
	 * @param employee to create
	 * @return the created employee 
	 */
	public Employee CreateEmployee(Administrator admin,Employee employee)
	{
		employee.setEmail(employee.getName() + "." + employee.getSurname() + "@example.it");
		
		employee.setPassword(String.valueOf(Math.random()*10)  + String.valueOf(Math.random()*10) + String.valueOf(Math.random()*10) + String.valueOf(Math.random()*10));
		
		this.employees.add(employee);
		
		return employee;
	}
	
	/**
	 * The method NotifyUser
	 * @param wine the wine to look for
	 * @return the notifications
	 */
	private ArrayList<Notification> notifyUsers(Wine wine) {
		ArrayList<Notification> results = new ArrayList<Notification>();
		
		for (Notification notification : this.notifications) {
			if(!wine.EqualTo(notification.getWine()))
				continue;
			
			results.add(notification);
		}
		
		for (Notification notification : results) {
			this.notifications.remove(this.notifications.indexOf(notification));
		}
		
		return results;
	}
	
	/**
	 * The method FindWine
	 * @param wine to search
	 * @return the wine if has been found
	 */
	private Wine FindWine(Wine wine) { 
		for (Wine globalwine : this.wines) {
			if(globalwine.EqualTo(wine))
				return wine;
		}
		
		return null;
	}
	
	/**
	 * The method FindWinePosition
	 * @param wine the wine to search
	 * @return the position the wine in the list
	 */
	private int FindWinePosition(Wine wine) { 
		for (int i = 0; i < this.wines.size(); i++) {
			if((this.wines.get(i)).EqualTo(wine))
				return i;
		}
		
		return -1;
	}
	
	
}
