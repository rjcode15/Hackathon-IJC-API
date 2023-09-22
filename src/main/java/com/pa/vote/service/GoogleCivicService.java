package com.pa.vote.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleCivicService {
	
	@Value("${auth.key}")
	private String authKey;
	
	@Value("${google.civic.elections.api}")
	private String electionsAPI;
	
	@Value("${google.civic.voterinfo.api}")
	private String voterInfoAPI;
	
	@Value("${google.civic.representatives.api}")
	private String candidatesAPI;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GoogleCivicService.class);	

	public String getElectionInfo() {
		Map<String, String> headerParams = new HashMap<>();
		LOGGER.info("Inside the Election Info--");
		String url = electionsAPI+"?key=" + authKey;
		return callRestApi(url, "", headerParams);
	}

	public String getVoterInfo(String address, String electionId) {
		Map<String, String> headerParams = new HashMap<>();
		String url = voterInfoAPI+"?address=" + address + "&key=" + authKey + "&electionId=" + electionId;
		return callRestApi(url, "", headerParams);
	}

	public String getCandidateInfo(String address) {
		Map<String, String> headerParams = new HashMap<>();
		String url = "?address=" + address + "&key=" + authKey;
		return callRestApi(url, "", headerParams);
	}

	public String callRestApi(String url, String payload, Map<String, String> headerparams) {
		try {
			HttpEntity<String> entity = null;
			HttpHeaders httpHeaders = headers();
			RestTemplate restTemplate = new RestTemplate();
			if (Objects.nonNull(headerparams)) {
				headerparams.forEach((key, value) -> {
					httpHeaders.add(key, value);
				});
			}
			entity = new HttpEntity<>(httpHeaders);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			LOGGER.debug(response.getBody());
			return response.getBody();
		} catch (Exception e) {
			LOGGER.error("Error During Rest API call {}", e.getMessage());
			return "ERROR in API";
		}
	}

	public HttpHeaders headers() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return httpHeaders;
	}
}

