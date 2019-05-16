package com.ttv.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttv.daos.TmdbDao;
import com.ttv.models.Ticket;
import com.ttv.models.Tmdb;

@Service
public class TmdbService {
	
	@Autowired
	private TmdbDao tmdbDao;
	
	@Autowired
	private TicketService ticketService;
	
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
	public void deleteByApiId(String apiId) {
		Tmdb tmdb = findIdByApiId(apiId);
		if(tmdb != null) {
			//delete all ticket with movie api id
			List<Ticket> tickets = ticketService.findAll();
			for(Ticket ticket : tickets) {
				if(ticket.getMovie().getMovieApiId().equals(apiId)) {
					ticketService.deleteById(ticket.getId());
				}
			}
			//then remove movie 
			tmdbDao.deleteById(tmdb.getId());
		}
	}
	
	public Tmdb findIdByApiId(String apiId) {
		List<Tmdb> tmdbList = tmdbDao.findAll();
		//loop through each object in the arrayList to find if there are any matches
		for(Tmdb tmdbIndex : tmdbList) {
			//if there is a match then return the object
			if(tmdbIndex.getMovieApiId().equals(apiId)) {
				return tmdbIndex;
			}
		}
		return null;
	}
	
	public Boolean exists(Tmdb tmdb) {
		List<Tmdb> tmdbList = tmdbDao.findAll();
		//loop through each object and look for a match
		for(Tmdb tm : tmdbList) {
			//if there is a match then return true that it exists in the DB
			if(tm.getMovieApiId().equals(tmdb.getMovieApiId())) {
				return true;
			}
		}
		return false;
	}

}
