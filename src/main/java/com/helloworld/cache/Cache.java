package com.helloworld.cache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class Cache {
	private Socket socket;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;


	public Cache() throws UnknownHostException, IOException {
		socket = new Socket("ec2-3-134-94-69.us-east-2.compute.amazonaws.com", 9001);
		printWriter = new PrintWriter(socket.getOutputStream(), true);
		bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		printWriter.println("id " + InetAddress.getLocalHost().getHostName());
		printWriter.flush();
	}

	public String getValue(String key) throws IOException {
		printWriter.println("get " + key);
		printWriter.flush();
		
		String response = bufferedReader.readLine();
		return response;
	}

	public void putValue(String key, String value) {
	}
}
