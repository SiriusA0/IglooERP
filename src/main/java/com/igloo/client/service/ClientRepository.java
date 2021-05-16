package com.igloo.client.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.igloo.client.model.Client;



@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findByFirstNameContainingOrLastNameContaining(String searchTerm1,String searchTerm2, Pageable pageable);

    List<Client> findAllByOrderByLastNameAsc();
    List<Client> findAllByOrderByLastNameDesc();

    List<Client> findAllByOrderByIdAsc();
    List<Client> findAllByOrderByIdDesc();

	
}
