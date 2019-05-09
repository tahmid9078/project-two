package com.ttv.daos;

import java.util.List;

import com.ttv.models.Tmdb;

public interface TmdbDao {
	public Tmdb add(Tmdb tmdb);
	public List<Tmdb> findAll();
	public Tmdb findById(Long id);
	public Boolean update(Tmdb tmdb);
	public Boolean deleteById(Long id);
}
