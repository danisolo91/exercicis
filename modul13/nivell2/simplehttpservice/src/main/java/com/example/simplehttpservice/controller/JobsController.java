package com.example.simplehttpservice.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.simplehttpservice.entity.Job;

@RestController
@RequestMapping("/v1")
public class JobsController {

	@GetMapping("/jobs")
	public ResponseEntity<?> list() {
		Map<String, String> jobs = new HashMap<String, String>();
		
		Stream.of(Job.values()).forEach(j -> jobs.put(j.toString(), j.getJobTitle()));
		
		return ResponseEntity.ok(jobs);
	}
}
