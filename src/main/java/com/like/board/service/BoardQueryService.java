package com.like.board.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.board.domain.model.Article;
import com.like.board.domain.model.Board;
import com.like.board.domain.repository.ArticleRepository;
import com.like.board.domain.repository.BoardRepository;
import com.like.board.domain.repository.dto.ArticleListDTO;
import com.like.board.infra.mapper.BoardMapper;

@Service
@Transactional(readOnly=true)
public class BoardQueryService {

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
	
	public List<Board> getBoardList(String likeBoardName) {
		return boardRepository.getBoardList(likeBoardName);
	}
		
	public List<?> getBoardHierarchy() {
		return boardRepository.getBoardHierarchy();
	}
	
	public Article getAritlce(Long id) {
		return articleRepository.getArticle(id);		
	}
			
	public List<Article> getAritlceList(Long fkBoard, String title, String contents) {
		return articleRepository.getArticleList(fkBoard,title,contents);
	}
	
	public List<ArticleListDTO> getArticleList(Map<String, Object> map) {
		return boardMapper.getArticleList(map);
	}
}
