package com.helloworld.spring;

import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.helloworld.cache.Cache;

@Configuration
public class HelloWorldConfig {

	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}
	
	@Bean
	public Cache getCache() throws UnknownHostException, IOException {
		return new Cache();
	}

	@Bean
	public HttpClient getHttpClient() {
		return HttpClients.createDefault();
	}
}
