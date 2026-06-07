package com.code.urlshorten.urlshorten.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.code.urlshorten.urlshorten.DTO.UrlRequest;
import com.code.urlshorten.urlshorten.DTO.UrlResponse;
import com.code.urlshorten.urlshorten.Service.UrlService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequiredArgsConstructor
public class UrlController {
	
	
	private final UrlService service;
	
	@PostMapping("/api/urls")
	public ResponseEntity<UrlResponse> createShortUrl(@RequestBody UrlRequest urlRequest) {
		
		UrlResponse urlResponse = service.createShortUrl(urlRequest);
		
		System.out.println(urlResponse.getShortUrl());
		return ResponseEntity.ok(service.createShortUrl(urlRequest));
	}
	
	
	
	@GetMapping("/{shortCode}")
	public ResponseEntity<Void> getOriginalUrl(@PathVariable String shortCode) {
		String originalUrl = service.getOriginalUrl(shortCode);
		System.out.println(ResponseEntity.status(302).header(HttpHeaders.LOCATION, originalUrl).build());
		return ResponseEntity.status(302).header(HttpHeaders.LOCATION, originalUrl).build();
				
	}
	
	
	@GetMapping("/debug/cache")
	public String debugtoCheckCache() {
		service.printAllEntries();
		return "DATA WILL APPEAR IN THE CONSOLE";
	}
	
	
	
	
	

}
