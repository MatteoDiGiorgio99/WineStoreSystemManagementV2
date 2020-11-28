package com.rossettimonicadigiorgio.winestoremanagementv2.backend;

import java.io.IOException;

/**
 * @author 296666
 *
 */
public class MainServer {

	/**
	 * Point of entry for the application
	 * @param args the arguments for the application to run
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {		
		new Server().run();
	}
}
