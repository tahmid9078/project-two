package com.ttv.services;

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
