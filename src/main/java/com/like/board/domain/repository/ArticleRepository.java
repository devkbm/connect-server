package com.like.board.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.board.domain.model.Article;
import com.like.board.domain.model.ArticleCheck;
import com.like.file.domain.model.FileInfo;

@Repository
public interface ArticleRepository {
	
	Article getArticle(Long id);
	
	List<Article> getArticleList(Long fkBoard);
	
	List<Article> getArticleList(Long fkBoard, String likeTitle, String likeContents);
	
	Long saveArticle(Article article, Long fkBoard);
	
	void deleteArticle(Article article);
	
	Long getArticleNextSeq(Long pkBoard);
	
	Article updateArticleHitCnt(Long pkAriticle, String userId);
	
	List<FileInfo> getFileInfoList(Long pkArticle);
		
	ArticleCheck getArticleCheck(Long fkarticle, String userId);
}
