package com.like.board.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.board.domain.model.Board;
import com.like.board.service.BoardService;
import com.like.common.ExtjsReturnObject;
import com.like.common.util.WebControllerUtil;

@RestController
public class BoardController {

	@Resource(name = "boardService")
	private BoardService boardService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BoardController.class);
	
	@RequestMapping(value={"/grw/board/getBoard.do"}, method=RequestMethod.GET) 
	public Map<String, Object> getBoard(HttpServletRequest request,
			@RequestParam(value="pkBoard", required=true) Long pkBoard) throws Exception {
			
		Map<String, Object> retMap;				
		
		try {									
			List<Board> list = new ArrayList<Board>(); 
			
			list.add(boardService.getBoard(pkBoard));
			
			retMap = WebControllerUtil.setReturnMap(list, list.size(), true, String.format("%d 건 조회되었습니다.", list.size()));
		} catch (Exception ex) {							
			ex.printStackTrace();
			retMap = WebControllerUtil.setReturnMap(null, 0, false, ex.getMessage());		
		}
		LOGGER.info(retMap.get("recv").toString());
		return retMap;
	}
	
	@RequestMapping(value={"/grw/board/getBoard2.do"}, method=RequestMethod.GET) 
	public ResponseEntity<ExtjsReturnObject> getBoard2(HttpServletRequest request,
			@RequestParam(value="pkBoard", required=true) Long pkBoard) throws Exception {
			
		ResponseEntity<ExtjsReturnObject> result = null;			
		
		try {									
			List<Board> list = new ArrayList<Board>(); 
			
			list.add(boardService.getBoard(pkBoard));
			
			result = WebControllerUtil.getResponse(list, list.size(), true, String.format("%d 건 조회되었습니다.", list.size())); 			
		} catch (Exception ex) {	
			ex.printStackTrace();
			result = WebControllerUtil.getResponse(null, 0, false, "조회시 오류가 발생하였습니다.");		
		}
		
		return result;
	}
}
