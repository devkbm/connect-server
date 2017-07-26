package com.like;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.like.board.domain.model.Board;
import com.like.board.domain.model.Article;
import com.like.board.service.BoardCommandService;
import com.like.board.service.BoardQueryService;
import com.like.file.domain.model.FileInfo;
import com.like.file.service.FileService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class JpaTest {

	private static final Logger log = LoggerFactory.getLogger(JpaTest.class);
	
	@Autowired
	BoardCommandService bcs;
	
	@Autowired
	BoardQueryService bqs;
	
	@Autowired
	FileService fs;
	
	/*@Test
	public void getBoardHierarchy() throws Exception {		
		List list = bs.getBoardHierarchy(2L);		
		log.info(list.toString());
	}
	
	@Test
	public void insertBoard() throws Exception {
		Board board = new Board("Test");
		List<Article> articles = new ArrayList<Article>();
		Article article = new Article();
		
		FileInfo file= new FileInfo();
		file.setFileNm("test");
		article.addAttachedFile(file);
		
		articles.add(article);
		board.setArticles(articles);
		bs.saveBoard(board);		
		
		bs.getBoardList();
	}*/
	
	@Test
	public void 게시글파일저장() throws Exception {
		Board board = new Board("Test");
		List<Article> articles = new ArrayList<Article>();
		Article article = new Article("test","test");		
		
		MockMultipartFile file = new MockMultipartFile("user-file", "test.txt",
                                  "multipart/form-data", "test data".getBytes());
		
		FileInfo info = fs.uploadFile(file, "test","test");
		
		article.addAttachedFile(info);		
		
		articles.add(article);
		board.setArticles(articles);
		
		bcs.saveBoard(board);
		bcs.saveArticle(article, board.getPkBoard());
		
		log.info("-------------------------------------");
		log.info(article.getPkArticle().toString());
		log.info(bqs.getAritlce(article.getPkArticle()).toString());
		log.info(bqs.getAritlce(article.getPkArticle()).getFiles().toString());
	}
	
	
}
