package com.like.common.file.infra.jparepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.common.file.domain.FileInfo;

@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
	List<FileInfo> findByPgmIdAndFk(String pgmId, String fk);
}
