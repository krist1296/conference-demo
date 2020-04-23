package com.springboot.conferencedemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.conferencedemo.models.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {

}
