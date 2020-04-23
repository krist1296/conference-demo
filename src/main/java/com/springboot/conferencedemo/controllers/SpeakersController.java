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

import com.springboot.conferencedemo.repositories.SpeakerRepository;
import com.springboot.conferencedemo.models.Speaker;

@RestController
@RequestMapping("api/v1/speakers")
public class SpeakersController {

	@Autowired
	private SpeakerRepository speakerRepository;

	@GetMapping
	public List<Speaker> list() {
		return speakerRepository.findAll();
	}

	@GetMapping
	@RequestMapping("{id}")
	public Speaker get(@PathVariable Long id) {
		return speakerRepository.getOne(id);

	}

	@PostMapping
	public Speaker create(@RequestBody final Speaker speaker) {
		return speakerRepository.saveAndFlush(speaker);
	}

	@RequestMapping(value =  "{id}" , method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		// Also need to check for children records before deleting.
		speakerRepository.deleteById(id);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker) {
		// because it is a PUT, we expect all attributes to be passed in
		// PATCH updates only what we need to update
		// TODO: Add validation that all attributes are passed in, otherwise return 400
		// bad payload
		Speaker existingSpeaker = speakerRepository.getOne(id);
		BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id"); // ignore the speaker_id
		return existingSpeaker;
	}
}
