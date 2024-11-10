package com.example.demo.Servis;

import com.example.demo.IServis.ITask;
import com.example.demo.model.Review;
import com.example.demo.model.User;
import com.example.demo.model.task;
import com.example.demo.repo.UserRepository;
import com.example.demo.repo.taskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class TaskService implements ITask {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private taskRepository TaskRepository;

    public void Create(task Tas) {
        Optional<User> optionalUser = userRepository.findById(Tas.getAuthor());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Tas.setAuthor(user.getId());
            TaskRepository.save(Tas);
        } else {
            throw new IllegalArgumentException("User with id " + Tas.getAuthor() + " not found");
        }
    }
    public void Delete(int id) {
        if (TaskRepository.existsById(id)) {
            TaskRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Task with id " + id + " not found");
        }
    }
    public void Edit(task Tas) {
        Optional<task> optionalTask = TaskRepository.findById(Tas.getId());
        if (optionalTask.isPresent()) {
            task tas = optionalTask.get();
            tas.setTitle(Tas.getTitle());
            tas.setDescription(Tas.getDescription());
            tas.setStatus(Tas.getStatus());
            tas.setPriority(Tas.getPriority());
            tas.setPerformer(Tas.getPerformer());
            TaskRepository.save(tas);
        } else {
            throw new IllegalArgumentException("Task with id " + Tas.getId() + " not found");
        }
    }
    public Iterable<task> getAll() {


        return TaskRepository.findAll();
    }
    public Iterable<task> getAll(int user_id) {

        ArrayList<task> tasks = new ArrayList();
        Iterable<task> allTasks = TaskRepository.findAll();


        for (task task : allTasks) {
            if (task.getAuthor() == user_id) {
                tasks.add(task);
            }
        }

        return (tasks);
    }
    public Iterable<task> getAlltask(int user_id) {

        ArrayList<task> tasks = new ArrayList();
        Iterable<task> allTasks = TaskRepository.findAll();


        for (task task : allTasks) {
            if (task.getPerformer() == user_id) {
                tasks.add(task);
            }
        }

        return (tasks);
    }
    public task gettask(int task_id)
    {
        Optional<task> taskOptional = TaskRepository.findById(task_id);
        return taskOptional.orElse(null);
    }


    public void rejecttask(int task_id)
    {
        Optional<task> taskOptional= TaskRepository.findById(task_id);
                if(taskOptional.isPresent()) {
                    task Tas = taskOptional.get();
                    Tas.setPerformer(0);
                    TaskRepository.save(Tas);
                }
    }
    }
