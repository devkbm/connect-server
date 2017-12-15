package com.like.common.domain;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DTOConverter {

	public <T> T toEntity(T entity) throws IllegalArgumentException, IllegalAccessException, SecurityException {
		
		Field[] fields = this.getClass().getDeclaredFields();
		
		Field destinationField = null;
		Object copyValue = null;
		
		for (Field originalField: fields) {
			originalField.setAccessible(true);
			copyValue = originalField.get(this);
									
			try {
				destinationField = entity.getClass().getDeclaredField(originalField.getName());
				
			} catch (NoSuchFieldException e) {
				// 상속된 필드는 현재 못가져옴
				/**
				 * 도메인 엔티티가 상속 되었을 경우 super 클래스 필드 가져오는 방법을 생각해보아야 함
				 * 현재는 null로 copy하지 않음.
				 */
				destinationField = null;
			}
			
			if (destinationField != null) {
				/**
				 *  대상 필드의 Null 및 동일 Type 체크
				 *  원본 필드 값이 Null이 아닌지 체크
				 */
				if ( vaildationField(originalField,destinationField) ) {				
					destinationField.setAccessible(true);
					destinationField.set(entity, copyValue);				
				}
			}
		}
					
		return entity;
		
	}
	
	private boolean vaildationField(Field originalField, Field destinationField) throws IllegalArgumentException, IllegalAccessException {
		originalField.setAccessible(true);
		destinationField.setAccessible(true);
		
		Object copyValue = originalField.get(this);
		boolean rtn = false;
				
		if ( destinationField != null && 
			 destinationField.getType().equals(originalField.getType()) &&
			 copyValue != null 				 				
			 ) {							
			rtn = true;				
		}
				
		return rtn;
	}
	
	private Field getEntityField(String fieldName) {
		return null;
	}
			
	public <T> List<String> getAllFieldNames(T entity) {
		
		List<String> fieldNameList = new ArrayList<String>();
		 		
		Field[] fields = entity.getClass().getDeclaredFields();		
		Field[] superClassFields = null;
		
		for (Field field: fields) {
			fieldNameList.add(field.getName());
		}
		
		if (entity.getClass().getSuperclass() != null) {
			superClassFields = entity.getClass().getSuperclass().getDeclaredFields();
			
			for (Field field: superClassFields) {
				fieldNameList.add(field.getName());
			}
		}								
								
		return fieldNameList;	
	}
}
