package com.springboot.conferencedemo.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.conferencedemo.models.Session;
import com.springboot.conferencedemo.repositories.SessionRepository;

//first accepts incoming and outcoming Json formats
//second the route path would look like this, requests sent to this controller
@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {
	
	@Autowired
	private SessionRepository sessionRepository;
	
	@GetMapping
	public List<Session> list(){
		return sessionRepository.findAll();
	}
	
	@GetMapping
	@RequestMapping("{id}")
	public Session get(@PathVariable Long id) {
		return sessionRepository.getOne(id);
	}
	
	@PostMapping
	public Session create(@RequestBody final Session session) {
		//saveAndFlush gets the entity and saves it to the DB
		return sessionRepository.saveAndFlush(session);
	}
	
	@RequestMapping(value= "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		//Also need to check for children records before deleting.
		sessionRepository.deleteById(id);
	}
	
	@RequestMapping(value= "{id}", method = RequestMethod.PUT)
	public Session update(@PathVariable Long id, @RequestBody Session session) {
		//because it is a PUT, we expect all attributes to be passed in
		//PATCH updates only what we need to update
		//TODO: Add validation that all attributes are passed in, otherwise return 400 bad payload
		Session existingSession=sessionRepository.getOne(id);
		BeanUtils.copyProperties(session, existingSession, "session_id"); //ignore the session_id 
		return sessionRepository.saveAndFlush(existingSession);
	}

}
