package com.ttv.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttv.daos.TmdbDao;
import com.ttv.models.Tmdb;

@Service
public class TmdbService {
	
	@Autowired
	private TmdbDao tmdbDao;
	public Tmdb add(Tmdb tmdb) {
		return tmdbDao.add(tmdb);
	}

	public List<Tmdb> findAll() {
		return tmdbDao.findAll();
	}
	
	public Tmdb findById(Long id) {
		return tmdbDao.findById(id);
	}

	public void update(Tmdb tmdb) {
		tmdbDao.update(tmdb);
	}

	public void deleteById(Long id) {
		tmdbDao.deleteById(id);
	}
	
	public Tmdb findIdByApiId(String apiId) {
		List<Tmdb> tmdbList = tmdbDao.findAll();
		for(Tmdb tmdbIndex : tmdbList) {
			if(tmdbIndex.getMovieApiId().equals(apiId)) {
				return tmdbIndex;
			}
		}
		return null;
	}
	
	public Boolean exists(Tmdb tmdb) {
		List<Tmdb> tmdbList = tmdbDao.findAll();
		if(tmdbList != null) {
			for(Tmdb tmdbIndex : tmdbList) {
				System.out.println( tmdbIndex.getMovieApiId()+"  "+tmdb.getMovieApiId()+" == "+tmdbIndex.getMovieApiId().equals(tmdb.getMovieApiId()));
				if(tmdbIndex.getMovieApiId().equals(tmdb.getMovieApiId())) {
					return true;
				}
			}
		}
		return false;
	}

}
