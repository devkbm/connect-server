package com.like.board.infra.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.like.board.infra.mapper.dto.ArticleListDTO;

@Mapper
public interface BoardMapper {
				
	/**
	 * 게시판 정보를 트리형태로 조회한다.
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> getBoardListByTree(Map<String, Object> map);
	
	/**
	 * 게시글 리스트 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<ArticleListDTO> getArticleList(Map<String, Object> map) throws Exception;
			
}
