package com.rossettimonicadigiorgio.winestoremanagementv2.backend;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Request;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Response;

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
					
					Response rs = HandleRequest.run(rq);
					
					os.writeObject(rs);
					os.flush();
										
					this.server.close(this);
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
	}
}
