package com.code.urlshorten.urlshorten.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UrlRequest {
	
	
	//it checks url is blank or not, if it is blank it throw a validation
	@NotBlank
	private String url;
	
	

}
