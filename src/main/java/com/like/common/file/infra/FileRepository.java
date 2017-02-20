package com.like.common.file.infra;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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

//import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository("fileRepository")
public class FileRepository {

	private String path;
	
	private int BUFFER_SIZE = 4096;
			
	public String getPath() {
		//this.path = egovMessageSource.getMessage("Globals.fileStorePath");
		this.path = "C:/test";
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
				
	public String saveFile(MultipartFile sourceFile)	throws Exception {	
		//this.path = "C:/test";
		//this.path = egovMessageSource.getMessage("Globals.fileStorePath");		
		
		if(sourceFile == null || sourceFile.isEmpty()){
			return null;
		}
		String key = UUID.randomUUID().toString();
		sourceFile.transferTo(new File(path, key));
						
		return key;		
	}
	
	public String saveFileNio(MultipartFile sourceFile)	throws Exception {
	
		this.getPath();
		
		if(sourceFile == null || sourceFile.isEmpty()){
			return null;
		}
		
		String key = UUID.randomUUID().toString();
		
		try (InputStream is = sourceFile.getInputStream();
			 ReadableByteChannel  cin = Channels.newChannel(is);	
			 FileOutputStream fos = new FileOutputStream(new File(this.path, key));
			 FileChannel cout = fos.getChannel();) {
			
			 //ByteBuffer buf = ByteBuffer.allocateDirect(this.bufferLength);
					
			 cout.transferFrom(cin, 0, is.available());						 				
		}				
		
		return key;		
	}
	
	public HttpServletResponse downloadFile(HttpServletResponse response,
							String uuid,
							String path,
							String name) throws Exception {
		
		byte[] buffer = new byte[this.BUFFER_SIZE];
		int bytesRead = -1;
		File downloadFile = new File(path, uuid);
		
		try (
		     InputStream is = new FileInputStream(downloadFile);
			 BufferedInputStream bis = new BufferedInputStream(is); 
			 OutputStream outStream = response.getOutputStream();
			 BufferedOutputStream bos = new BufferedOutputStream(outStream);) {							
						
			response = this.setResponse(response, downloadFile, name);		
									
			// write bytes read from the input stream into the output stream
			while ((bytesRead = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}												
		}
		
		return response;
	}
	
	public HttpServletResponse downloadFileNio(HttpServletResponse response,
			String uuid,
			String path,
			String name) throws Exception {

		File downloadFile = new File(path, uuid);
		
		try (
			FileInputStream fis = new FileInputStream(downloadFile);
			FileChannel inChannel = fis.getChannel();
			WritableByteChannel outChannel = Channels.newChannel(response.getOutputStream());) {							
			
			response = this.setResponse(response, downloadFile, name);
			
			inChannel.transferTo(0, fis.available(), outChannel);			
		}
		
		return response;
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
		
	
	public Boolean deleteFile(String uuid) throws Exception {
		return FileUtil.deleteFile(path, uuid);
	}
	
	public String getFileToBase64String(String path, String uuid) throws Exception {			
		/*byte[] buffer;
		byte[] byteArray;
		int bytesRead = -1;
		
		try (InputStream is = new FileInputStream(new File(path, uuid));
			 BufferedInputStream bis = new BufferedInputStream(is);
			 ByteArrayOutputStream bos = new ByteArrayOutputStream();) {
						
			buffer = new byte[this.BUFFER_SIZE];		
			while ((bytesRead = is.read(buffer)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
			byteArray = bos.toByteArray();					
		} 
		
		return Base64.encodeBase64String(byteArray);*/
		return null;
	}
		
}







