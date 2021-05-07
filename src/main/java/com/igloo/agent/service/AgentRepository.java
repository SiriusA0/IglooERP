package com.igloo.agent.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.igloo.agent.model.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Integer> {

}
