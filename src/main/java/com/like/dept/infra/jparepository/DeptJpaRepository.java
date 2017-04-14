package com.like.dept.infra.jparepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.dept.domain.model.Dept;
import com.like.dept.domain.repository.DeptRepository;
import com.like.dept.infra.jparepository.springdata.JpaDept;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class DeptJpaRepository implements DeptRepository {
	
	@Autowired
	private JPAQueryFactory  queryFactory;
	
	@Autowired
	private JpaDept jpaDept;
	
	@Override
	public Dept getDept(String deptCode) {
		return jpaDept.findOne(deptCode);
	}

	@Override
	public List<Dept> getDeptList() {
		return jpaDept.findAll();
	}

	@Override
	public void saveDept(Dept dept) {
		jpaDept.save(dept);		
	}

	@Override
	public void deleteDept(String deptCode) {
		jpaDept.delete(deptCode);		
	}

}
