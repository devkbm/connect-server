package com.like.board.domain.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.like.board.domain.model.Article;
import com.like.board.domain.model.ArticleCheck;
import com.like.board.domain.model.Board;

@Repository
public interface BoardRepository {
	
	Board getBoard(Long id);
	
	List<Board> getBoardList();
	
	List<Board> getBoardList(String boardName);
	
	List<Map<String, Object>> getBoardListByTree(Map<String, Object> map);
	
	void saveBoard(Board board);	
	
	Long saveArticle(Article article, Long fkBoard);
	
	public void deleteArticle(Article article);
	
	Long getArticleNextSeq(Long pkBoard);
	
	int updateArticleHitCnt(Long pkAriticle, String userId);
		
	ArticleCheck getArticleCheck(Long fkarticle, String userId);
	
}
