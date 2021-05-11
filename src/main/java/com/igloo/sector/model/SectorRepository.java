package com.igloo.sector.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SectorRepository extends JpaRepository<Sector, Integer> {
	
	

}
