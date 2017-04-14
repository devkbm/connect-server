package com.like.dept.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.dept.domain.model.Dept;


@Repository
public interface DeptRepository {
	
	Dept getDept(String deptCode);
	
	List<Dept> getDeptList();
			
	void saveDept(Dept dept);
	
	void deleteDept(String deptCode);			
}