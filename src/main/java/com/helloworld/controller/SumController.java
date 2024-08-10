package com.helloworld.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.helloworld.dto.SumRequest;
import com.helloworld.dto.SumResponse;

@RestController
public class SumController {
	@RequestMapping("/sum")
	public @ResponseBody SumResponse sum(@RequestBody SumRequest sumRequest) {
		return new SumResponse(sumRequest.getA() + sumRequest.getB() + 10);
	}
}
