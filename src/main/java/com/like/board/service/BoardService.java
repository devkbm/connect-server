package com.like.board.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.board.domain.model.Article;
import com.like.board.domain.model.Board;
import com.like.board.domain.repository.BoardRepository;
import com.like.board.infra.mapper.BoardMapper;

@Service("boardService")
@Transactional
public class BoardService {
	
    @Resource(name="boardJpaRepository")
	private BoardRepository boardRepository;
    
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
		return boardRepository.getArticle(id);		
	}
	
	public List<Article> getAritlceList(Long fkBoard) {
		return boardRepository.getArticleList(fkBoard);
	}
	
	public String saveArticle(Article article, Long fkBoard) {										
		return boardRepository.saveArticle(article, fkBoard).toString();
	}

	public void deleteArticle(Article article) throws Exception {					
		boardRepository.deleteArticle(article);
	}
	
	public Article updateArticleHitCnt(Long pkAriticle, String userId) {		
		return boardRepository.updateArticleHitCnt(pkAriticle, userId);
	}	
		
}