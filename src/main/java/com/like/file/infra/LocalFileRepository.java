package com.like.file.infra;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

public class LocalFileRepository {

	private String path;
	
	private int BUFFER_SIZE = 4096;
	
	public LocalFileRepository() {		
	}
	
	public String getPath() {
		return "C:\\temp";
	}
	public void fileTransfer(MultipartFile sourceFile, String path, String fileName) throws Exception {
						
		if(sourceFile == null || sourceFile.isEmpty()){			
			throw new FileNotFoundException();
		}
					
		try (InputStream is = sourceFile.getInputStream();
			 ReadableByteChannel  cin = Channels.newChannel(is);	
			 FileOutputStream fos = new FileOutputStream(new File(path, fileName));
			 FileChannel cout = fos.getChannel();) {			
					
			 cout.transferFrom(cin, 0, is.available());						 				
		}							
	}
	
	public void fileToStream(File file, OutputStream os) throws Exception {
			
		try (
			FileInputStream fis = new FileInputStream(file);
			FileChannel inChannel = fis.getChannel();
			WritableByteChannel outChannel = Channels.newChannel(os);) {							
			
			//response = this.setResponse(response, downloadFile, name);
			
			inChannel.transferTo(0, fis.available(), outChannel);			
		}
		
		//return response;		
	}
	
	public String fileToBase64String(String path, String fileName) throws Exception {
		
		byte[] buffer;
		byte[] byteArray;
		int bytesRead = -1;
		
		try (InputStream is = new FileInputStream(new File(path, fileName));
			 BufferedInputStream bis = new BufferedInputStream(is);
			 ByteArrayOutputStream bos = new ByteArrayOutputStream();) {
						
			buffer = new byte[this.BUFFER_SIZE];		
			while ((bytesRead = is.read(buffer)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
			byteArray = bos.toByteArray();					
		} 
		
		return Base64.encodeBase64String(byteArray);		
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
