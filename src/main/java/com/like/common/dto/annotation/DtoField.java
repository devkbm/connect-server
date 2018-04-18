package com.like.common.dto.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.like.common.dto.Converter;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DtoField {
	
	/**
	 * 매핑될 Class 인스턴스
	 */
	Class<?> targetEntity();
	
	/**
	 * 매핑될 필드명
	 */
	String fieldName() default "";
	
	/**
	 * Type 및 값 변경시 사용될 Class 인스턴스
	 * Convert 함수 구현 필요
	 */
	Class<?> converter() default void.class;
}
