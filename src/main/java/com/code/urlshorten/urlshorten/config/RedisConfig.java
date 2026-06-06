package com.code.urlshorten.urlshorten.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;


//this is configuration to enable caching either we can add @EnableCaching directly here or we can add it on the top of main class
@Configuration
@EnableCaching
public class RedisConfig {
	
	

}
