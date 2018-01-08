package com.like.board;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.like.board.domain.model.Board;
import com.like.board.domain.repository.dto.ArticleListDTO;
import com.like.board.domain.model.Article;
import com.like.board.service.BoardCommandService;
import com.like.board.service.BoardQueryService;
import com.like.file.domain.model.FileInfo;
import com.like.file.service.FileService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BoardServiceTest {

	private static final Logger log = LoggerFactory.getLogger(BoardServiceTest.class);
	
	 
	
	@Autowired
	BoardCommandService bcs;
	
	@Autowired
	BoardQueryService bqs;
	
	private String user;
	
	@Autowired
	FileService fs;
	
	@Before 
    public void setUp() { 
		       
    } 
	
	@Test
	@Transactional
	public void 게시판등록() {
		Board board = new Board("테스트 게시판"); 
		
		bcs.saveBoard(board);
		
		Board confirmBoard = bqs.getBoard(board.getPkBoard());
		
		assertThat(confirmBoard.getPkBoard(), is(board.getPkBoard()));			
	}
	
	/*@Test
	public void 게시글명단조회() {
		Map<String, Object> map = new HashMap<>();
		map.put("pkBoard", 1);
		
		List<ArticleListDTO> list = bqs.getArticleList(map);
		
		log.info(list.toString());		
	}
	
	@Test
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
	
	/*@Test
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
	}*/
	
	
}
