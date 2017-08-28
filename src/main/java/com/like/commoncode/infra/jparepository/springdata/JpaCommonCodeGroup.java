package com.like.commoncode.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.commoncode.domain.model.CodeGroup;

@Repository
public interface JpaCommonCodeGroup extends JpaRepository<CodeGroup, String> {

}
