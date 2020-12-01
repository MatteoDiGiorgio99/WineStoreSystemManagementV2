package com.rossettimonicadigiorgio.winestoremanagementv2.backend;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * The {@code Server} is a class that defines:
 * a server capable of using multiple connections simultaneously using threads and pools.
 * 
 * @author 296666
 *
 */
public class Server {
	public static final int COREPOOL = 25;
	private static final int MAXPOOL = 50;
	private static final long IDLETIME = 5000;
	
	private static final int SPORT = 4444;
	
	private ServerSocket socket;
	private ThreadPoolExecutor pool;
	
	/** 
	 * The class constructor
	 * 
	 * @throws IOException if unable to open the socket
	 */
	public Server() throws IOException {
		this.socket = new ServerSocket(SPORT);
	}
	
	/** 
	 * When a client creates a connection a new thread is created to manage its request
	 */
	public void run() {
		this.pool = new ThreadPoolExecutor(COREPOOL, MAXPOOL, IDLETIME, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
		
		while(true) {
			try {
				Socket s = this.socket.accept();
				this.pool.execute(new ServerThread(this, s));
			} catch (Exception e) {
				break;
			}
		}
		
		this.pool.shutdown();
	}
	
	/**
	 * A method to fetch all pools of a thread
	 * @return the list of pools
	 */
	public ThreadPoolExecutor getPool() {
		return this.pool;
	}
	
	/**
	 * A method to close a connection thread without closing the socket 
	 * @param thread the connection to close
	 */
	public void close(ServerThread thread) {
		try {
			this.pool.remove(thread);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}