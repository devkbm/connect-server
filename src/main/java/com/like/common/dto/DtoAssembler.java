package com.like.common.dto;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import com.like.common.dto.annotation.DtoField;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 김병민
 *
 */
@Slf4j
public class DtoAssembler {	
	
	/**
	 * DTO -> Entity 값 복사, 동일 필드명에 대한 값 복사
	 * @param entity	복사 대상 Entity 클래스
	 * @param dto		원본 DTO 클래스
	 * @return	Entity 클래스
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 */
	public static <T extends Object, Y extends Object> Y convertEntity(Y entity, T dto) throws IllegalArgumentException, IllegalAccessException, SecurityException {		
		List<Field> dtoFields = getAllFields(dto.getClass());
		List<Field> entityFields = getAllFields(entity.getClass());									
		
		for (Field dtoField: dtoFields) {								
			for (Field entityField : entityFields ) {				
				if ( vaildationField(dtoField, entityField) ) {
					entityField.set(entity, dtoField.get(dto));
				}
			}					
		}
					
		return entity;	
	}		
	
	/**
	 * DTO -> Entity 값 복사, 특정 엔티티의 필드 변환 기능 (DTOInfo 어노테이션 활용)
	 * @param dto			원본 DTO 클래스
	 * @param entity		복사 대상 Entity 클래스
	 * @param entityClass	entity 클래스 정보
	 * @return	Entity 클래스
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static <T extends Object, Y extends Object> Y convertEntityByAnnotation(T dto, Y entity, Class<?> entityClass) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		
		if (entity == null)				
			entity = (Y) entityClass.newInstance();		
		
		List<Field> dtoFields = getAllFields(dto.getClass());
		List<Field> entityFields = getAllFields(entity.getClass());									
		
		for (Field dtoField: dtoFields) {			
			DtoField dtoAnnotation = getAnnotation(dtoField);
			
			if (dtoAnnotation != null) {																												
				Class<?> cls = dtoAnnotation.targetEntity();									
				
				if ( entity.getClass().equals(cls) ) {
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
	
	public static <T extends Object, Y extends Object> Y ConvertDtoToEntity(T dto, Y entity) throws Exception {
					
		List<Field> dtoFields = getAllFields(dto.getClass());
		List<Field> entityFields = getAllFields(entity.getClass());									
		
		for (Field dtoField: dtoFields) {			
			DtoField dtoAnnotation = getAnnotation(dtoField);
			
			if (dtoAnnotation != null) {																												
				Class<?> cls = dtoAnnotation.targetEntity();													
				Class<?> converter = dtoAnnotation.converter();
				
				if ( entity.getClass().equals(cls) ) {
					
					for (Field entityField : entityFields ) {						
						
						if ( !converter.equals(void.class) && isEqualFieldName(dtoField,entityField)) {
							// TODO: convert 함수 호출하여 값 변환
							//Method method = converter.getMethod("convert", entityField.getType());
							//method.invoke(converter.getClass(), dtoField.get(dto));
						
						} else if ( vaildationField(dtoField,entityField) ) {
							entityField.set(entity, dtoField.get(dto));
						}
					}					
				}
			}
		}
					
		return entity;	
	}
		
	
	/**
	 * 전체 필드 리스트를 조회한다.(부모 객체 포함)
	 * @param objClass	대상 클래스
	 * @return	Field 리스트
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
	 * 복사 가능한 필드인지 검증한다. (타입, 필드명 동일한지 체크)
	 * @param originalField		원본 필드
	 * @param destinationField	대상 필드
	 * @return	복사 가능 여부
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private static boolean vaildationField(Field originalField, Field destinationField) throws IllegalArgumentException, IllegalAccessException {
		boolean rtn = false;
		String originalFieldName = null;		
		
		if ( !originalField.isAccessible() )
			originalField.setAccessible(true);
		if ( !destinationField.isAccessible() )
			destinationField.setAccessible(true);			
								
		originalFieldName = getFieldNameByAnnotation(originalField);		
		
		if ( isEqualFieldName(originalFieldName, destinationField) ) {			
			if ( isEqualFieldType(originalField, destinationField) )
				rtn = true;									
		}		
		
		return rtn;
	}
	
	/**
	 * 동일 타입인지 확인 한다.
	 * @param originalField		원본 필드
	 * @param destinationField	대상 필드
	 * @return	동일 필드 타입 여부
	 */
	private static boolean isEqualFieldType(Field originalField, Field destinationField) {		
		return destinationField.getType().equals(originalField.getType());
	}
	
	/**
	 * 동일 필드명인지 확인 한다.
	 * @param originalField		원본 필드
	 * @param destinationField	대상 필드
	 * @return	돌일 필드명 확인 여부
	 */
	private static boolean isEqualFieldName(Field originalField, Field destinationField) {
		return destinationField.getName().equals(originalField.getName());
	}
	
	/**
	 * 동일 필드명인지 확인 한다.
	 * @param originalFieldName	원본 필드명
	 * @param destinationField	대상 필드
	 * @return	돌일 필드명 확인 여부
	 */
	private static boolean isEqualFieldName(String originalFieldName, Field destinationField) {
		return destinationField.getName().equals(originalFieldName);
	}
	
	/**
	 * DtoField 어노테이션 정보를 가져온다.
	 * @param field	필드
	 * @return	DTOInfo 어노테이션 정보, 없으면 null 리턴
	 */
	private static DtoField getAnnotation(Field field) {			
		return field.isAnnotationPresent(DtoField.class) ? field.getAnnotation(DtoField.class) : null;
	}
	
	/**
	 * DtoField 어노테이션의 필드명을 가져온다.
	 * @param field	대상 필드
	 * @return	대상 필드의 DTOInfo의 필드명이 있을 경우 해당 필드명 리턴, 없으면 대상 필드명 리턴  
	 */
	private static String getFieldNameByAnnotation(Field field) {		
		DtoField dtoAnnotation = null;
		String fieldName = null;
		
		if (field.isAnnotationPresent(DtoField.class)) {
			dtoAnnotation = field.getAnnotation(DtoField.class);
						
			fieldName = dtoAnnotation.fieldName().length() > 0 ? dtoAnnotation.fieldName() : field.getName();			
		} else {
			fieldName = field.getName();
		}
		
		return fieldName;
	}
	
}
