package com.like.board.infra.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.like.board.domain.repository.dto.ArticleListDTO;

@Mapper
public interface BoardMapper {
					
	/**
	 * 게시글 리스트 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<ArticleListDTO> getArticleList(Map<String, Object> map);
			
}
