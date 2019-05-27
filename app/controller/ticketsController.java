package com.ayushya.spring.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ayushya.spring.bean.technician;
import com.ayushya.spring.bean.tickets;
import com.ayushya.spring.repository.technicianRepository;
import com.ayushya.spring.repository.ticketsRepository;
import com.ayushya.spring.service.AssignService;
import com.ayushya.spring.service.NextSequenceService;
import com.ayushya.spring.service.TicketService;

@RestController
@RequestMapping("/tickets")
public class ticketsController {
	
	private static List<technician> SE = new ArrayList<technician>();
	@Autowired
	private ticketsRepository repository;

	@Autowired
	private NextSequenceService nextSequenceService;

	@Autowired
	private TicketService ticketService;
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Iterable<tickets> getAllTickets(Pageable pageable){
		return repository.findAll(pageable);
	}

	@RequestMapping(value = "/{_id}", method = RequestMethod.GET)
	public tickets getOneTicket(@PathVariable String _id) {
		return repository.findOne(_id);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public tickets createTicket(@Valid @RequestBody tickets tick) {
		tick.set_id(nextSequenceService.getNextSequence("customSequences",new SimpleDateFormat("ddMMyy").format(new Date())));
		tick.setDate_of_post(new SimpleDateFormat("ddMMyyhhmmss").format(new Date()));
		ticketService.getEmployeeData(SE);
		tick.setTech_name(new AssignService().assignTicket(SE, tick.getCity()));
		repository.save(tick);
		return tick;
	}
	
	@RequestMapping(value = "/{_id}", method = RequestMethod.PUT)
	public tickets updateTicket(@PathVariable String _id, @Valid @RequestBody tickets ticket) {
		ticket.set_id(_id);
		return repository.save(ticket);
	}
	
	@RequestMapping(value = "/{_id}", method = RequestMethod.PUT)
	public tickets updateTicket(@PathVariable String _id, @Valid @RequestBody tickets ticket) {
		ticket.set_id(_id);
		return repository.save(ticket);
	}
	
	@RequestMapping(value = "/city/{city_filter}", method = RequestMethod.GET)
	public tickets updateTicket(@PathVariable String city_filter, @Valid @RequestBody tickets ticket) {
		return repository.findOne(city_filter);
	}



}
