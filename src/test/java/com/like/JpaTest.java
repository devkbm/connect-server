package com.like;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.like.board.domain.model.Board;
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
	public void insertBoard() throws Exception {
		Board board = new Board("Test");
		
		bs.saveBoard(board);							
	}
}
