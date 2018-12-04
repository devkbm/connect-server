package com.like.board.web;

import java.util.List;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.board.domain.model.Board;
import com.like.board.dto.BoardSaveDTO;
import com.like.board.service.BoardCommandService;
import com.like.board.service.BoardQueryService;
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

	@Resource
	private BoardCommandService boardCommandService;
	
	@Resource
	private BoardQueryService boardQueryService;
		
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);	
		
	@GetMapping("/grw/boards")
	public ResponseEntity<?> getBoardList() {			
		
		List<Board> list = boardQueryService.getBoardList(); 										
							
		return WebControllerUtil.getResponse(list,				
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK);
	}
		
	@GetMapping("/grw/boards/{id}")
	public ResponseEntity<?> getBoard(@PathVariable(value="id") Long id) {				
				
		Board board = boardQueryService.getBoard(id);							
					
		return WebControllerUtil.getResponse(board,				
				board != null ? 1 : 0, 
				true, 
				String.format("%d 건 조회되었습니다.", board != null ? 1 : 0), 
				HttpStatus.OK);
	}
	
	@GetMapping("/grw/boardHierarchy")
	public ResponseEntity<?> getBoardHierarchyList() {
											
		List<?> list = boardQueryService.getBoardHierarchy();				 			
		
		return WebControllerUtil.getResponse(list,
				list.size(), 
				true,
				String.format("%d 건 조회되었습니다.", list.size()),
				HttpStatus.OK);
	}
		
	@RequestMapping(value={"/grw/boards/{id}"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveBoard(@PathVariable(value="id",required=false) Long id, @RequestBody BoardSaveDTO boardDTO, BindingResult result) {
							
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		log.info(authentication.getPrincipal().toString());
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 
		
		Board board = boardQueryService.getBoard(id);			
		
		if ( board == null )
			board = new Board();
		
		board.updateEntity(boardDTO);
		
		boardCommandService.saveBoard(board);																					
								 					
		return WebControllerUtil.getResponse(board,
				board != null ? 1 : 0, 
				true, 
				String.format("%d 건 저장되었습니다.", board != null ? 1 : 0), 
				HttpStatus.OK);
	}	
		
	@DeleteMapping("/grw/boards/{id}")
	public ResponseEntity<?> delBoard(@PathVariable(value="id") Long id) {					
												
		boardCommandService.deleteBoard(id);							
		
		return WebControllerUtil.getResponse(null, 
				1, 
				true, 
				String.format("%d 건 삭제되었습니다.", 1), 
				HttpStatus.OK);
	}		
			
}