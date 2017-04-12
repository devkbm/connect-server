package com.like.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.board.domain.model.Article;
import com.like.board.domain.model.Board;
import com.like.board.domain.repository.ArticleRepository;
import com.like.board.domain.repository.BoardRepository;
import com.like.board.infra.mapper.BoardMapper;

@Service("boardService")
@Transactional
public class BoardService {
	
    @Resource(name="boardJpaRepository")
	private BoardRepository boardRepository;
    
    @Resource(name="articleJpaRepository")
	private ArticleRepository articleRepository;
    
    @Resource(name="boardMapper")
    private BoardMapper boardMapper;
    
    public Board getBoard(Long id) {
    	return boardRepository.getBoard(id);
    }
    
	public List<Board> getBoardList() {
		return boardRepository.getBoardList();
	}
		
	public void saveBoard(Board board) {		
		boardRepository.saveBoard(board);		
	}
	
	public void deleteBoard(Long id) {
		boardRepository.deleteBoard(id);
	}	
	
	public List<?> getBoardHierarchy(Long parentId) {
		return boardRepository.getBoardHierarchy(parentId);
	}
	
	public Article getAritlce(Long id) {
		return articleRepository.getArticle(id);		
	}
	
	public List<Article> getAritlceList(Long fkBoard) {
		return articleRepository.getArticleList(fkBoard);
	}
	
	public List<Article> getAritlceList(Long fkBoard, String title, String contents) {
		return articleRepository.getArticleList(fkBoard,title,contents);
	}
	
	public String saveArticle(Article article, Long fkBoard) {										
		return articleRepository.saveArticle(article, fkBoard).toString();
	}

	public void deleteArticle(Article article) throws Exception {					
		articleRepository.deleteArticle(article);
	}
	
	public Article updateArticleHitCnt(Long pkAriticle, String userId) {		
		return articleRepository.updateArticleHitCnt(pkAriticle, userId);
	}	
		
}