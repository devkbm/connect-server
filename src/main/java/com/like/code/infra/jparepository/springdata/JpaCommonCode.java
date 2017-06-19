package com.like.code.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.code.domain.model.CommonCode;
import com.like.code.domain.model.id.CommonCodeId;

@Repository
public interface JpaCommonCode extends JpaRepository<CommonCode, CommonCodeId> {

}
