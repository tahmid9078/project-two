package com.ttv.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttv.daos.MovieShowTimeDao;
import com.ttv.models.MovieShowTime;
import com.ttv.models.Tmdb;

@Service
public class MovieShowTimeService {
	
	@Autowired
	private MovieShowTimeDao movieShowTimeDao;
	@Autowired
	private TmdbService tmdbService;
	
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
	
	public Boolean exists(MovieShowTime movieShowTime) {
		List<MovieShowTime> mstList = movieShowTimeDao.findAll();
		Tmdb movie = tmdbService.findIdByApiId(movieShowTime.getMovie().getMovieApiId());
		if(movie != null) {
			for(MovieShowTime mst : mstList) {
				System.out.println(mst.getMovie().getId()+"   " + movieShowTime.getId() 
				+ mst.getMovie().getId().equals(movie.getId()));
				if(mst.getMovie().getId().equals(movie.getId()) 
						&& mst.getMovieTime().equals(movieShowTime.getMovieTime())) {
					return true;
				}
			}
		}
		return false;
		
	}
	
}
