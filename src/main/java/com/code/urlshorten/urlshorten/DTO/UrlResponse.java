package com.code.urlshorten.urlshorten.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UrlResponse {

	private String shortUrl;
}
