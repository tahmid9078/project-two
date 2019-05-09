package com.ttv.daos;

import java.util.List;

import com.ttv.models.Ticket;

public interface ticketDao {
	public Ticket add(Ticket ticket);
	public List<Ticket> findAll();
	public Ticket findById(Long id);
	public Boolean update(Ticket ticket);
	public Boolean deleteById(Long id);
}
