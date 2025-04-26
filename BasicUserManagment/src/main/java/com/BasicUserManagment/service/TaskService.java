package com.BasicUserManagment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BasicUserManagment.dto.TaskDto;
import com.BasicUserManagment.entity.Task;
import com.BasicUserManagment.entity.User;
import com.BasicUserManagment.enums.Role;
import com.BasicUserManagment.exception.UserNotFound;
import com.BasicUserManagment.repository.TaskRepository;
import com.BasicUserManagment.repository.UserRepository;
@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private UserRepository userRepo;

	public List<TaskDto> getAllTaskById(Long uid) {
		User user=userRepo.findById(uid).orElseThrow(()-> new UserNotFound("User is not Registered"));
		List<Task> tasks=taskRepo.findByAssignedUser(user);
		return tasks.stream()
	            .map(this::convertToTaskDto)
	            .collect(Collectors.toList());
	}

	public List<TaskDto> getAllTask() {
		 List<Task> tasks = taskRepo.findAll();
		    return tasks.stream()
		            .map(this::convertToTaskDto)
		            .collect(Collectors.toList());
	}
	
	private TaskDto convertToTaskDto(Task task) {
       TaskDto newtask= new TaskDto();
       newtask.setId(task.getId());
       newtask.setTitle(task.getTitle());
       newtask.setDescription(task.getDescription());
       newtask.setDueDate(task.getDueDate());
       newtask.setUserid(task.getAssignedUser().getId());
       newtask.setCompleted(task.isCompleted());
       return newtask;
    }
 

	public TaskDto CreateTask(Long userId, TaskDto taskDto) {
		User user=userRepo.findById(userId).orElseThrow(()-> new UserNotFound("User is not Registered"));
		 if (user.getRole()!=Role.ROLE_USER) {
	            throw new UserNotFound("Tasks can only be assigned to users with USER role");
	        }
		Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setDueDate(taskDto.getDueDate());
        task.setAssignedUser(user);
        task.setCompleted(false);
        Task savetask=taskRepo.save(task);
	    return convertToTaskDto(savetask);
	}
	
	

	

}