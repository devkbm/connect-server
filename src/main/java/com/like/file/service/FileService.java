package com.like.file.service;

import java.io.File;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.like.common.file.infra.FileRepository;
import com.like.common.file.infra.mapper.FileMapper;
import com.like.file.domain.model.FileInfo;
import com.like.file.infra.LocalFileRepository;
import com.like.file.infra.jparepository.FileInfoJpaRepository;
import com.like.file.infra.jparepository.springdata.JpaFileInfo;

@Service
public class FileService {
		
	@Resource(name="fileInfoJpaRepository")
	private FileInfoJpaRepository fileInfoRepository;	
	
	@Resource(name="localFileRepository")
	private LocalFileRepository localFileRepository;
	
	@Transactional
	public FileInfo uploadFile(MultipartFile sourceFile, String userId, String pgmId) throws Exception {
				
		String uuid = UUID.randomUUID().toString();
		
		localFileRepository.fileTransfer(sourceFile, localFileRepository.getPath(), uuid);
																
		FileInfo file = new FileInfo();			
		file.setUuid(uuid);		
		file.setPath(localFileRepository.getPath());
		file.setFileNm(sourceFile.getOriginalFilename());		
		file.setSize(sourceFile.getSize());
		file.setContentType(sourceFile.getContentType());		
		file.setDownloadCnt(0);
		file.setUserId(userId);
		file.setPgmId(pgmId);		
												
		return fileInfoRepository.saveFile(file);		
	}
		
		
	public FileInfo downloadFile(HttpServletResponse response, Long pk)
			throws Exception {
		
		FileInfo file = getFileInfo(pk);
		
		localFileRepository.fileToStream(new File(file.getPath(), file.getUuid()), response.getOutputStream());
		
		return file;
	}
	
	@Transactional	
	public void downloadFile(FileInfo fileInfo, OutputStream os)
			throws Exception {		
		
		localFileRepository.fileToStream(new File(fileInfo.getPath(), fileInfo.getUuid()), os);
		
		// 다운로드 카운트 + 1
	}
	
		
	public FileInfo getFileInfo(Long id) {
		return fileInfoRepository.getFileInfo(id);
	}
	
	@Transactional
	public void deleteFile(FileInfo fileInfo) throws Exception {
				
		rtn = fileInfoRepository.deleteFile(fileInfo.getPkFile());
		
		localFileRepository.delete(fileInfo);								
	}
	
	public String downloadBase64(Long id) throws Exception {
		FileInfo info = fileInfoRepository.getFileInfo(id);
					
		//return repository.getFileToBase64String(info.getPath(), info.getUuid());
		return null;
	}
	
}
