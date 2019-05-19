package com.ayushya.spring.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ayushya.spring.bean.tickets;
import com.ayushya.spring.repository.ticketsRepository;
import com.ayushya.spring.service.NextSequenceService;

@RestController
@RequestMapping("/tickets")
public class ticketsController {
	@Autowired
	private ticketsRepository repository;

	@Autowired
	private NextSequenceService nextSequenceService;
	
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
		tick.setTech_name(null);
		repository.save(tick);
		return tick;
	}
	
	
}
