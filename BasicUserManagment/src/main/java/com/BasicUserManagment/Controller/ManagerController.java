package com.BasicUserManagment.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BasicUserManagment.dto.TaskDto;
import com.BasicUserManagment.dto.UserDto;
import com.BasicUserManagment.service.TaskService;
import com.BasicUserManagment.service.UserService;


@RestController
@RequestMapping("/manager")
@PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
public class ManagerController {

    private UserService userService;
    
    private TaskService taskService;

    public ManagerController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService=taskService;
    }

    @GetMapping("/getallusers")
    public ResponseEntity<List<UserDto>> getAllUser(@RequestParam (defaultValue="0")int page,@RequestParam (defaultValue="10")int size) {
        return ResponseEntity.ok(userService.getAllUser(page,size));
    }
    
    @PostMapping("/assigntask/userid/{userId}")
    public ResponseEntity<TaskDto> getUserById(@PathVariable Long userId, @RequestBody TaskDto task) {
        return ResponseEntity.ok(taskService.CreateTask(userId,task));
    }
    

}