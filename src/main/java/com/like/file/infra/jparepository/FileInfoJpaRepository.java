package com.like.file.infra.jparepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.file.domain.model.FileInfo;
import com.like.file.domain.repository.FileRepository;
import com.like.file.infra.jparepository.springdata.JpaFileInfo;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
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

	public void delete(Long id) {
		jpaFileInfo.delete(id);
	}
	
	public FileInfo save(FileInfo fileInfo) {
		return jpaFileInfo.saveAndFlush(fileInfo);		
	}
	
}
