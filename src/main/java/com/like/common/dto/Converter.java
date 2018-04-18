package com.like.common.dto;

@FunctionalInterface
public interface Converter {
	
	<T,Y> T convert(Y obj);
}
