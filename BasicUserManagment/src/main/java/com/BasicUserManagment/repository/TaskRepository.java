package com.BasicUserManagment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BasicUserManagment.entity.Task;
import com.BasicUserManagment.entity.User;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
Optional<Task> findByTitle(String title);  
    
    List<Task> findByAssignedUser(User user);

}