package com.code.urlshorten.urlshorten.Service;

import java.net.ResponseCache;
import java.util.Map;

import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cache.CacheManager;

import com.code.urlshorten.urlshorten.DTO.UrlRequest;
import com.code.urlshorten.urlshorten.DTO.UrlResponse;
import com.code.urlshorten.urlshorten.Entity.UrlMapping;
import com.code.urlshorten.urlshorten.exception.UrlNotFoundException;
import com.code.urlshorten.urlshorten.repository.UrlMappingRepository;
import com.code.urlshorten.urlshorten.util.Base62Encoder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UrlService {
	
	private final UrlMappingRepository repository;
	
	private final CacheManager cacheManager;

	//method to create shortUrl
	public UrlResponse createShortUrl(UrlRequest urlRequest) {
		
		
		//THIS is is like getter setter we are setting the data to the UrlMapping Entity
		UrlMapping mapping = UrlMapping.builder()
				.originalUrl(urlRequest.getUrl())
				.clickCount(0L)
				.build();
		
		
		//saving the above mapping data using repository
		mapping = repository.save(mapping);
		
		String shortCode = Base62Encoder.endcode(mapping.getId());
		
		mapping.setShortCode(shortCode);
		
		repository.save(mapping);
		
		
		//setting the response to UrlResponse Bean
		return UrlResponse.builder()
				.shortUrl("http://localhost:8080/" + shortCode)
				.build();
	}
	
	
	
	@Cacheable(value = "urls", key = "#shortCode")
	public String getOriginalUrl(String shortCode) {
		
		
		
		System.out.println("Fetching from the database....");
		
	
		UrlMapping mapping = repository.findByShortCode(shortCode).orElseThrow(() -> new UrlNotFoundException("Short URL Not Found"));
		
		mapping.setClickCount(mapping.getClickCount() + 1);
		
		repository.save(mapping);
		
		return mapping.getOriginalUrl();
		
	}



	public void printAllEntries() {
		
		Cache cache = cacheManager.getCache("urls");
		
		if (cache instanceof ConcurrentMapCache mapCache) {

            Map<Object, Object> nativeCache =
                    mapCache.getNativeCache();

            nativeCache.forEach((k, v) ->
                    System.out.println(
                            k + " -> " + v
                    ));
        }
	}
	
//	public String analytics(@PathVariable String shortCode) {
//		
//		return ResponseEntity<T>
//	}

}
