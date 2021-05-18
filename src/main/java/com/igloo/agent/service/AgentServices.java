package com.igloo.agent.service;

import java.util.List;

import com.igloo.agent.response.AgentAdapter;
import com.igloo.agent.response.AgentResponse;
import com.igloo.client.model.Client;
import com.igloo.client.response.ClientResponse;
import com.igloo.invoice.model.Invoice;
import com.igloo.invoice.response.InvoiceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.igloo.agent.model.Agent;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class AgentServices {

	@Autowired
	private AgentRepository agentRepository;

	@Autowired
	private AgentAdapter agentAdapter;

	public List<AgentResponse> get() {

		List<Agent> agents = agentRepository.findAll();
		return agentAdapter.of(agents);

	}

	public Agent create(String firstName, String lastName, String email, String profilePic) {

		Agent agent = new Agent();
		agent.setFirstName(firstName);
		agent.setLastName(lastName);
		agent.setEmail(email);
		agent.setProfilePic(profilePic);
		agentRepository.save(agent);
		return agent;
	}

	public void edit(Integer id, String firstName, String lastName, String email, String profilePic) {

		Agent agent = agentRepository.findById(id).get();

		agent.setFirstName(firstName);
		agent.setLastName(lastName);
		agent.setEmail(email);
		agent.setProfilePic(profilePic);
		agentRepository.save(agent);

	}

	public AgentResponse find(Integer id) {

		Agent agent = new Agent();

		agent = agentRepository.findById(id).get();
		agentRepository.save(agent);
		return agentAdapter.of(agent);
	}

	public List<AgentResponse> search(String action, String option, String term, Integer page) {

		List<Agent> agents = null;

		if (page == null) {
			page = 1;
		}

		if (action == null || action.isEmpty()) {
			Pageable pageable = PageRequest.of(page - 1, 8);
			Page<Agent> lista = agentRepository.findAll(pageable);
			agents = lista.getContent();

		} else if (action.equals("sort")) {
			Sort.Direction direction = Sort.Direction.fromString(option);
			Sort sort = Sort.by(direction, term);
			Pageable pageable = PageRequest.of(page - 1, 8, sort);
			agents = agentRepository.findAll(pageable).getContent();

		} else if (action.equals("search")) {
			if (option.equals("agent")) {
				Pageable pageable = PageRequest.of(page - 1, 8);
				agents = agentRepository.findByFirstNameContainingOrLastNameContaining(term, term, pageable);
			} else if (option.equals("favorite")) {

				Pageable pageable = PageRequest.of(page - 1, 8);
				agents = agentRepository.findByFavoriteIs(true, pageable);
			}

		}

		return agentAdapter.of(agents);
	}

	public Boolean delete(String id) {

		Boolean correct = false;
		try {
			int idToDelete = Integer.valueOf(id);
			agentRepository.deleteById(idToDelete);
			correct = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return correct;

	}

	public void addFavorite(Integer id) {

		Agent agent = agentRepository.findById(id).get();

		if (agent.getFavorite() == false || agent.getFavorite() == null) {

			agent.setFavorite(true);
		} else {

			agent.setFavorite(false);
		}

		agentRepository.save(agent);

	}

}
