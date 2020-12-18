package com.rossettimonicadigiorgio.winestoremanagementv2.tests;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.*;

import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Request;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Response;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.User;
import com.rossettimonicadigiorgio.winestoremanagementv2.frontend.Client;

public class UserTest {
	private static ArrayList<Object> params = new ArrayList<Object>();
		
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
		
		User result = (User) response.getValue();
		
		assertNotNull(result);
	}
}
