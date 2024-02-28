package com.example.demo.IServis;

import com.example.demo.model.Review;
import com.example.demo.model.task;

public interface IReview {
    public void PostReview(Review review);
    public Iterable<Review> getAll(int task_id);
}
