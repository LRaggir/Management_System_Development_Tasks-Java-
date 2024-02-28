package com.example.demo.IServis;

import com.example.demo.model.User;
import org.springframework.http.ResponseEntity;

public interface IUser {
     Iterable<User> getAll();
     String Create(User user);
     void purpose(int task_id,int performer_id);
     Integer authorization (User user);
    User getUser(int user_id);
}
