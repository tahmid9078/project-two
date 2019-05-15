package com.ttv.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttv.daos.TicketTypeDao;
import com.ttv.models.TicketType;

@Service
public class TicketTypeService {
	
	@Autowired
	private TicketTypeDao ticketTypeDao;
	
	public TicketType add(TicketType ticketType) {
		return ticketTypeDao.add(ticketType);
	}

	public List<TicketType> findAll() {
		return ticketTypeDao.findAll();
	}

	public TicketType findById(Long id) {
		return ticketTypeDao.findById(id);
	}
	public TicketType findByName(String ticketName) {
		List<TicketType> ticketTypes = ticketTypeDao.findAll();
		//loop to find the ticketName that matches one in the DB
		for(TicketType ticketType : ticketTypes) {
			if(ticketType.getType().equals(ticketName)) {
				return ticketType;
			}
		}
		return null;
	}

	public void update(TicketType ticketType) {
		ticketTypeDao.update(ticketType);
	}

	public void deleteById(Long id) {
		ticketTypeDao.deleteById(id);
	}
	
}
