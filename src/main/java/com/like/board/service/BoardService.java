package com.like.board.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.board.domain.model.Article;
import com.like.board.domain.model.Board;
import com.like.board.domain.repository.BoardRepository;

@Service("boardService")
@Transactional
public class BoardService {
	
    @Resource(name="boardJpaRepository")
	private BoardRepository boardRepository;
    
    public Board getBoard(Long id) {
    	return boardRepository.getBoard(id);
    }
    
	public List<Board> getBoardList() {
		return boardRepository.getBoardList();
	}
		
	public void saveBoard(Board board) throws Exception {		
		boardRepository.saveBoard(board);		
	}
	
	public void deleteBoard(Long id) {
		boardRepository.deleteBoard(id);
	}
	
	
	public List<Map<String,Object>> getBoardListByTree(Map<String,Object> map) throws Exception {
		//return boardRepository.getBoardListByTree(map);
		return null;
	}
	
	
	public String saveArticle(Article article, Long fkBoard) throws Exception {										
		return boardRepository.saveArticle(article, fkBoard).toString();
	}

	public void deleteArticle(Article article) throws Exception {					
		boardRepository.deleteArticle(article);
	}
	
	public int updateArticleHitCnt(Long pkAriticle, String userId) throws Exception {
		
		return boardRepository.updateArticleHitCnt(pkAriticle, userId);
	}	
		
}
