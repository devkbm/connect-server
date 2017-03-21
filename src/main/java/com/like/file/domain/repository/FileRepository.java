package com.like.file.domain.repository;

import java.util.List;

import com.like.file.domain.model.FileInfo;

public interface FileRepository {
	
	FileInfo getFileInfo();
	
	List<FileInfo> getFileInfoList();
		
}
