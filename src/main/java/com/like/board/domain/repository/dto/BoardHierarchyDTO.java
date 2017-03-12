package com.like.board.domain.repository.dto;

import java.io.Serializable;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class BoardHierarchyDTO implements Serializable {

	private static final long serialVersionUID = -4534626900084055780L;

	Long id;
	
	String text;
	
	String leaf;
	
	String qtitle;
	
	String qtip;
	
	Long parentId;	
	
	@QueryProjection
	public BoardHierarchyDTO(Long id, String text, String leaf, String qtitle, String qtip, Long parentId) {
		this.id = id;
		this.text = text;
		this.leaf = leaf;
		this.qtitle = qtitle;
		this.qtip = qtip;
		this.parentId = parentId;
	}
	
}
