package com.like.board;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.like.board.domain.model.Board;
import com.like.board.service.BoardCommandService;
import com.like.board.service.BoardQueryService;
import com.like.file.service.FileService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BoardServiceTest {

	private static final Logger log = LoggerFactory.getLogger(BoardServiceTest.class);
		 
	@Autowired
	BoardCommandService bcs;
	
	@Autowired
	BoardQueryService bqs;	
	
	@Autowired
	FileService fs;
	
	Long key;
	
	@Before 
    public void setUp() { 

    } 
	
	@Test	
	public void test01_게시판등록() {
		Board board = new Board("테스트 게시판"); 
		
		bcs.saveBoard(board);
		
		Board confirmBoard = bqs.getBoard(board.getPkBoard());
		
		assertEquals(confirmBoard, board);
		
		//assertThat(confirmBoard.getPkBoard(), is(board.getPkBoard()));			
	}
	
		
	/**
	 * 삭제 테스트 확인해봐야함
	 */
	@Test
	public void test02_게시판삭제() {		

		Board board = new Board("삭제 테스트 게시판");
		bcs.saveBoard(board);				
		
		bcs.deleteBoard(board);
		
		//board = null;
		
		//assertNull(board);			
	}
		
	
	/*@Testf
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
