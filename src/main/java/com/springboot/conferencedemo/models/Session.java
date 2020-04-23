package com.springboot.conferencedemo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

//session because is one row of the data
@Entity(name="sessions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Session {
	//having the same name with the db fields
	//no need for annotation
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long session_id;
	
	private String session_name;
	private String session_description;
	private Integer session_length;
	
	//we have a joinTable in the DB with attributes
	//with a many to many relationship 
	//when speakers called, sql from jpa created
	@ManyToMany
	@JoinTable(
			name="session_speakers",
			joinColumns= @JoinColumn(name="session_id"),
			inverseJoinColumns=@JoinColumn(name="speaker_id"))
	private List<Speaker> speakers;
	
	public List<Speaker> getSpeakers() {
		return speakers;
	}
	public void setSpeakers(List<Speaker> speakers) {
		this.speakers = speakers;
	}
	public Session() {
		
	}
	public Long getSession_id() {
		return session_id;
	}
	public void setSession_id(Long session_id) {
		this.session_id = session_id;
	}
	public String getSession_name() {
		return session_name;
	}
	public void setSession_name(String session_name) {
		this.session_name = session_name;
	}
	public String getSession_description() {
		return session_description;
	}
	public void setSession_description(String session_description) {
		this.session_description = session_description;
	}
	public Integer getSession_length() {
		return session_length;
	}
	public void setSession_length(Integer session_length) {
		this.session_length = session_length;
	} 
	

}
