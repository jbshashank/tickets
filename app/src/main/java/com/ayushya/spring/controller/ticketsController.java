package com.ayushya.spring.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
import com.ayushya.spring.service.NextSequenceService;

@RestController
@RequestMapping("/tickets")
public class ticketsController {
	@Autowired
	private ticketsRepository repository;

	@Autowired
	private NextSequenceService nextSequenceService;
	
	@Autowired
	technicianRepository techRepo;
	
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
		getCachedData();
		tick.set_id(nextSequenceService.getNextSequence("customSequences",new SimpleDateFormat("ddMMyy").format(new Date())));
		tick.setDate_of_post(new SimpleDateFormat("ddMMyyhhmmss").format(new Date()));
		tick.setTech_name(null);
		repository.save(tick);
		return tick;
	}
	
	@Cacheable("technician")
	public Iterable<technician> getCachedData() {
		// TODO Auto-generated method stub
		try{
            System.out.println("Going to sleep for 5 Secs.. to simulate backend call.");
            Thread.sleep(1000*5);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
		System.out.println(techRepo.findAll().toString());
		return techRepo.findAll();
	}
	
	
}
