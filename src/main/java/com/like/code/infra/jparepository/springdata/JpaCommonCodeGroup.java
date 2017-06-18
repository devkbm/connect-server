package com.like.code.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.code.domain.model.CommonCodeGroup;

@Repository
public interface JpaCommonCodeGroup extends JpaRepository<CommonCodeGroup, Long> {

}
