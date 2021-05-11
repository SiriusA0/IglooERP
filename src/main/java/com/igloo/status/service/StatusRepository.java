package com.igloo.status.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.igloo.status.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> { 

}
