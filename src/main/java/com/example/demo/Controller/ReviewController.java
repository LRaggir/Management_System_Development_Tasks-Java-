package com.example.demo.Controller;

import com.example.demo.IServis.IReview;
import com.example.demo.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {
    @Autowired
    private IReview iReview;

    @PostMapping("/createRe")
    public ResponseEntity<String> createReview(Review review) {
        try {
            iReview.PostReview(review);
            return ResponseEntity.ok("Review created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating review: " + e.getMessage());
        }
    }

    @GetMapping("/getRe")
    public ResponseEntity<?> getReview(Integer task_id) {
        try {
            Iterable<?> reviews = iReview.getAll(task_id);
            return ResponseEntity.ok(reviews);
        } catch (Exception e) {
           return ResponseEntity.badRequest().body("Error fetching Review: " + e.getMessage());
        }
    }
}
