package com.like.common.domain.jpaconverter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GenericEnumConverter<E> implements AttributeConverter<E, String> {

	@Override
	public String convertToDatabaseColumn(E attribute) {
		return attribute.toString();
	}

	@Override
	public E convertToEntityAttribute(String dbData) {
		// TODO Auto-generated method stub
		return null;
	}

}
