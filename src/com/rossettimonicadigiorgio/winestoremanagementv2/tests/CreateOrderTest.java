package com.rossettimonicadigiorgio.winestoremanagementv2.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Order;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Request;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Response;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.StatusEnum;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.User;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Wine;
import com.rossettimonicadigiorgio.winestoremanagementv2.frontend.Client;

public class CreateOrderTest {
	private static ArrayList<Object> params = new ArrayList<Object>();
	private static User user = null;
	private static ArrayList<Wine> wines = new ArrayList<Wine>();
		
	@BeforeEach
	static void init() {
		params.clear();
	}
	
	@Test
	static void testLogin() {
		params.add("");
		params.add("");
		
		Request request = new Request("userLogin",params);
		
		Response response = new Client().run(request);
		
		user = (User) response.getValue();
		
		assertNotNull(user);
	}
	
	@Test
	static void testGetAllWines() {
		wines = (ArrayList<Wine>) new Client().run(new Request("listWines", params)).getValue();
		
		assertNull(wines);
		assertTrue(wines.size() >= 0);
	}
	
	@ParameterizedTest
	@CsvSource({ "vino1", "vino2" })
	static void testReserchWines(String query) {
		params.add(query);
		
		wines = (ArrayList<Wine>) new Client().run(new Request("listWines", params)).getValue();
		
		assertNull(wines);
		assertTrue(wines.size() >= 0);
	}
	
	@Test
	static void testInsertOrder() {
		ArrayList<Wine> toOrder = new ArrayList<Wine>();
		
		for(Wine wine : wines) {
			if(wine.getBottlesNumber() > 0) {
				Wine toAdd = wine.clone();
				toAdd.setBottlesNumber((int) (Math.random() * wine.getBottlesNumber()));
				toOrder.add(toAdd);
			}
		}
		
		Order order = new Order(-1, StatusEnum.Confirmed, user, toOrder);
		
		params.add(order);
		
		Order result = (Order) new Client().run(new Request("insertOrder", params)).getValue();
		
		assertNull(result);
		assertTrue(result.getWines().size() == toOrder.size());
	}
}
