package com.domingueti.crypto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.domingueti.crypto.responsedto.DataResponseDTO;
import com.domingueti.crypto.responsedto.TotalGainsDTO;
import com.domingueti.crypto.services.CalculateGainsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "messari")
public class MessariController {
	
	@Autowired
	private RestTemplate rest;
	
	@Autowired
	private CalculateGainsService calculateGainsService;
	
	@Autowired
	private ObjectMapper mapper;
	
	private String url = "https://data.messari.io/api/v1/assets/";


	@GetMapping(value = "get/{crypto}")
	public ResponseEntity<DataResponseDTO> getMessariCrypto(@PathVariable("crypto") String crypto) throws JsonMappingException, JsonProcessingException {
		String response = rest.getForEntity(url + crypto + "/metrics", String.class).getBody();
		//create a service to call getForEntity RestTemplate's method.
		
		DataResponseDTO responseData = mapper.readValue(response, DataResponseDTO.class);
		return ResponseEntity.ok().body(responseData);
	}
	
	@GetMapping(value = "get/{crypto}/{amount}")
	public ResponseEntity<TotalGainsDTO> getMessariCryptoMoney(@PathVariable("crypto") String crypto, @PathVariable("amount") String amount) throws JsonMappingException, JsonProcessingException {
		String response = rest.getForEntity(url + crypto + "/metrics", String.class).getBody();
		DataResponseDTO responseData = mapper.readValue(response, DataResponseDTO.class);
		
		TotalGainsDTO finalResponse = calculateGainsService.execute(amount, responseData);
		
		return ResponseEntity.ok().body(finalResponse);
	}
	
}
