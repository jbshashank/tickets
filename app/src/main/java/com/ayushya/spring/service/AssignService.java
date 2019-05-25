package com.ayushya.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayushya.spring.bean.technician;
import com.ayushya.spring.repository.technicianRepository;

@Service
@Transactional
public class AssignService {
	
	private String assignedTech = null;
	int min = 100;
	
	@Autowired
	private technicianRepository repository;
	
	public String assignTicket(List<technician> SE, String city) {
		for(int i = 0;i < SE.size();i++) {
			if(SE.get(i).getCity().equalsIgnoreCase(city) && SE.get(i).getNo_assigned() < min) {
				min = SE.get(i).getNo_assigned();
				assignedTech = SE.get(i).get_id();
			}
		}
//		for(int i = 0;i < SE.size();i++)
//			if(SE.get(i).getCity().equals(city) && SE.get(i).getNo_assigned() == min) {
//				
//				break;
//			}
		return assignedTech;
	}
}
