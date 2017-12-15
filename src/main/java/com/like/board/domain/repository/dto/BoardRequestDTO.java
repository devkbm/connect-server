package com.like.board.domain.repository.dto;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

import com.like.board.domain.model.Board;
import com.like.common.domain.DTOConverter;

import lombok.Data;

@Data
public class BoardRequestDTO extends DTOConverter implements Serializable {
	
	LocalDateTime sysDt;	
		
	String sysUser;
		
	LocalDateTime updDt;
		
	String updUser;
	
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
	
	public Board toEntity1() {
		
		
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
	
	// 리플렉션을 이용한 객체 복사
	/**
	 * DTO에서 Entity로 값 복사 
	 * @param board 
	 * @return Board 도메인
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	public Board toEntity2(Board board) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		
		Field[] fields = this.getClass().getDeclaredFields();
		
		Field destinationField = null;
		Object copyValue = null;
		
		for (Field originalField: fields) {
			originalField.setAccessible(true);
			copyValue = originalField.get(this);
			
			destinationField = Board.class.getDeclaredField(originalField.getName());
			
			/**
			 *  대상 필드의 Null 및 동일 Type 체크
			 *  원본 필드 값이 Null이 아닌지 체크
			 */
			if ( destinationField != null && 
				 destinationField.getType().equals(originalField.getType()) &&
				 copyValue != null
				 ) {				
				destinationField.setAccessible(true);
				destinationField.set(Board.class, copyValue);
			}								
		}
					
		return board;
		
	}
			
}