package com.like.board.infra.jparepository.dto;

import java.io.Serializable;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class BoardHierarchyDTO implements Serializable{

	Long id;
	
	String text;
	
	String leaf;
	
	String qtitle;
	
	String qtip;
	
	String parent_id;	
	
	@QueryProjection
	public BoardHierarchyDTO(Long id, String text, String leaf, String qtitle, String qtip, String parent_id) {
		this.id = id;
		this.text = text;
		this.leaf = leaf;
		this.qtitle = qtitle;
		this.qtip = qtip;
		this.parent_id = parent_id;
	}
	
}
