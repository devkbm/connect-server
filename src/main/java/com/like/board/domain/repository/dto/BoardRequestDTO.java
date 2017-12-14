package com.like.board.domain.repository.dto;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDate;

import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

import com.like.board.domain.model.Board;

import lombok.Data;

@Data
public class BoardRequestDTO implements Serializable {

	private static final long serialVersionUID = 8495364239247192458L;

	@Id
	Long pkBoard;
	
    /**
     * 상위 게시판 키
     */	
    Long ppkBoard;
		
	/**
	 * 게시판_타입
	 */	
    String boardType;
	
	/**
     * 게시판 명
     */
	@NotEmpty(message="게시판명은 필수 입력사항입니다.")	
    String boardNm;             
    
    /**
     * 게시판_설명
     */	
    String boardDesc;
    
    /**
     * 시작일자
     */	
	LocalDate fromDt;
    
    /**
     * 종료일자
     */	
    LocalDate toDt;    
    
    /**
     * 사용여부
     */	
    Boolean useYn;
    
    /**
     * 게시글 갯수
     */	
    long articleCnt;
    
    /**
     * 출력순서
     */	
    long seq;
		
	String pwdYn;
		
	String pwdMethod;
	
	/*private Object getEntityId(@NotNull Object dto) {
        for (Field field : dto.getClass().getDeclaredFields()) {
            if (field.getAnnotation(Id.class) != null) {
                try {
                    field.setAccessible(true);
                    return field.get(dto);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }*/
	
	public Board toEntity() {
		
		
		for (Field field : this.getClass().getDeclaredFields()) {
			
			if (field.getAnnotation(Id.class) != null) {
				field.setAccessible(true);
				try {
					Object value = field.get(this);					
					//System.out.println(value);
					System.out.println(value);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new RuntimeException(e);
				} 
			}
		}
		return null;
	}
			
}