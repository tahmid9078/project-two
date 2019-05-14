package com.ttv.services;

import java.util.ArrayList;
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
		Tmdb t = tmdbService.findIdByApiId(movieShowTime.getMovie().getMovieApiId());
		movieShowTime.setMovie(t);
		return movieShowTimeDao.add(movieShowTime);
	}

	public List<MovieShowTime> findAll() {
		List<MovieShowTime> movieShowTimeList = movieShowTimeDao.findAll();

		return movieShowTimeList;
	}

	public MovieShowTime findById(Long id) {
		MovieShowTime movieShowTime = movieShowTimeDao.findById(id);
		movieShowTime.setMovie(tmdbService.findById(movieShowTime.getMovie().getId()));
		return movieShowTime;
	}

	public void update(MovieShowTime movieShowTime) {
		movieShowTimeDao.update(movieShowTime);
	}

	public void deleteById(Long id) {
		movieShowTimeDao.deleteById(id);
	}
	
	public Boolean exists(MovieShowTime movieShowTime) {
		List<MovieShowTime> movieShowTimeList = movieShowTimeDao.findAll();
		Tmdb movie = tmdbService.findIdByApiId(movieShowTime.getMovie().getMovieApiId());
		for(MovieShowTime mst : movieShowTimeList) {
			if(mst.getMovie().getMovieApiId().equals(movie.getMovieApiId()) && mst.getMovieTime().equals(movieShowTime.getMovieTime())) {
				return true;
			}
		}
		return false;
	}
	
	public List<MovieShowTime> getAllShowTimeByApiId(String id) {
		List<MovieShowTime> mstList = movieShowTimeDao.findAll();
		List<MovieShowTime> apiMstList = new ArrayList<>();
		for(MovieShowTime movieShowTime : mstList) {
			System.out.println(movieShowTime);
			System.out.println(movieShowTime.getMovie().getMovieApiId().equals(id));
			if(movieShowTime.getMovie().getMovieApiId().equals(id)) {
				apiMstList.add(movieShowTime);
			}
		}
		return apiMstList;
	}
	
}
