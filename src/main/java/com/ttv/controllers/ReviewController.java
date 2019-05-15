package com.ttv.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttv.models.Review;
import com.ttv.services.ReviewService;
import com.ttv.services.TmdbService;


//==================== NOT IMPLEMENTED===================================

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	ReviewService reviewService;
	
	@Autowired
	TmdbService tmdbService;
	
	@GetMapping("/all")
	public List<Review> getAllReviews() {
		return reviewService.findAll();
	}
	@GetMapping("/{id}")
	public void getReviewsByMovieId(@PathVariable String movie_id) {
		List<Review> reviews = reviewService.findAllByMovieId(movie_id);
	}
	
	@PostMapping("")
	public Map<String, Boolean> insertReview(@RequestBody Review review) {
		if(reviewService.add(review) != null) {
			return Collections.singletonMap("success", true);
		}
		return Collections.singletonMap("success", false);
	}
	
	
	
}
