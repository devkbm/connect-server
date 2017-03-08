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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.like.board.domain.model.Board;
import com.like.board.domain.model.Article;
import com.like.board.service.BoardService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class JpaTest {

	private static final Logger log = LoggerFactory.getLogger(JpaTest.class);
	
	@Autowired
	BoardService bs;
	 
	@Test
	public void contextLoads() {		
		System.out.println(bs.getBoardList());			
	}
	
	@Test
	public void getBoardTree() throws Exception {
		Map<String, Object> map = new HashMap();
		List list = null;
		map.put("ppkboard","root");
		//bs.getBoardListByTree(map);
		list = bs.getBoardByTree();		
		log.info(list.toString());
	}
	
	@Test
	public void insertBoard() throws Exception {
		Board board = new Board("Test");
		List<Article> articles = new ArrayList<Article>();
		Article article = new Article();
		
		articles.add(article);
		board.setArticles(articles);
		bs.saveBoard(board);							
	}
}
