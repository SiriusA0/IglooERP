package com.igloo.agent.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.igloo.agent.model.Agent;
import com.igloo.client.model.Client;

import java.util.List;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Integer> {

	List<Agent> findByFirstNameContainingOrLastNameContaining(String searchTerm1, String searchTerm2,
			Pageable pageable);

	List<Agent> findByFavoriteIs(Boolean favorite, Pageable pageable);

}
