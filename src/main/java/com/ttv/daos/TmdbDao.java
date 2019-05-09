package com.ttv.daos;

import java.util.List;

import com.ttv.models.Tmdb;

public interface TmdbDao {
	public Tmdb add(Tmdb tmdb);
	public List<Tmdb> findAll();
	public Tmdb findById(Long id);
	public void update(Tmdb tmdb);
	public void deleteById(Long id);
}
