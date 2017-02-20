package com.like.board.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.board.domain.model.Article;
import com.like.board.domain.model.ArticleCheck;
import com.like.board.domain.model.Board;

@Repository
public interface BoardRepository {
	
	Board getBoard(Long id);
	
	List<Board> getBoardList();
	
	List<Board> getBoardList(String boardName);
		
	void saveBoard(Board board);
	
	void deleteBoard(Long pkBoard);
	
	List<Article> getArticleList(Long fkBoard);
	
	Long saveArticle(Article article, Long fkBoard);
	
	public void deleteArticle(Article article);
	
	Long getArticleNextSeq(Long pkBoard);
	
	int updateArticleHitCnt(Long pkAriticle, String userId);
		
	ArticleCheck getArticleCheck(Long fkarticle, String userId);
	
}