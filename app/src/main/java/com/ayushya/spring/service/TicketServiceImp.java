package com.ayushya.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayushya.spring.bean.tickets;
import com.ayushya.spring.repository.ticketsRepository;



@Service
@Transactional
public class TicketServiceImp implements TicketService {
	@Autowired
	ticketsRepository ticketRepository;

	@Override
	public void createTicket(List<tickets> ticket) {
		// TODO Auto-generated method stub
		ticketRepository.save(ticket);
	}

}
