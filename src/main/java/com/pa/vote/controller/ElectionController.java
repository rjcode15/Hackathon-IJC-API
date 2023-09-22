package com.pa.vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pa.vote.service.GoogleCivicService;

@RestController
@CrossOrigin
public class ElectionController {
	
	@Autowired
	GoogleCivicService civicService;
	
	@GetMapping(value = "/v1/elections/upcoming")
	public String getElectionInfo() {
		
		return civicService.getElectionInfo();
	}
	
	@GetMapping(value = "/v1/elections/polling-location-info")
	public String getVotingInfo(@RequestParam String address, @RequestParam String electionId) {
		
		return civicService.getVotingInfo(address,electionId);
	}
	
	@GetMapping(value = "/v1/elections/representatives")
	public String getElectionCandidates(@RequestParam String address) {
		
		return civicService.getCandidateInfo(address);
	}
	
}
