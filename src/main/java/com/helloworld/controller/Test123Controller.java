package com.helloworld.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.inject.Inject;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.helloworld.dto.Test123Request;
import com.helloworld.dto.Test123Response;

@RestController
public class Test123Controller {
	@Inject
	private ObjectMapper objectMapper;

	@Inject
	private HttpClient httpClient;

	@RequestMapping("/test123")
	public @ResponseBody Test123Response sum(@RequestBody Test123Request test123Request) {
		String result = "";
		
		HttpGet getRequest = new HttpGet("http://www.omdbapi.com/?apikey=c92fd75d&i=tt1375666&plot=full");

		HttpResponse response;
		try {
			response = httpClient.execute(getRequest);
			
			result += EntityUtils.toString(response.getEntity());
			System.out.println("Reached...111...response" + result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		try (Socket socket = new Socket("ec2-3-134-94-69.us-east-2.compute.amazonaws.com", 9001)) {
//		try (Socket socket = new Socket("172.31.37.242", 9001)) {
            InputStream input = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(input);
 
            int character;
            StringBuilder data = new StringBuilder();
 
            while ((character = reader.read()) != -1) {
                data.append((char) character);
            }
            result += data.toString();
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
		return new Test123Response(result);
	}
}
