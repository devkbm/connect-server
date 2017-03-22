package com.like.file.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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

import com.like.board.domain.model.Board;
import com.like.board.service.BoardService;
import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.file.domain.model.FileInfo;
import com.like.file.service.FileService;

@RestController
public class FileController {

	@Resource(name = "boardService")
	private BoardService boardService;
	
	@Resource(name = "fileService")
	private FileService fileService;
	
	private static final Logger log = LoggerFactory.getLogger(FileController.class);
			
	@RequestMapping(value={"/file/{id}"}, method=RequestMethod.GET) 
	public HttpServletResponse fileDownLoad(HttpServletResponse response,
			@PathVariable(value="id") Long id) throws Exception {
								
		FileInfo fileInfo = fileService.downloadFile(id, response.getOutputStream());
		
		response = this.setResponse(response, fileInfo.getSize(), fileInfo.getFileNm());
		
		return response;
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
		
		List list = boardService.getBoardHierarchy(id);
		
		result = WebControllerUtil.getResponse(list,
				list.size(), 
				true,
				String.format("%d 건 조회되었습니다.", list.size()),
				HttpStatus.OK); 					
		
		return result;
	}
		
		
	private HttpServletResponse setResponse(HttpServletResponse response, long fileSize, String fileName) throws Exception {
		
		// get MIME type of the file
		String mimeType= null;
		if (mimeType == null) {
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";	         
		}
		
		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLengthLong(fileSize);
		response.setCharacterEncoding("UTF-8");
		
		// set headers for the response
		String headerKey = "Content-Disposition";
		//String headerValue = String.format("attachment;filename=\"%s\"", new String(name.getBytes("EUC-KR"), "8859_1"));
		String headerValue = String.format("attachment;filename=\"%s\"", new String(fileName.getBytes("EUC-KR"), "8859_1"));
		
		response.setHeader(headerKey, headerValue);
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");	
		
		return response;
	}
			
}