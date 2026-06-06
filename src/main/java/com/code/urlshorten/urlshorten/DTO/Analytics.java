package com.code.urlshorten.urlshorten.DTO;

import lombok.Data;

@Data
public class Analytics {

	private String shortCode;
	private Long clicks;
	private String originalUrl;
	
}
