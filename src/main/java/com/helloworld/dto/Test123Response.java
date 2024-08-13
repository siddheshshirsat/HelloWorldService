package com.helloworld.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Test123Response {
	private String httpResult;
	private String cacheResult;
	private long httpCallLatency;
	private long cacheCallLatency;
}
