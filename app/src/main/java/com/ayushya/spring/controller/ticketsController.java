package com.ayushya.spring.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ayushya.spring.bean.technician;
import com.ayushya.spring.bean.tickets;
import com.ayushya.spring.repository.technicianRepository;
import com.ayushya.spring.repository.ticketsRepository;
import com.ayushya.spring.service.AssignService;
import com.ayushya.spring.service.NextSequenceService;
import com.ayushya.spring.service.TicketService;
import com.ayushya.spring.service.closedTicketService;

@RestController
@RequestMapping("/tickets")
public class ticketsController {
	
	private static List<technician> SE = new ArrayList<technician>();
	@Autowired
	private ticketsRepository repository;

	@Autowired
	private technicianRepository technicianRepository;
	
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
	
	@RequestMapping(value = "/closed", method = RequestMethod.GET)
	public List<tickets> getClosedTickets(){
		return new closedTicketService().getClosedTickets(repository.findAll());
	}
	
	@RequestMapping(value = "/city/{city_filter}", method = RequestMethod.GET)
	public Iterable<tickets> getTicketsInCity(@PathVariable String city_filter,Pageable pageable){
		return repository.findAll(pageable);
	}
	@RequestMapping(value = "/visit_date/{visit_date}", method = RequestMethod.GET)
	public Iterable<tickets> getTicketsBasedOnVisitDate(@PathVariable String visit_date,Pageable pageable){
		return repository.findAll(pageable);
	}
	


	@RequestMapping(value = "/", method = RequestMethod.POST)
	public tickets createTicket(@Valid @RequestBody tickets tick) {
		tick.set_id(nextSequenceService.getNextSequence("customSequences",new SimpleDateFormat("ddMMyy").format(new Date())));
		tick.setDate_of_post(new SimpleDateFormat("ddMMyyhhmmss").format(new Date()));
		List<technician> data = ticketService.getEmployeeData(SE);
		String id = new AssignService().assignTicket(data, tick.getCity());
		tick.setTech_name(id);
		technician tech = new technician();
		tech = technicianRepository.findOne(id);
		tech.setNo_assigned(technicianRepository.findOne(id).getNo_assigned() + 1);
		technicianRepository.save(tech);
		repository.save(tick);
		return tick;
	}
	
	@RequestMapping(value = "/{_id}", method = RequestMethod.PUT)
	public tickets updateTicket(@PathVariable String _id, @Valid @RequestBody tickets ticket) {
		ticket.set_id(_id);
		return repository.save(ticket);
	}




}
