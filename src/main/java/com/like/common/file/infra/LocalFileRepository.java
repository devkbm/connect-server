package com.like.common.file.infra;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public class LocalFileRepository {

	private String path;	
	
	public String saveFileNio(MultipartFile sourceFile)	throws Exception {
		
		this.path = "C:/test";
		
		if(sourceFile == null || sourceFile.isEmpty()){
			return null;
		}
		
		String key = UUID.randomUUID().toString();
		
		try (InputStream is = sourceFile.getInputStream();
			 ReadableByteChannel  cin = Channels.newChannel(is);	
			 FileOutputStream fos = new FileOutputStream(new File(this.path, key));
			 FileChannel cout = fos.getChannel();) {			
					
			 cout.transferFrom(cin, 0, is.available());						 				
		}			
		
		return key;		
	}
	
	public void downloadFile(OutputStream os, File file) throws Exception {
			
		try (
			FileInputStream fis = new FileInputStream(file);
			FileChannel inChannel = fis.getChannel();
			WritableByteChannel outChannel = Channels.newChannel(os);) {							
			
			//response = this.setResponse(response, downloadFile, name);
			
			inChannel.transferTo(0, fis.available(), outChannel);			
		}
		
		//return response;		
	}
	
	public HttpServletResponse setResponse(HttpServletResponse response, File file, String fileName) throws Exception {
		
		// get MIME type of the file
		String mimeType= null;
		if (mimeType == null) {
		// set to binary type if MIME mapping not found
		mimeType = "application/octet-stream";	         
		}
		
		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLengthLong(file.length());
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
