package com.ttv.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttv.models.Ticket;
import com.ttv.services.TicketService;

@CrossOrigin(origins = "*", allowedHeaders="*")
@RestController
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired 
	TicketService ticketService;
	
	@PostMapping("")
	public Map<String, Boolean> insertTicket(@RequestBody Ticket ticket) {
		if(ticketService.verifyTicketSubmittion(ticket)) {
			Ticket returnedTicket = ticketService.add(ticket);
			if(returnedTicket != null) {
				return Collections.singletonMap("success", true);
			}
		}
		return Collections.singletonMap("success", false);
	}
	
	@GetMapping("/all")
	public List<Ticket> getAllTickets() {
		return ticketService.findAll();
	}
	
	@GetMapping("/account/{id}")
	public List<Ticket> getAllTicketByAccountId(@PathVariable Long id) {
		return ticketService.getAllTicketsByAccountId(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void insertTicket(@PathVariable Long id) {
		ticketService.deleteById(id);
	}
}
