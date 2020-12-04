package com.rossettimonicadigiorgio.winestoremanagementv2.frontend;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Request;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Response;

/**
 * The {@code Client} is a class that defines:
 * a client capable of sending a request to a specific server.
 * 
 * @author 297398
 *
 */
public class Client {
	
	private static final int SPORT = 4444;
	private static final String SHOST = "localhost";
	
	/**
	 * This method sends a request to a server and waits for its response.
	 * @param request the request to send to the server
	 * @return the response from the server
	 */
	public Response run(Request request) {
		try {
			Socket client = new Socket(SHOST, SPORT);
			
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;
			
			os.writeObject(request);
			os.flush();
			
			if(is == null) {
				is = new ObjectInputStream(client.getInputStream());
			}
			
			Object o = is.readObject();
			Response rs = null;
			
			if (o instanceof Response)
				rs = (Response) o;
			
			client.close();
			
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
