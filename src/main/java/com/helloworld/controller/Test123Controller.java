package com.helloworld.controller;

import javax.inject.Inject;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.helloworld.cache.Cache;
import com.helloworld.dto.Test123Request;
import com.helloworld.dto.Test123Response;

@RestController
public class Test123Controller {
	@Inject
	private Cache cache;

	@Inject
	private HttpClient httpClient;

	@RequestMapping("/test123")
	public @ResponseBody Test123Response test123(@RequestBody Test123Request test123Request) {
		String httpResult = "";
		
		long startTime = System.currentTimeMillis();
		
		HttpGet getRequest = new HttpGet("http://www.omdbapi.com/?apikey=c92fd75d&i=tt1375666&plot=full");

		HttpResponse response;
		try {
			response = httpClient.execute(getRequest);
			
			httpResult = EntityUtils.toString(response.getEntity());
			System.out.println("Reached...111...httpResult" + httpResult);
			long httpCallLatency = System.currentTimeMillis() - startTime;

			startTime = System.currentTimeMillis();
			String cacheResult = cache.getValue("test123");
			long cacheCallLatency = System.currentTimeMillis() - startTime;

			return new Test123Response(httpResult, cacheResult, httpCallLatency, cacheCallLatency);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
