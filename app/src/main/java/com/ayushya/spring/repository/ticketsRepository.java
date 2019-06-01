package com.ayushya.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.ayushya.spring.bean.tickets;
import org.bson.types.ObjectId;


public interface ticketsRepository extends MongoRepository<tickets, String>{
	@Query("{'city': ?0 }")
	Page<tickets> findByCity(String city,Pageable pageable);
	@Query("{'visit_date': ?0 }")
	Page<tickets> findByVisit_date(String visit_date,Pageable pageable);
}



