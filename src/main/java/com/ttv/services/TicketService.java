package com.ttv.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttv.daos.TicketDao;
import com.ttv.models.Account;
import com.ttv.models.Ticket;
import com.ttv.models.TicketType;
import com.ttv.models.Tmdb;

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
		Account account = accountService.findById(ticket.getAccount().getId());
		TicketType ticketType = ticketTypeService.findByName(ticket.getTicketType().getType());
		Tmdb movie = tmdbService.findIdByApiId(ticket.getMovie().getMovieApiId());
		if(account != null && ticketType != null && movie != null) {
			ticket.setAccount(account);
			ticket.setTicketType(ticketType);
			ticket.setMovie(movie);	
			return ticketDao.add(ticket);
		}
		return null;
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
	
	public Boolean verifyTicketSubmittion(Ticket ticket) {
		//empty or null verification
		if(ticket.getMovie() == null 
				|| ticket.getMovieShowTime() == "" || ticket.getMovieShowTime() == null 
				|| ticket.getPaymentCardNumber() == "" || ticket.getPaymentCardNumber() == null 
				|| ticket.getTicketType() == null
				|| ticket.getAccount() == null) {
			return false;
		} else {
			//Paymentcard verification
			try {
				Long.parseLong(ticket.getPaymentCardNumber());
			} catch (NumberFormatException e) {
				return false;
			}
			//return true if everything passes
			return true;
		}
	}
	
}
