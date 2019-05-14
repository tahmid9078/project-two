package com.ttv.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttv.models.Ticket;
import com.ttv.services.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired 
	TicketService ticketService;
	
	@PostMapping("")
	public Map<String, Boolean> insertTicket(@RequestBody Ticket ticket) {
		System.out.println(ticket);
		if(ticketService.add(ticket) != null) {
			return Collections.singletonMap("success", true);
		}
		return Collections.singletonMap("success", true);
	}
	
	@GetMapping("/all")
	public List<Ticket> getAllTickets() {
		return ticketService.findAll();
	}
}
