package com.ttv.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttv.daos.ReviewDao;
import com.ttv.models.Review;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewDao reviewDao;
	
	public Review add(Review review) {
		return reviewDao.add(review);
	}

	public List<Review> findAll() {
		return reviewDao.findAll();
	}
	
	public List<Review> findAllByMovieId(String movie_id) {
		List<Review> reviews = new ArrayList<>();
		//loop and find the movies with matching movieApiId
		for(Review review : reviewDao.findAll()) {
			if(review.getMovie().getMovieApiId().equals(movie_id)) {
				reviews.add(review);
			}
		}
		return reviews;
	}
	
	public Review findById(Long id) {
		return reviewDao.findById(id);
	}

	public void update(Review review) {
		reviewDao.update(review);
	}

	public void deleteById(Long id) {
		reviewDao.deleteById(id);
	}
}
