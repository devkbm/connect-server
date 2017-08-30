package com.like.todo.infra.jparepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.todo.domain.model.Task;
import com.like.todo.domain.model.TaskGroup;
import com.like.todo.domain.repository.TaskRepository;
import com.like.todo.infra.jparepository.springdata.JpaTask;
import com.like.todo.infra.jparepository.springdata.JpaTaskGroup;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class TaskJpaRepository implements TaskRepository {

	@Autowired
	private JPAQueryFactory  queryFactory;
	
	@Autowired
	private JpaTaskGroup jpaTaskGroup;
	
	@Autowired
	private JpaTask jpaTask;
	
	@Override
	public TaskGroup getTaskGroup(Long pkTaskGroup) {		
		return jpaTaskGroup.findOne(pkTaskGroup);
	}

	@Override
	public List<TaskGroup> getTaskGroupList() {
		return jpaTaskGroup.findAll();
	}

	@Override
	public void saveTaskGroup(TaskGroup taskGroup) {
		jpaTaskGroup.save(taskGroup);
	}

	@Override
	public void deleteTaskGroup(Long pkTaskGroup) {
		jpaTaskGroup.delete(pkTaskGroup);
	}

	@Override
	public Task getTask(Long pkTask) {
		return jpaTask.findOne(pkTask);
	}

	@Override
	public List<Task> getTaskList() {
		return jpaTask.findAll();
	}

	@Override
	public void saveTask(Task task) {
		jpaTask.save(task);

	}

	@Override
	public void deleteTask(Long pkTask) {
		jpaTask.delete(pkTask);
	}

}
