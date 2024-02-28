package com.example.demo.Servis;

import com.example.demo.IServis.IUser;
import com.example.demo.model.User;
import com.example.demo.model.task;
import com.example.demo.repo.UserRepository;
import com.example.demo.repo.taskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUser {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private taskRepository Task;
    public Iterable<User> getAll() {
        return userRepository.findAll();
    }


    public String Create(User user) {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());

        if (optionalUser.isPresent()) {
            return "no";
        } else {
            userRepository.save(user);
            return "yes";
        }
    }
    public Integer authorization(User user) {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if (!optionalUser.isPresent()) {
            return (0);
        } else {
            User existingUser = optionalUser.get();
            if (existingUser.getPassword().equals(user.getPassword())) {
                return existingUser.getId();
            } else {
                return 0;
            }
        }
    }


    public void purpose( int task_id, int performer_id) {
        Optional<task> taskById = Task.findById(task_id);
        task tasks= taskById.get();
        tasks.setPerformer(performer_id);
    }


    public User getUser(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);


        return userOptional.orElse(null);
    }
}

