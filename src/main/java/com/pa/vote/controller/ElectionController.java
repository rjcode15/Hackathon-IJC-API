package com.pa.vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pa.vote.service.GoogleCivicService;

@RestController
public class ElectionController {
	
	@Autowired
	GoogleCivicService civicService;
	
	@GetMapping(value = "/v1/elections/info")
	public String getElectionInfo() {
		
		return civicService.getElectionInfo();
	}
}
