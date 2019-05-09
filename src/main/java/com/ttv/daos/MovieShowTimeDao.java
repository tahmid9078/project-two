package com.ttv.daos;

import java.util.List;

import com.ttv.models.MovieShowTime;

public interface MovieShowTimeDao {
	public MovieShowTime add(MovieShowTime movieShowTime);
	public List<MovieShowTime> findAll();
	public MovieShowTime findById(Long id);
	public void update(MovieShowTime movieShowTime);
	public void deleteById(Long id);
}
