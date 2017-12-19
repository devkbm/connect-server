package com.like.common.domain;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
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
		
	
	/*private boolean vaildationField(Field originalField, Field destinationField) throws IllegalArgumentException, IllegalAccessException {
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
	}*/	
		
			
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
	
	/**
	 * 복사 가능한 필드인지 검증한다.
	 * @param originalField
	 * @param destinationField
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private static boolean vaildationField(Field originalField, Field destinationField) throws IllegalArgumentException, IllegalAccessException {
		boolean rtn = false;
		
		originalField.setAccessible(true);
		destinationField.setAccessible(true);				
				
		if ( destinationField.getType().equals(originalField.getType()) &&
			 destinationField.getName().equals(originalField.getName()) ) {							
			rtn = true;				
		}
				
		return rtn;
	}
	
	/**
	 * 전체 필드 리스트를 조회한다.
	 * @param objClass
	 * @return
	 */
	public static List<Field> getAllFields(Class<?> objClass) {
		List<Field> fields = new ArrayList<>();
		
		do {				
			Collections.addAll(fields, objClass.getDeclaredFields());
			objClass = objClass.getSuperclass();
		} while (objClass != null);
		
		return fields.size() > 0 ? fields : null; 
	}
	
	/**
	 * DTO -> Entity 변환, 1:1만 대응
	 * @param entity
	 * @param dto
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 */
	public static <T extends Object, Y extends Object> Y convertEntity(Y entity,T dto) throws IllegalArgumentException, IllegalAccessException, SecurityException {		
		List<Field> dtoFields = getAllFields(dto.getClass());
		List<Field> entityFields = getAllFields(entity.getClass());									
		
		for (Field dtoField: dtoFields) {								
			for (Field entityField : entityFields ) {				
				if ( vaildationField(dtoField,entityField) ) {
					entityField.set(entity, dtoField.get(dto));
				}
			}					
		}
					
		return entity;	
	}
		
}
