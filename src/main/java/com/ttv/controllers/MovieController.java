package com.ttv.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttv.models.Tmdb;
import com.ttv.services.TmdbService;

@RestController
@RequestMapping("/movie")
public class MovieController {
	
	@Autowired
	TmdbService tmdbService;
	
	@PostMapping("") 
	public Map<String, Boolean> insertMovie(@RequestBody Tmdb movie) {
		if(!tmdbService.exists(movie)) {
			tmdbService.add(movie);
			return Collections.singletonMap("success", true);
		}
		return Collections.singletonMap("success", false);
	}
	
	@GetMapping("/all")
	public List<Tmdb> findAllTmdb() {
		return tmdbService.findAll();
	}
	
	@GetMapping("/{id}")
	public Tmdb getMovieById(@PathVariable long id) {
		return tmdbService.findById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteTmdb(@RequestBody Tmdb movie) {
		if(tmdbService.exists(movie)) {
			tmdbService.deleteById(movie.getId());
		}
	}
}
