package com.igloo.agent.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.igloo.agent.model.Agent;

import java.util.List;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Integer> {

    List<Agent> findByFirstNameContainingOrLastNameContaining(String searchTerm1,String searchTerm2);

    List<Agent> findAllByOrderByLastNameAsc();
    List<Agent> findAllByOrderByLastNameDesc();

    List<Agent> findAllByOrderByIdAsc();
    List<Agent> findAllByOrderByIdDesc();

}



