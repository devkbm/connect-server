package com.like.common.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.like.common.ExtjsReturnObject;


public class WebControllerUtil {
	
	private static final ObjectMapper mapper = new ObjectMapper();

	public WebControllerUtil() {	
								
		//mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);						
					
		/*Hibernate5Module hibernateModule = new Hibernate5Module();
		hibernateModule.configure(Hibernate5Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS, true);			
		mapper.registerModule(hibernateModule);*/
		
		//mapper.registerModule(new Hibernate4Module());
				
		//mapper.registerModule(new JavaTimeModule());		
		//mapper.findAndRegisterModules();
		
	}
	/**
	 * Json 스트링을 List 형태로 변환한다.
	 * @param jsonStr
	 * @param target
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static <T> List<T> toBeanList(String jsonStr, Class<?> target) throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException {
		if (jsonStr == null)
			return null;					
		return mapper.readValue(jsonStr, mapper.getTypeFactory().constructCollectionType(List.class, Class.forName(target.getName())));	
	}
		
	
	/**
     * <p> 게시판 리스트 조회한다. </p>
     *
     * @param List 		recv		리턴 받을 리스트		
	 * @param int		size		갯수
	 * @param boolean	success		성공 여부
	 * @param String 	message		메세지
     * 
     * @return Map
     * 
     * @throws Exception
     */
	public static Map<String, Object> setReturnMap(List<?> recv, int size, boolean success, String message) throws Exception {
		Map<String, Object> retMap = new HashMap<String, Object>();		
		
		retMap.put("recv", 	recv);
		retMap.put("total", size);
		retMap.put("success", success);
		retMap.put("message", message);
												
		return retMap;		
	}
	
	public static Map<String, Object> setReturnMapByString(String recv, int size, boolean success, String message) throws Exception {
		Map<String, Object> retMap = new HashMap<String, Object>();
		
		retMap.put("recv", 	recv);
		retMap.put("total", size);
		retMap.put("success", success);
		retMap.put("message", message);
												
		return retMap;		
	} 
	
	public static ResponseEntity<ExtjsReturnObject> getResponse(List<?> recv, int size, boolean success, String message, HttpStatus httpStatus) {
		
		ExtjsReturnObject obj = new ExtjsReturnObject(recv, size, success, message);
		
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add("Content-Type", "application/json;charset=UTF-8"); //인코딩을 utf-8로 설정
	    
	    return new ResponseEntity<ExtjsReturnObject>(obj,responseHeaders,httpStatus);	    	    	    	    	
	}
	
}
