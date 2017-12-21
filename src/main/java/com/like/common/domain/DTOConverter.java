package com.like.common.domain;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.like.common.domain.annotation.DTOInfo;

public class DTOConverter {
	
	/**
	 * DTO -> Entity 변환, 동일 필드명에 대한 값 복사
	 * @param entity
	 * @param dto
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 */
	public static <T extends Object, Y extends Object> Y convertEntity(Y entity, T dto) throws IllegalArgumentException, IllegalAccessException, SecurityException {		
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
	
	/**
	 * DTO -> Entity 변환, 특정 엔티티의 필드 변환 기능 (DTOInfo 어노테이션 활용)
	 * 필드에 클래스, 컬렉션이 있을 경우 테스트해보아야함
	 * @param entity
	 * @param dto
	 * @param entityName
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 */
	public static <T extends Object, Y extends Object> Y convertEntityByAnnotation(Y entity, T dto) throws IllegalArgumentException, IllegalAccessException, SecurityException {		
		List<Field> dtoFields = getAllFields(dto.getClass());
		List<Field> entityFields = getAllFields(entity.getClass());									
		
		for (Field dtoField: dtoFields) {			
			DTOInfo dtoInfo = getAnnotation(dtoField);
			
			if (dtoInfo != null) {
				String entityName = dtoInfo.entityName();																									
				
				if ( entity.getClass().getSimpleName().equals(entityName) ) {					
					for (Field entityField : entityFields ) {									
						if ( vaildationField(dtoField,entityField) ) {
							entityField.set(entity, dtoField.get(dto));
						}
					}					
				}
			}
		}
					
		return entity;	
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
	 * 복사 가능한 필드인지 검증한다.
	 * @param originalField
	 * @param destinationField
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private static boolean vaildationField(Field originalField, Field destinationField) throws IllegalArgumentException, IllegalAccessException {
		boolean rtn = false;
		String originalFieldName = null;
		DTOInfo dtoInfo = null;
		
		originalField.setAccessible(true);
		destinationField.setAccessible(true);
		
		dtoInfo = getAnnotation(originalField);
		
		// 어노테이션이 있을 경우 필드명 확인
		if (dtoInfo != null) {					
			originalFieldName = dtoInfo.fieldName();
		} else {
			originalFieldName = originalField.getName(); 
		}
		
		if ( vaildationFieldType(originalField, destinationField) && destinationField.getName().equals(originalFieldName) ) {							
			rtn = true;				
		}			
		return rtn;
	}
		
	private static boolean vaildationFieldType(Field originalField, Field destinationField) {		
		return destinationField.getType().equals(originalField.getType());
	}
	
	private static DTOInfo getAnnotation(Field field) {			
		return field.isAnnotationPresent(DTOInfo.class) ? field.getAnnotation(DTOInfo.class) : null;
	}
	
	
	
}
