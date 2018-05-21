package com.like.board;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.like.board.domain.model.Article;
import com.like.board.domain.model.Board;
import com.like.board.service.BoardCommandService;
import com.like.board.service.BoardQueryService;
import com.like.file.domain.model.FileInfo;
import com.like.file.service.FileService;
import com.like.user.domain.model.User;
import com.like.user.service.UserService;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BoardServiceTest {

	private static final Logger log = LoggerFactory.getLogger(BoardServiceTest.class);
		 
	@MockBean
	private AuditorAware<String> auditorAware;
		
	@Autowired
	UserService userService;
	
	@Autowired
	BoardCommandService bcs;
	
	@Autowired
	BoardQueryService bqs;	
	
	@Autowired
	FileService fs;
	
	Long key;
	
	@Before 
    public void setUp() { 
		User user = userService.getUser("1");
		Mockito.when(auditorAware.getCurrentAuditor()).thenReturn(user.getUsername());
		
		Board board = new Board("테스트"); 		
		bcs.saveBoard(board);
    } 
	
	@Test	
	public void test01_게시판등록() {
		Board board = new Board("테스트 게시판"); 
		
		bcs.saveBoard(board);
		
		assertEquals(board.getBoardName(),"테스트 게시판");
		assertEquals(board.getFromDate(), LocalDate.now());
		assertEquals(board.getToDate(), LocalDate.of(9999, 12, 31));
		assertEquals(board.getUseYn(), true);			
	}
	
		
	/**
	 * 삭제 테스트 확인해봐야함
	 */
	@Test
	public void test02_게시판삭제() {		

		List<Board> boardList = bqs.getBoardList("테스트");
		
		List<Board> boardSearch = 
		boardList.stream()
				.filter(board-> board.getBoardName().equals("테스트"))
				.collect(Collectors.toList());
		
		assertEquals(boardSearch.size(), 1);
		
		for (Board board: boardSearch)
			bcs.deleteBoard(board);
					
		//Assertions.assertThat(boardSearch).isEmpty();;
	}
	
	@Test
	public void test03_게시글등록() {
		Board board = new Board("게시판");
		bcs.saveBoard(board);
		
		Article article = new Article(board, "제목", "내용");
		
		bcs.saveArticle(article, board.getPkBoard());
		
	}
	
	@Test
	public void test04_게시글파일저장() throws Exception {
		Board board = new Board("Test");
		
		bcs.saveBoard(board);
		
		Article article = new Article(board, "test","test");		
		
		MockMultipartFile file = new MockMultipartFile("user-file", "test.txt",
                                  "multipart/form-data", "test data".getBytes());
		
		FileInfo info = fs.uploadFile(file, "test","test");
		
		article.addAttachedFile(info);							
		
		bcs.saveArticle(article, board.getPkBoard());
		
		log.info("-------------------------------------");
		log.info(article.getPkArticle().toString());
		log.info(bqs.getAritlce(article.getPkArticle()).toString());
		log.info(bqs.getAritlce(article.getPkArticle()).getFiles().toString());
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
	
	
}
