package com.like.file.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import com.like.board.service.BoardCommandService;
import com.like.common.web.util.WebControllerUtil;
import com.like.file.domain.model.FileInfo;
import com.like.file.service.FileService;

@Controller
public class FileController {

	@Resource(name = "boardCommandService")
	private BoardCommandService boardCommandService;
	
	@Resource(name = "fileService")
	private FileService fileService;
	
	private static final Logger log = LoggerFactory.getLogger(FileController.class);
			
	@RequestMapping(value={"/file/{id}"}, method=RequestMethod.GET) 
	public HttpServletResponse fileDownLoad(HttpServletResponse response,
			@PathVariable(value="id") Long id) throws Exception {
								
		FileInfo fileInfo = fileService.getFileInfo(id);
		
		response = this.setResponse(response, fileInfo.getSize(), fileInfo.getFileNm());
		
		fileService.downloadFile(fileInfo, response.getOutputStream());		
		
		return response;
	}	
	
	@RequestMapping(value={"/file"}, method=RequestMethod.POST) 
	public ResponseEntity<?> fileUpload(final MultipartHttpServletRequest request,
			@RequestParam(value="pgmId", required=false) String pgmId ) throws Exception {
				
		ResponseEntity<?> result = null;		
		List<FileInfo> list = new ArrayList<FileInfo>();
		final Map<String, MultipartFile> files = request.getFileMap();
		Iterator<Entry<String,MultipartFile>> itr = files.entrySet().iterator();		
				
		while ( itr.hasNext() ) {			
			Entry<String,MultipartFile> entry = itr.next(); 			
			MultipartFile file = entry.getValue();					
			list.add(fileService.uploadFile(file, "kbm", pgmId));																
		}
		
		result = WebControllerUtil.getResponse(list,
				list.size(), 
				true,
				String.format("%d 건 저장되었습니다.", list.size()),
				HttpStatus.OK); 					
		
		return result;
	}
	
	@RequestMapping(value={"/file2"}, method=RequestMethod.POST) 
	public void handleFileUpload(@RequestParam("file") MultipartFile file) throws Exception {

		fileService.uploadFile(file, "kbm", "test");
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