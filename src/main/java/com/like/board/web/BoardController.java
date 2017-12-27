package com.like.board.web;

import java.util.List;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
import com.like.board.domain.repository.dto.BoardRequestDTO;
import com.like.board.domain.repository.dto.BoardRequestDTO2;
import com.like.board.service.BoardCommandService;
import com.like.board.service.BoardQueryService;
import com.like.common.domain.DTOConverter;
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
		
		ResponseEntity<?> result = null;
		
		List<Board> list = boardQueryService.getBoardList(); 										
			
		result = WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK); 					
		
		return result;
	}
		
	@GetMapping("/grw/boards/{id}")
	public ResponseEntity<?> getBoard(@PathVariable(value="id") Long id) {
			
		ResponseEntity<?> result = null;
				
		Board board = boardQueryService.getBoard(id);			
				
		result = WebControllerUtil.getResponse(board, 
				board != null ? 1 : 0, 
				true, 
				String.format("%d 건 조회되었습니다.", board != null ? 1 : 0), 
				HttpStatus.OK); 					
		
		return result;
	}
	
	@GetMapping("/grw/boardHierarchy")
	public ResponseEntity<?> getBoardHierarchyList(@RequestParam(value="parentId", required=false) String parentId ) {
			
		ResponseEntity<?> result = null;			
		Long id;
		
		if ("root".equals(parentId) || parentId == null) {		
			id = null;
		} else {
			id = Long.parseLong(parentId);
		}
		
		List<?> list = boardQueryService.getBoardHierarchy(id);
		
		result = WebControllerUtil.getResponse(list,
				list.size(), 
				true,
				String.format("%d 건 조회되었습니다.", list.size()),
				HttpStatus.OK); 					
		
		return result;
	}
		
	@RequestMapping(value={"/grw/boards/{id}"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveBoard(@PathVariable(value="id",required=false) Long id, @RequestBody BoardRequestDTO2 boardDTO, BindingResult result) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
			
		ResponseEntity<?> res = null;
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} else {
			
			Board board = boardQueryService.getBoard(id);			
			
			log.info(boardDTO.toString());
			//log.info(board.toString());
			//board = DTOConverter.convertEntity(board, boardDTO);						
			board = DTOConverter.convertEntityByAnnotation(board, boardDTO);
			log.info(board.toString());
			boardCommandService.saveBoard(board);
																						
			res = WebControllerUtil.getResponse(board,
					board != null ? 1 : 0, 
					true, 
					String.format("%d 건 저장되었습니다.", board != null ? 1 : 0), 
					HttpStatus.OK);
		}
								 					
		return res;
	}	
		
	@DeleteMapping("/grw/boards/{id}")
	public ResponseEntity<?> delBoard(@PathVariable(value="id") Long id) {
			
		ResponseEntity<?> result = null;			
												
		boardCommandService.deleteBoard(id);
						
		result = WebControllerUtil.getResponse(null, 
				1, 
				true, 
				String.format("%d 건 삭제되었습니다.", 1), 
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
				boardCommandService.saveBoard(board);
			}
				
			res = WebControllerUtil.getResponse(null,
					boardList.size(), 
					true, 
					String.format("%d 건 저장되었습니다.", 1), 
					HttpStatus.OK);
		}
								 					
		return res;
	}
		
}