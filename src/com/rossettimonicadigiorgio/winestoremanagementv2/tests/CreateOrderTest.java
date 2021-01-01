package com.rossettimonicadigiorgio.winestoremanagementv2.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Order;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Request;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Response;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.StatusEnum;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.User;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Wine;
import com.rossettimonicadigiorgio.winestoremanagementv2.frontend.Client;

/**
 * The {@code CreateOrderTest} is a class that defines the following behavior:
 * Allows to test all the classes of the system that allows a user to create a order
 * from the login.
 * 
 * @author 296666
 *
 */
@TestMethodOrder(OrderAnnotation.class)
public class CreateOrderTest {
	private static ArrayList<Object> params = new ArrayList<Object>();
	private static User user = null;
	private static ArrayList<Wine> wines = new ArrayList<Wine>();
		
	/**
	 * The method init
	 * allows to reset the params arraylist
	 * before every test
	 */
	@BeforeEach
	public void init() {
		CreateOrderTest.params.clear();
	}
	
	/**
 	 * The testlogin method
	 * allows to simulate a login operation
	 *  
	 * @param email the user email to test
	 * @param password the user password to test
	 */
	@ParameterizedTest
	@CsvSource({"aa,aa"})
	@org.junit.jupiter.api.Order(1)
	public void testLogin(String email, String password) {
		CreateOrderTest.params.add(email);
		CreateOrderTest.params.add(password);
		
		Request request = new Request("userLogin", CreateOrderTest.params);
		
		Response response = new Client().run(request);
		
		CreateOrderTest.user = (User) response.getValue();
		
		assertNotNull(CreateOrderTest.user);
	}
	
	/**
	 * The testResearchWine method
	 * allows to simulate the research of a wine textbox-like
	 * 
	 * @param query the string of the name of the wine to search in the DB
	 */
	@ParameterizedTest
	@CsvSource({ "vino1", "vino2" })
	@org.junit.jupiter.api.Order(2)
	public void testReserchWines(String query) {
		CreateOrderTest.params.add(query);
		
		CreateOrderTest.wines = (ArrayList<Wine>) new Client().run(new Request("filterWines", params)).getValue();
		
		assertTrue(CreateOrderTest.wines.size() >= 0);
	}
	
	/**
	 * The method testInsertOrder
	 * allows to submit a order into the database 
	 */
	@Test
	@org.junit.jupiter.api.Order(3)
	public void testInsertOrder() {
		ArrayList<Wine> toOrder = new ArrayList<Wine>();
		
		for(Wine wine : CreateOrderTest.wines) {
			if(wine.getBottlesNumber() > 0) {
				Wine toAdd = wine.clone();
				toAdd.setBottlesNumber((int) (Math.random() * wine.getBottlesNumber()));
				toOrder.add(toAdd);
			}
		}
		
		Order order = new Order(-1, StatusEnum.Confirmed, CreateOrderTest.user, toOrder);
		
		CreateOrderTest.params.add(order);
		
		Order result = (Order) new Client().run(new Request("insertOrder", CreateOrderTest.params)).getValue();
		
		assertTrue(result.getWines().size() == toOrder.size());
	}
}
