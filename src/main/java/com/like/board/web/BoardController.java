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
import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;

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
	
	/*@Autowired 
	RestTemplate restTemplate;*/	
	
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	private boolean validId(Long id) {				
		return ( id != null && id > 0 ) ? true : false;
	}
	
	//private Long
	
	
	@RequestMapping(value={"/grw/boards"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getBoard(@RequestParam(value="id", required=false) Long id) {
			
		ResponseEntity<?> result = null;
				
		List<Board> list; 
		
		if ( validId(id) ) {
			list = new ArrayList<Board>();
			list.add(boardService.getBoard(id));			
		} else {
			list = boardService.getBoardList();
		}			
			
		result = WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK); 					
		
		return result;
	}	
	
	@RequestMapping(value={"/grw/boardHierarchy"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getBoardHierarchyList(@RequestParam(value="parentId", required=false) String parentId ) {
			
		ResponseEntity<?> result = null;			
		Long id;
		
		if ("root".equals(parentId) || parentId == null) {		
			id = null;
		} else {
			id = Long.parseLong(parentId);
		}
		
		List<?> list = boardService.getBoardHierarchy(id);
		
		result = WebControllerUtil.getResponse(list,
				list.size(), 
				true,
				String.format("%d 건 조회되었습니다.", list.size()),
				HttpStatus.OK); 					
		
		return result;
	}
		
	@RequestMapping(value={"/grw/boards"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveBoard(@RequestBody List<Board> boardList, BindingResult result) {
			
		ResponseEntity<?> res = null;
		
		if ( result.hasErrors()) {
			//throw new IllegalArgumentException();
			throw new ControllerException("오류");
		} else {
															
			for (Board board : boardList ) {
				boardService.saveBoard(board);
			}
				
			res = WebControllerUtil.getResponse(null,
					boardList.size(), 
					true, 
					String.format("%d 건 저장되었습니다.", 1), 
					HttpStatus.OK);
		}
								 					
		return res;
	}	
	
	@RequestMapping(value={"/grw/boards/{id}"}, method=RequestMethod.DELETE) 
	public ResponseEntity<?> delBoard(@PathVariable(value="id") Long id) {
			
		ResponseEntity<?> result = null;			
												
		boardService.deleteBoard(id);
						
		result = WebControllerUtil.getResponse(null, 
				1, 
				true, 
				String.format("%d 건 삭제되었습니다.", 1), 
				HttpStatus.OK); 					
		
		return result;
	}	
	
	@RequestMapping(value={"/grw/boards/articles"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getArticleList(@RequestParam(value="fkBoard", required=true) Long fkBoard,
			@RequestParam(value="id", required=false) Long id) {
			
		ResponseEntity<?> result = null;			
		
		List<Article> list;
		
		if ( validId(id) ) {
			list = new ArrayList<>(); 			
			list.add(boardService.getAritlce(id));
		} else {
			list = boardService.getAritlceList(fkBoard);
		}			
			
		result = WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK); 			
				
		return result;
	}
	
	@RequestMapping(value={"/grw/boards/articles"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveArticle(@RequestBody List<Article> articleList,
			@RequestParam(value="fkBoard", required=true) Long fkBoard) {
			
		ResponseEntity<?> result = null;			
								
		for (Article article : articleList ) {			
			boardService.saveArticle(article, fkBoard);
		}
		
		/*ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8090/file", 
																			request, 
																			responseType, 
																			uriVariables);*/
						
		result = WebControllerUtil.getResponse(null, 
				articleList.size(), 
				true, 
				String.format("%d 건 저장되었습니다.", 1), 
				HttpStatus.OK); 					
		
		return result;
	}
	
	@RequestMapping(value={"/grw/boards/articles"}, method=RequestMethod.DELETE) 
	public ResponseEntity<?> delArticle(@RequestParam(value="id", required=true) Long id) {
			
		ResponseEntity<?> result = null;			
												
		boardService.deleteBoard(id);
						
		result = WebControllerUtil.getResponse(null, 
				1, 
				true, 
				String.format("%d 건 삭제되었습니다.", 1), 
				HttpStatus.OK); 					
		
		return result;
	}
	
	@RequestMapping(value={"/grw/boards/articles/hitcnt"}, method=RequestMethod.GET) 
	public ResponseEntity<?> updateArticleHitCnt(@RequestParam(value="id", required=true) Long id,
			@RequestParam(value="userid", required=true) String userId) {
			
		ResponseEntity<?> result = null;			
		List<Article> list = new ArrayList<>();
				
		Article aritlce = boardService.updateArticleHitCnt(id, userId);
		
		list.add(aritlce);
						
		result = WebControllerUtil.getResponse(list, 
				1, 
				true, 
				String.format("%d건 업데이트 하였습니다.", 1), 
				HttpStatus.OK); 					
		
		return result;
	}	
	
}