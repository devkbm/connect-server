package com.like.board.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.board.domain.model.Article;
import com.like.board.domain.model.Board;
import com.like.board.service.BoardService;
import com.like.common.ExtjsReturnObject;
import com.like.common.exception.ControllerException;
import com.like.common.util.WebControllerUtil;

/**
 * 게시판 Rest 컨트롤러
 * 
 * @author 	bmkim
 * @date	2016.2.23
 */
@RestController
public class BoardController {

	@Resource(name = "boardService")
	private BoardService boardService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BoardController.class);
			
	@RequestMapping(value={"/grw/boards/{id}"}, method=RequestMethod.GET) 
	public ResponseEntity<ExtjsReturnObject> getBoard(@PathVariable(value="id") Long id) {
			
		ResponseEntity<ExtjsReturnObject> result = null;
				
		List<Board> list = new ArrayList<Board>(); 
			
		list.add(boardService.getBoard(id));
			
		result = WebControllerUtil.getResponse(list, list.size(), true, String.format("%d 건 조회되었습니다.", list.size()), HttpStatus.OK); 					
		
		return result;
	}
	
	@RequestMapping(value={"/grw/boards"}, method=RequestMethod.GET) 
	public ResponseEntity<ExtjsReturnObject> getBoardList() {
			
		ResponseEntity<ExtjsReturnObject> result = null;			
				
		List<Board> list = boardService.getBoardList();
		
		result = WebControllerUtil.getResponse(list, list.size(), true, String.format("%d 건 조회되었습니다.", list.size()), HttpStatus.OK); 					
		
		return result;
	}
		
	@RequestMapping(value={"/grw/boards"}, method=RequestMethod.POST) 
	public ResponseEntity<ExtjsReturnObject> saveBoard(@RequestBody List<Board> boardList, BindingResult result) {
			
		ResponseEntity<ExtjsReturnObject> res = null;
		LOGGER.info(result.toString());			
		if ( result.hasErrors()) {
			//throw new IllegalArgumentException();
			throw new ControllerException("오류");
		} else {
															
			for (Board board : boardList ) {
				boardService.saveBoard(board);
			}
			
			res = WebControllerUtil.getResponse(null, boardList.size(), true, String.format("%d 건 저장되었습니다.", 1), HttpStatus.OK);
		}
								 					
		return res;
	}
	
	@RequestMapping(value={"/grw/boards/{id}"}, method=RequestMethod.DELETE) 
	public ResponseEntity<ExtjsReturnObject> delBoard(@PathVariable(value="id") Long id) {
			
		ResponseEntity<ExtjsReturnObject> result = null;			
												
		boardService.deleteBoard(id);
						
		result = WebControllerUtil.getResponse(null, 1, true, String.format("%d 건 삭제되었습니다.", 1), HttpStatus.OK); 					
		
		return result;
	}
	
	
	@RequestMapping(value={"/grw/boards/articles"}, method=RequestMethod.GET) 
	public ResponseEntity<ExtjsReturnObject> getArticleList(@RequestParam(value="fkBoard", required=true) Long fkBoard) {
			
		ResponseEntity<ExtjsReturnObject> result = null;			
												
		List<Article> list = boardService.getAritlceList(fkBoard);
			
		result = WebControllerUtil.getResponse(list, list.size(), true, String.format("%d 건 조회되었습니다.", list.size()), HttpStatus.OK); 			
				
		return result;
	}
	
	@RequestMapping(value={"/grw/boards/articles"}, method=RequestMethod.POST) 
	public ResponseEntity<ExtjsReturnObject> saveArticle(@RequestBody List<Article> articleList,
			@RequestParam(value="fkBoard", required=true) Long fkBoard) {
			
		ResponseEntity<ExtjsReturnObject> result = null;			
								
		for (Article article : articleList ) {
			LOGGER.info(article.toString());
			boardService.saveArticle(article, fkBoard);
		}
						
		result = WebControllerUtil.getResponse(null, articleList.size(), true, String.format("%d 건 저장되었습니다.", 1), HttpStatus.OK); 					
		
		return result;
	}
	
	@RequestMapping(value={"/grw/boards/articles/{id}"}, method=RequestMethod.DELETE) 
	public ResponseEntity<ExtjsReturnObject> delArticle(@PathVariable(value="id") Long id) {
			
		ResponseEntity<ExtjsReturnObject> result = null;			
												
		boardService.deleteBoard(id);
						
		result = WebControllerUtil.getResponse(null, 1, true, String.format("%d 건 삭제되었습니다.", 1), HttpStatus.OK); 					
		
		return result;
	}
	
	@RequestMapping(value={"/grw/boards/articles/hitcnt"}, method=RequestMethod.PUT) 
	public ResponseEntity<ExtjsReturnObject> updateArticleHitCnt(@RequestParam(value="id", required=true) Long id,
			@RequestParam(value="userid", required=true) String userId) {
			
		ResponseEntity<ExtjsReturnObject> result = null;			
												
		boardService.updateArticleHitCnt(id, userId);
						
		result = WebControllerUtil.getResponse(null, 1, true, String.format("%d건 업데이트 하였습니다.", 1), HttpStatus.OK); 					
		
		return result;
	}
	
	
	
}