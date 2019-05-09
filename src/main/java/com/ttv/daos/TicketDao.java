package com.ttv.daos;

import java.util.List;

import com.ttv.models.Ticket;

public interface TicketDao {
	public Ticket add(Ticket ticket);
	public List<Ticket> findAll();
	public Ticket findById(Long id);
	public void update(Ticket ticket);
	public void deleteById(Long id);
}
