package com.like.common.file.infra.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.like.common.file.infra.mapper.dto.FileListDTO;

@Mapper
public interface FileMapper {
				
	/**
	 * 게시글 리스트 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<FileListDTO> getFileList(Map<String, Object> map) throws Exception;
	
	
}
