package com.like.common.domain;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DTOConverter {
	
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
		
		originalField.setAccessible(true);
		destinationField.setAccessible(true);				
				
		if ( destinationField.getType().equals(originalField.getType()) &&
			 destinationField.getName().equals(originalField.getName()) ) {							
			rtn = true;				
		}
				
		return rtn;
	}		
}
