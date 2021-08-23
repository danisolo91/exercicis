package com.example.nivell2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProvaController {

	@GetMapping("/")
	public String helloUser(@RequestParam(value = "name", defaultValue = "World") String name) {
		return "Hello, " + name;
	}
}
