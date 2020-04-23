package com.springboot.conferencedemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.conferencedemo.models.Speaker;

public interface SpeakerRepository extends JpaRepository<Speaker,Long> {

}
