package com.like.commoncode.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.commoncode.domain.model.Code;
import com.like.commoncode.domain.model.id.CommonCodeId;

@Repository
public interface JpaCommonCode extends JpaRepository<Code, CommonCodeId> {

}
