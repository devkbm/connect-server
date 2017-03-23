package com.like.file.infra.jparepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.like.file.domain.model.FileInfo;
import com.like.file.domain.repository.FileRepository;
import com.like.file.infra.jparepository.springdata.JpaFileInfo;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class FileInfoJpaRepository implements FileRepository {
	
	@Autowired
	private JPAQueryFactory  queryFactory;

	@Autowired
	private JpaFileInfo jpaFileInfo;
	
	@Override
	public FileInfo getFileInfo(Long id) {
		return jpaFileInfo.findOne(id);
	}

	@Override
	public List<FileInfo> getFileInfoList() {
		return jpaFileInfo.findAll();
	}

	public void deleteFile(Long id) {
		jpaFileInfo.delete(id);
	}
	
	public FileInfo saveFile(FileInfo fileInfo) {
		return jpaFileInfo.save(fileInfo);		
	}
	
}
