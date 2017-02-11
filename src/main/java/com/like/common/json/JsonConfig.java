/*package com.like.common.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
//import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration; 
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration 
public class JsonConfig {
	
	@Bean 
	public ObjectMapper objectMapper() 
	{ 							
		return Jackson2ObjectMapperBuilder.json()
				.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
				.featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)				
				.modules(new JavaTimeModule())
				.modules(new Hibernate5Module())
				.modules(new Jdk8Module())
				.build();
		return new ObjectMapper();
	} 
}

*/