package com.code.urlshorten.urlshorten.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.stereotype.Repository;

import com.code.urlshorten.urlshorten.Entity.UrlMapping;
import java.util.List;


@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long>{
	
	Optional<UrlMapping>  findByShortCode(String shortCode);
	
	Optional<UrlMapping>  findByOriginalUrl(String originalUrl);

}
