package com.ttv.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttv.models.MovieShowTime;
import com.ttv.models.Tmdb;
import com.ttv.services.MovieShowTimeService;
import com.ttv.services.TmdbService;

@CrossOrigin(origins = "*", allowedHeaders="*")
@RestController
@RequestMapping("/movie")
public class MovieController {
	
	@Autowired
	TmdbService tmdbService;
	
	@Autowired
	MovieShowTimeService movieShowTimeService;
	
	@GetMapping("/all")
	public List<Tmdb> getAllMovies() {
		return tmdbService.findAll();
	}
	
	@GetMapping("/{id}")
	public Tmdb getMovieById(@PathVariable Long id) {
		return tmdbService.findById(id);
	}
	
	@PostMapping("")
	public Map<String, Boolean> insertMovie(@RequestBody MovieShowTime movieShowTime) {
		if(!tmdbService.exists(movieShowTime.getMovie())) {
			tmdbService.add(movieShowTime.getMovie());
		}
		if(!movieShowTimeService.exists(movieShowTime)) {
			System.out.println(movieShowTime);
			movieShowTimeService.add(movieShowTime);
			return Collections.singletonMap("success", true);
		}
		return Collections.singletonMap("success", false);
	}
	
	@DeleteMapping("/delete/{id}")
	public Map<String, Boolean> deleteMovieById(@PathVariable Long id) {
		tmdbService.deleteById(id);
		return Collections.singletonMap("Success", true);
	}
	

}
