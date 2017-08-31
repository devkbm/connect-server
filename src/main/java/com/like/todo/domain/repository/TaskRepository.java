package com.like.todo.domain.repository;

import java.util.List;

import com.like.todo.domain.model.Task;
import com.like.todo.domain.model.TaskGroup;

public interface TaskRepository {
	
	TaskGroup getTaskGroup(Long pkTaskGroup);
	
	List<TaskGroup> getTaskGroupList();
	
	List<TaskGroup> getTaskGroupList(String userId);
	
	void saveTaskGroup(TaskGroup taskGroup);
	
	void deleteTaskGroup(Long pkTaskGroup);
	
	Task getTask(Long pkTask);
	
	List<Task> getTaskList();
	
	void saveTask(Task task);
	
	void deleteTask(Long pkTask);
}
