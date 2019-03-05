package com.like.commoncode.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.like.commoncode.domain.model.Code;

@Repository
public interface JpaCommonCode extends JpaRepository<Code, String>, QueryDslPredicateExecutor<Code> {

}
