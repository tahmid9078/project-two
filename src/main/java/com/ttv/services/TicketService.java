package com.ttv.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttv.daos.TicketDao;
import com.ttv.models.Ticket;

@Service
public class TicketService {
	
	@Autowired
	private TicketDao ticketDao;
	@Autowired
	private AccountService accountService;
	@Autowired
	private TmdbService tmdbService;

	@Autowired
	private TicketTypeService ticketTypeService;
	
	public Ticket add(Ticket ticket) {
		//find and set the account, ticketType, and movie based on Id, Name, and ApiId
		ticket.setAccount(accountService.findById(ticket.getAccount().getId()));
		ticket.setTicketType(ticketTypeService.findByName(ticket.getTicketType().getType()));
		ticket.setMovie(tmdbService.findIdByApiId(ticket.getMovie().getMovieApiId()));
		
		return ticketDao.add(ticket);
	}

	public List<Ticket> findAll() {
		return ticketDao.findAll();
	}

	public Ticket findById(Long id) {
		return ticketDao.findById(id);
	}

	public void update(Ticket ticket) {
		ticketDao.update(ticket);
	}

	public void deleteById(Long id) {
		ticketDao.deleteById(id);
	}
	
	public List<Ticket> getAllTicketsByAccountId(Long id) {
		List<Ticket> tickets = ticketDao.findAll();
		List<Ticket> accTickets = new ArrayList<>();
		//loop through tickets to find tickets with an account 
		//	id that matches the id specified
		for(Ticket ticket : tickets) {
			if(ticket.getAccount().getId().equals(id)) {
				accTickets.add(ticket);
			}
		}
		return accTickets;
		
	}
}
