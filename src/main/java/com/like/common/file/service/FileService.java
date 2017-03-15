package com.like.common.file.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.like.common.file.domain.model.FileInfo;
import com.like.common.file.infra.FileRepository;
import com.like.common.file.infra.jparepository.FileInfoRepository;
import com.like.common.file.infra.mapper.FileMapper;

@Service
public class FileService {
	
	@Resource(name="fileRepository")
	private FileRepository repository;
	
	@Resource(name="fileInfoRepository")
	private FileInfoRepository fileInfoRepository;
	
	@Resource(name="fileMapper")
	private FileMapper fileMapper;
	
	@Transactional
	public FileInfo uploadFile(MultipartFile multiPartFile, String userId, String pgmId, String fk) throws Exception {
			
		String key = repository.saveFile(multiPartFile);						
		
		if (key == null) {
			return null;
		}				
		
		FileInfo file = new FileInfo();
				
		file.setUuid(key);		
		file.setPath(repository.getPath());
		file.setFileNm(multiPartFile.getOriginalFilename());		
		file.setSize(multiPartFile.getSize());
		file.setContentType(multiPartFile.getContentType());		
		file.setDownloadCnt(0);
		file.setUserId(userId);
		file.setPgmId(pgmId);		
		file.setFk(fk);
													
		return fileInfoRepository.save(file);		
	}
	
	public void downloadFile(HttpServletResponse response,
				String uuid,
				String path,
				String name) throws Exception {
	
		repository.downloadFileNio(response,uuid,path,name);
	}	
		
	public void downloadFile(HttpServletResponse response, Long pk)
			throws Exception {
		
		FileInfo file = getFileInfo(pk);
		
		repository.downloadFileNio(response, file.getUuid(), file.getPath(), file.getFileNm());
		
	}
		
	public FileInfo getFileInfo(Long pkFile) {
		return fileInfoRepository.findOne(pkFile);
	}

	public List<FileInfo> getFileInfoList(String pgmId, String fk)
			throws Exception {		
		return fileInfoRepository.findByPgmIdAndFk(pgmId,fk);
		/*Map<String, Object> map = new HashMap<String, Object>();
		map.put("pgmId", pgmId);
		map.put("fk", fk);
		
		return fileMapper.getFileList(map);*/
	}
	
	@Transactional
	public boolean deleteFile(FileInfo fileInfo) throws Exception {
		boolean rtn = false;
		
		rtn = repository.deleteFile(fileInfo.getUuid());
		
		fileInfoRepository.delete(fileInfo);
							
		return rtn;
	}
	
	public String downloadBase64(Long pk) throws Exception {
		FileInfo info = fileInfoRepository.findOne(pk);
					
		//return repository.getFileToBase64String(info.getPath(), info.getUuid());
		return null;
	}
	
}
