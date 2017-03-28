package com.like.board.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.board.domain.model.Article;
import com.like.board.domain.model.ArticleCheck;
import com.like.board.domain.model.Board;
import com.like.board.domain.repository.dto.BoardHierarchyDTO;
import com.like.file.domain.model.FileInfo;

@Repository
public interface BoardRepository {
	
	Board getBoard(Long id);
	
	List<Board> getBoardList();
	
	List<Board> getBoardList(String boardName);
	
	List<BoardHierarchyDTO> getBoardHierarchy(Long parentId);
		
	void saveBoard(Board board);
	
	void deleteBoard(Long id);
	
	
	Article getArticle(Long id);
	
	List<Article> getArticleList(Long fkBoard);
	
	Long saveArticle(Article article, Long fkBoard);
	
	void deleteArticle(Article article);
	
	Long getArticleNextSeq(Long pkBoard);
	
	Article updateArticleHitCnt(Long pkAriticle, String userId);
	
	List<FileInfo> getFileInfoList(Long pkArticle);
		
	ArticleCheck getArticleCheck(Long fkarticle, String userId);
	
}