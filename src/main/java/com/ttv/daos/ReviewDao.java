package com.ttv.daos;

import java.util.List;

import com.ttv.models.Review;

public interface ReviewDao {
	public Review add(Review review);
	public List<Review> findAll();
	public Review findById(Long id);
	public void update(Review review);
	public void deleteById(Long id);
}
