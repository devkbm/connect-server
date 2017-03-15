package com.like.common.file.domain.repository;

import java.util.List;

import com.like.common.file.domain.model.FileInfo;

public interface FileRepository {
	
	FileInfo getFileInfo();
	
	List<FileInfo> getFileInfoList();
		
}
