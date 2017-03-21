package com.like.file.infra.jparepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.file.domain.model.FileInfo;

@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
	List<FileInfo> findByPgmIdAndFk(String pgmId, String fk);
}
