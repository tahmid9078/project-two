package com.ttv.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttv.daos.MovieShowTimeDao;
import com.ttv.models.MovieShowTime;

@Service
public class MovieShowTimeService {
	
	@Autowired
	private MovieShowTimeDao movieShowTimeDao;
	
	public MovieShowTime add(MovieShowTime movieShowTime) {
		return movieShowTimeDao.add(movieShowTime);
	}

	public List<MovieShowTime> findAll() {
		return movieShowTimeDao.findAll();
	}

	public MovieShowTime findById(Long id) {
		return movieShowTimeDao.findById(id);
	}

	public void update(MovieShowTime movieShowTime) {
		movieShowTimeDao.update(movieShowTime);
	}

	public void deleteById(Long id) {
		movieShowTimeDao.deleteById(id);
	}
	
}
