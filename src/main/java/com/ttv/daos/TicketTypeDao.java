package com.ttv.daos;

import java.util.List;

import com.ttv.models.TicketType;

public interface TicketTypeDao {
	public TicketType add(TicketType ticketType);
	public List<TicketType> findAll();
	public TicketType findById(Long id);
	public void update(TicketType ticketType);
	public void deleteById(Long id);
}
