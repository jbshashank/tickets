package com.ayushya.spring.service;

import java.util.List;

import com.ayushya.spring.bean.technician;

public class AssignService {
	
	private String assignedTech = null;
	int min = 100;
	
	public String assignTicket(List<technician> SE, String city) {
		for(int i = 0;i < SE.size();i++) {
			if(SE.get(i).getCity().equals("Ahmedabad") && SE.get(i).getNo_assigned() < min) {
				min = SE.get(i).getNo_assigned();
			}
		}
		for(int i = 0;i < SE.size();i++)
			if(SE.get(i).getCity().equals("Ahmedabad") && SE.get(i).getNo_assigned() == min) {
				assignedTech = SE.get(i).get_id();
				SE.get(i).setNo_assigned(SE.get(i).getNo_assigned()+1);
				System.out.println(SE.get(i).getNo_assigned());
				break;
			}
		return assignedTech;
	}
}
