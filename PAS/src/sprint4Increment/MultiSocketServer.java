package sprint4Increment;

import java.net.*;
import java.io.*;
import java.util.*;

/**
 * This is the Server class which listens for connection requests and then
 * creates and distributes threads to deal with the requests.
 * 
 * Implements Runnable
 * 
 * @author Shauna-Marie
 *
 */
public class MultiSocketServer implements Runnable {
	Scanner keyboard = new Scanner(System.in);
	/**
	 * Create a socket connection
	 */
	private Socket connection;
	/**
	 * Variable to store time stamp
	 */
	private String TimeStamp;
	/**
	 * Variable to store ID for each connection
	 */
	private int ID;

	/**
	 * Constructor with arg
	 * 
	 * @param s
	 * @param ID
	 */
	MultiSocketServer(Socket s, int ID) {
		this.connection = s;
		this.ID = ID;
	}

	/**
	 * Main method from which the server operates
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		 * Variable to store port number
		 */
		int port = 8080;
		/*
		 * Variable used to increment the ID each time a new socket is added
		 */
		int count = 0;

		// try
		try {
			// to create a new socket connection
			ServerSocket socket1 = new ServerSocket(port);
			// System.out.println("MultiSocketServer Initialized");
			// Infinitely
			while (true) {
				// create a new socket connection and accept an incoming request
				Socket connection = socket1.accept();
				// create new thread to handle client request
				Runnable runnable = new MultiSocketServer(connection, ++count);
				Thread thread = new Thread(runnable);
				// call the treads run() to initiate it
				thread.start();
			}
			// catch any exception
		} catch (Exception e) {
		}
	}// end main

	/**
	 * This is the threads run method which will be implemented by calling
	 * thread.start()
	 */
	public void run() {
		TriageManager tM = new TriageManager();
		BayManager bM = new BayManager();
		try {
			BufferedInputStream is = new BufferedInputStream(
					connection.getInputStream());
			InputStreamReader isr = new InputStreamReader(is);
			int character;

			StringBuffer process = new StringBuffer();
			while ((character = isr.read()) != 13) {
				process.append((char) character);
			}
			System.out.println(process);
			// need to wait 10 seconds to pretend that we're processing
			// something
			try {
				Thread.sleep(10000);
			} catch (Exception e) {
			}
			TimeStamp = new java.util.Date().toString();
			String returnCode = bM + "MultiSocketServer repsonded at "
					+ TimeStamp + (char) 13;
			BufferedOutputStream os = new BufferedOutputStream(
					connection.getOutputStream());
			OutputStreamWriter osw = new OutputStreamWriter(os, "US-ASCII");
			osw.write(returnCode);
			osw.flush();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				connection.close();
			} catch (IOException e) {
			}
		}
	}
}
