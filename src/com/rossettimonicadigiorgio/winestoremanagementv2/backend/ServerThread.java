package com.rossettimonicadigiorgio.winestoremanagementv2.backend;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.rossettimonicadigiorgio.winestoremanagementv2.backend.controllers.AdministratorController;
import com.rossettimonicadigiorgio.winestoremanagementv2.backend.controllers.EmployeeController;
import com.rossettimonicadigiorgio.winestoremanagementv2.backend.controllers.NotificationController;
import com.rossettimonicadigiorgio.winestoremanagementv2.backend.controllers.OrderController;
import com.rossettimonicadigiorgio.winestoremanagementv2.backend.controllers.UserController;
import com.rossettimonicadigiorgio.winestoremanagementv2.backend.controllers.WineController;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Employee;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Notification;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Order;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Request;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Response;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.User;

/**
 * The {@code ServerThread} is a class that defines:
 * a connection handled by the {@code Server} class.
 * 
 * @author 296666
 *
 */
public class ServerThread implements Runnable {	
	private Server server;
	private Socket socket;
	
	/**
	 * Class constructor
	 * @param s the server reference
	 * @param c the socket reference
	 */
	public ServerThread(final Server s, final Socket c) {
		this.server = s;
		this.socket = c;
	}
	
	/**
	 * This method handles the connection by
	 * listening to the request made by the client
	 * and provides an answer to it.
	 * 
	 * When an answer is sent the connection is safely closed.
	 */
	@Override
	public void run() {
		ObjectInputStream is = null;
		ObjectOutputStream os = null;
		
		try {
			is = new ObjectInputStream(new BufferedInputStream(this.socket.getInputStream()));
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		while(true) {
			try {
				Object i = is.readObject();
				
				if(i instanceof Request) {
					Request rq = (Request) i;
					
					if(os == null) {
						os = new ObjectOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
					}
					
					Response rs = new Response(rq.getValue());
					
					switch(rq.getValue()) {
						case "administratorLogin":
							Object administratorLogin = (Object) AdministratorController.login((String)rq.getParams().get(0), (String)rq.getParams().get(1));
							rs = new Response(administratorLogin);
							break;
							
						case "employeeLogin":
							Object employeeLogin = (Object) EmployeeController.login((String)rq.getParams().get(0), (String)rq.getParams().get(1));
							rs = new Response(employeeLogin);
							break;
							
						case "userLogin":
							Object userLogin = (Object) UserController.login((String)rq.getParams().get(0), (String)rq.getParams().get(1));
							rs = new Response(userLogin);
							break;
							
						case "employeeRegister":
							Object employeeRegister = (Object) EmployeeController.register((Employee)rq.getParams().get(0));
							rs = new Response(employeeRegister);
							break;
							
						case "userRegister":
							Object userRegister = (Object) UserController.register((User)rq.getParams().get(0));
							rs = new Response(userRegister);
							break;
							
						case "filterWines":
							Object filterWines = (Object) WineController.getFilteredWines((String)rq.getParams().get(0));
							rs = new Response(filterWines);
							break;
							
						case "listWines":
							Object listWines = (Object) WineController.getAllWines();
							rs = new Response(listWines);
							break;
							
						case "listOrders":
							Object listOrders = (Object) OrderController.getAllOrders();
							rs = new Response(listOrders);
							break;
							
						case "insertOrder":
							Object insertOrder = (Object) OrderController.insertOrder(((Order)rq.getParams().get(0)));
							rs = new Response(insertOrder);
							break;
							
						case "insertNotification":
							Object insertNotification = (Object) NotificationController.insertNotification(((Notification)rq.getParams().get(0)));
							rs = new Response(insertNotification);
							break;
					}
					
					os.writeObject(rs);
					os.flush();
										
					this.server.close(this);
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
}
