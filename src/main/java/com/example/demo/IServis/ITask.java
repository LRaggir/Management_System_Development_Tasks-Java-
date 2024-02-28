package com.example.demo.IServis;

import com.example.demo.model.Review;
import com.example.demo.model.User;
import com.example.demo.model.task;

public interface ITask {
    public void Create(task Tas);
    public void Delete(int id);
    public void Edit(task Tas);
    public Iterable<task> getAll();
    public Iterable<task> getAll(int id);
    public Iterable<task> getAlltask(int user_id);
    public task gettask(int task_id);
    public void rejecttask(int task_id);
}