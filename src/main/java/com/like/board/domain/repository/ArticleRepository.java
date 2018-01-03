package com.like.board.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.board.domain.model.Article;
import com.like.board.domain.model.ArticleCheck;
import com.like.board.domain.repository.dto.ArticleResponseDTO;
import com.like.file.domain.model.FileInfo;

@Repository
public interface ArticleRepository {
	
	/**
	 * 게시글 도메인 조회
	 * @param id	게시글 도메인 PK
	 * @return 게시글 도메인
	 */
	Article getArticle(Long id);
	
	/**
	 * 게시글 DTO 조회
	 * @param id	게시글 도메인 PK
	 * @return	게시글 DTO
	 */
	ArticleResponseDTO getArticleDTO(Long id);
	
	/**
	 * 게시글 도메인 리스트 조회
	 * @param fkBoard	게시글 도메인 FK
	 * @return 게시글 도메인 리스트
	 */
	List<Article> getArticleList(Long fkBoard);
	
	/**
	 * 게시글 도메인 리스트 조회
	 * @param fkBoard		게시글 도메인 FK
	 * @param likeTitle		제목
	 * @param likeContents	내용
	 * @return	게시글 도메인 리스트
	 */
	List<Article> getArticleList(Long fkBoard, String likeTitle, String likeContents);
	
	/**
	 * 게시글 도메인 저장
	 * @param article	게시글 도메인
	 * @param fkBoard	
	 * @return Article 도메인의 PK
	 */
	Long saveArticle(Article article, Long fkBoard);
	
	/**
	 * 게시글 도메인 삭제
	 * @param article	게시글 도메인
	 */
	void deleteArticle(Article article);
	
	/**
	 * 게시글 도메인 삭제
	 * @param id	게시글 PK
	 */
	void deleteArticle(Long id);
	
	/**
	 * 게시글 도메인 리스트 삭제
	 * @param articleList	게시글 리스트
	 */
	void deleteArticle(List<Article> articleList);
	
	/**
	 * 신규 게시글 생성시 사용될 순번을 조회
	 * @param pkBoard	게시판 PK
	 * @return	마지막 순번
	 */
	Integer getArticleNextSeq(Long pkBoard);
	
	/**
	 * 게시글 조회수 증가
	 * @param pkAriticle	게시글 도메인 PK
	 * @param userId		사용자 ID
	 * @return	게시글 도메인
	 */
	Article updateArticleHitCnt(Long pkAriticle, String userId);
	
	/**
	 * 게시글에 첨부된 파일 리스트를 조회
	 * @param pkArticle	게시글 도메인 PK
	 * @return
	 */
	List<FileInfo> getFileInfoList(Long pkArticle);
		
	/**
	 * 게시글 확인 도메인을 조회 
	 * @param fkarticle		게시글 도메인 FK
	 * @param userId		사용자 ID
	 * @return	게시글 확인 도메인
	 */
	ArticleCheck getArticleCheck(Long fkarticle, String userId);
}
