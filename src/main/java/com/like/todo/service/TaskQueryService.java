package com.like.todo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.todo.domain.model.Task;
import com.like.todo.domain.model.TaskGroup;
import com.like.todo.domain.repository.TaskRepository;

@Service
@Transactional(readOnly=true)
public class TaskQueryService {
	
	@Resource(name="articleJpaRepository")
	private TaskRepository taskRepository;
	
	
	public TaskGroup getTaskGroup(Long pkTaskGroup) {		
		return taskRepository.getTaskGroup(pkTaskGroup);
	}
	
	public List<TaskGroup> getTaskGroupList() {
		return taskRepository.getTaskGroupList();
	}
	
	public Task getTask(Long pkTask) {
		return taskRepository.getTask(pkTask);
	}
	
	public List<Task> getTaskList() {
		return taskRepository.getTaskList();
	}

}
