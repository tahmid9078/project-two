package com.ttv.services;

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
	private MovieShowTimeService movieShowTimeService;
	
	public Ticket add(Ticket ticket) {
		ticket.setAccount(accountService.findById(ticket.getAccount().getId()));
//		ticket.setMovieShowTime(movieShowTimeService.findById(id));
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
}
