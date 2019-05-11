package com.ttv.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttv.models.Review;
import com.ttv.services.ReviewService;
import com.ttv.services.TmdbService;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	ReviewService reviewService;
	
	@Autowired
	TmdbService tmdbService;
	
	@PostMapping("/{id}")
	public void getReviewsByMovieId(@PathVariable long movie_id) {
		List<Review> reviews = reviewService.findAllByMovieId(movie_id);
	}
	
	
	
}
