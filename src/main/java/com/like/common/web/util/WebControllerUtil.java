package com.like.common.web.util;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.like.common.web.response.ExtjsReturnObject;


public class WebControllerUtil {
	
	public enum ResponseBody {Extjs};
	
	private static final ObjectMapper mapper = new ObjectMapper();
	
	private static final ResponseBody responseBody = ResponseBody.Extjs;
		
	public WebControllerUtil() {	
								
		//mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);						
					
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
	 * 
	 * @param data			결과 payload 데이터
	 * @param size			data의 사이즈
	 * @param success		성공 여부
	 * @param message		결과 메세지
	 * @param httpStatus	Http 응답 코드
	 * @return Rest 요청 결과 
	 * 
	 * @todo 추후 리턴되는 객체가 변할 경우 어떻게 처리할지 고민 후 개선해 가야함
	 */
	public static ResponseEntity<?> getResponse(List<?> data, int size, boolean success, String message, HttpStatus httpStatus) {
		
		ExtjsReturnObject obj = null;
		
		if ( responseBody == ResponseBody.Extjs ) {
			obj = new ExtjsReturnObject(data, size, success, message);
		}
		
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add("Content-Type", "application/json;charset=UTF-8"); //인코딩을 utf-8로 설정
	    
	    return new ResponseEntity<ExtjsReturnObject>(obj,responseHeaders,httpStatus);	    	    	    	    	
	}
	
}
