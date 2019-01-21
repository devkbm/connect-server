package com.like.board.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.like.board.domain.model.Article;
import com.like.board.domain.model.AttachedFile;
import com.like.board.domain.model.Board;
import com.like.board.domain.repository.ArticleRepository;
import com.like.board.domain.repository.BoardRepository;
import com.like.file.domain.model.FileInfo;
import com.like.file.service.FileService;

@Service
@Transactional
public class BoardCommandService {
	
    @Resource(name="boardJpaRepository")
	private BoardRepository boardRepository;
    
    @Resource(name="articleJpaRepository")
	private ArticleRepository articleRepository;          
		
    @Resource(name="fileService")
    private FileService fileService;
    
	public void saveBoard(Board board) {		
		boardRepository.saveBoard(board);		
	}
	
	public void deleteBoard(Long id) {
		boardRepository.deleteBoard(id);
	}			
	
	public void deleteBoard(Board board) {
		boardRepository.deleteBoard(board);
	}
	
	//public String saveArticle(Article article, List<FileInfo> fileInfoList) {
	public String saveArticle(Article article, List<MultipartFile> fileList) throws Exception {
		
		List<FileInfo> fileInfoList;
		
		// 게시글 저장
		String pkArticle = articleRepository.saveArticle(article).getPkArticle().toString(); 						
		
		// 첨부파일 저장
		if (!fileList.isEmpty()) {
			
			fileInfoList = fileService.uploadFile(fileList, "test", "board");
												
			List<AttachedFile> attachedFileList = fileInfoList.stream()
																.map( v -> new AttachedFile(article, v) )
																.collect(Collectors.toList());						
			
			articleRepository.saveAttachedFile(attachedFileList);
		}
				
		return pkArticle;
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