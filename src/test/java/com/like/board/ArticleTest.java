package com.like.board;

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
import com.like.board.domain.repository.dto.ArticleListDTO;
import com.like.board.domain.model.Article;
import com.like.board.service.BoardService;
import com.like.file.domain.model.FileInfo;
import com.like.file.service.FileService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ArticleTest {

	private static final Logger log = LoggerFactory.getLogger(ArticleTest.class);
	
	@Autowired
	BoardService bs;
	
	@Autowired
	FileService fs;
		
	
	@Test
	public void 게시글명단조회() {
		Map<String, Object> map = new HashMap<>();
		map.put("pkBoard", 1);
		
		List<ArticleListDTO> list = bs.getArticleList(map);
		
		log.info(list.toString());		
	}
	
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
		
		bs.saveBoard(board);
		bs.saveArticle(article, board.getPkBoard());
		
		log.info("-------------------------------------");
		log.info(article.getPkArticle().toString());
		log.info(bs.getAritlce(article.getPkArticle()).toString());
		log.info(bs.getAritlce(article.getPkArticle()).getFiles().toString());
	}
	
	
}
