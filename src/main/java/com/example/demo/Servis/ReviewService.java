package com.example.demo.Servis;

import com.example.demo.IServis.IReview;
import com.example.demo.model.Review;
import com.example.demo.repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService implements IReview {
    @Autowired
    private ReviewRepository reviewRepository;
    public void PostReview(Review review) {
        reviewRepository.save(review);
    }

    public Iterable<Review> getAll(int task_id)
    {

        Iterable<Review> reviewIterable = reviewRepository.findAll();
        List<Review> reviews = new ArrayList<>();
        reviewIterable.forEach(reviews::add);
        if(reviews != null)
        {
            for(int i=0;i< reviews.size();i++) {
                if(reviews.get(i).task_id!=task_id) {

                    reviews.remove(i);
                    i=i-1;
                }
            }
        }
        return reviews;
    }
}
