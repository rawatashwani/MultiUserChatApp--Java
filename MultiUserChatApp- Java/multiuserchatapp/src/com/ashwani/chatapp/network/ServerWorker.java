package com.brainmentors.chatapp.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

// Thread == Worker
// Worker need a Job to Perform
// For Job u give runnable
// Once job is created via Runnable so write the job logic inside a run function
// Assign the job to a Thread/Worker
public class ServerWorker extends Thread{
	private Socket clientSocket;
	private InputStream in;
	private OutputStream out;
	private Server server;
	public ServerWorker(Socket clientSocket, Server server) throws IOException{
			this.server = server;
			this.clientSocket = clientSocket;
			in = clientSocket.getInputStream(); // Client Data Read
			out = clientSocket.getOutputStream(); // Client Data Write
			System.out.println("New Client Comes ");
	}
	@Override
	public void run() {
		// Read Data from the Client and BroadCast the data to all
		BufferedReader br  = new BufferedReader(new InputStreamReader(in));
		String line; 
		try {
		while(true) {
			
				line = br.readLine(); // \n
				System.out.println("Line Read .... "+line);
				if(line.equalsIgnoreCase("quit")) {
					break; // Client Chat End
				}
				//out.write(line.getBytes()); // Client Send
				// Broadcast to all client
				for(ServerWorker serverWorker : server.workers) {
					line = line + "\n";
					serverWorker.out.write(line.getBytes());
					
				}
			
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		finally {
			try {
			if(br!=null) {
				br.close();
			}
			if(in!=null) {
				in.close();
			}
			if(out!=null) {
				out.close();
			}
			if(clientSocket!=null) {
				clientSocket.close();
			}
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		
	}
}
























//public class ServerWorker implements Runnable {






/*
public class ServerWorker extends Thread{

	
	@Override
	public void run() {
		// Job to Perform
		// Logic
		for(int i = 1;i<=5; i++) {
			System.out.println("RUN I is "+i+" "+Thread.currentThread());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args) {
//		ServerWorker job = new ServerWorker();
		ServerWorker worker = new ServerWorker();
		worker.start();
		// Assign the job to a Thread/ Worker
//		Thread worker = new Thread (job,"worker1");
//		worker.start(); // Internally it call run ()
		for(int j = 1; j<=5; j++) {
			System.out.println("Main "+j +" "+Thread.currentThread());
		}
		
	}
	
	
}
*/


