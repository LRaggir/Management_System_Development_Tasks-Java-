package com.example.demo.repo;
import com.example.demo.model.User;
import com.example.demo.model.task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface taskRepository extends CrudRepository<task,Integer>{
}
