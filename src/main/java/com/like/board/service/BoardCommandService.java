package com.like.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.board.domain.model.Article;
import com.like.board.domain.model.Board;
import com.like.board.domain.repository.ArticleRepository;
import com.like.board.domain.repository.BoardRepository;

@Service
@Transactional
public class BoardCommandService {
	
    @Resource(name="boardJpaRepository")
	private BoardRepository boardRepository;
    
    @Resource(name="articleJpaRepository")
	private ArticleRepository articleRepository;          
		
	public void saveBoard(Board board) {		
		boardRepository.saveBoard(board);		
	}
	
	public void deleteBoard(Long id) {
		boardRepository.deleteBoard(id);
	}			
	
	public void deleteBoard(Board board) {
		boardRepository.deleteBoard(board);
	}
	
	public String saveArticle(Article article, Long fkBoard) {										
		return articleRepository.saveArticle(article, fkBoard).toString();
	}

	public void deleteArticle(Article article) {					
		articleRepository.deleteArticle(article);
	}
	
	public void deleteArticle(Long id) {					
		articleRepository.deleteArticle(id);
	}
	
	public void deleteArticle(List<Article> articleList) {					
		articleRepository.deleteArticle(articleList);
	}
	
	public Article updateArticleHitCnt(Long pkAriticle, String userId) {		
		return articleRepository.updateArticleHitCnt(pkAriticle, userId);
	}	
		
}