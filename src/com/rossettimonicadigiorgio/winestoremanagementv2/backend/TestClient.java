package com.rossettimonicadigiorgio.winestoremanagementv2.backend;

import java.io.IOException;
import java.util.ArrayList;

import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Request;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Response;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Wine;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Employee;
import com.rossettimonicadigiorgio.winestoremanagementv2.frontend.Client;

/**
 * @author 296666
 *
 */
public class TestClient {

	/**
	 * Point of entry for the application
	 * @param args the arguments for the application to run
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Employee user = new Employee(-1, "nome", "cognome", "email", "password");
		
		ArrayList<Object> params = new ArrayList<Object>();  
		params.add("vi");
		
		Request request = new Request("filterWines", params);
		
		Response response = new Client().run(request);

		ArrayList<Wine> result = (ArrayList<Wine>) response.getValue();		
		
		System.out.println(result);
	}
}
