package com.igloo.sector.service;

import java.util.List;

import com.igloo.sector.model.Sector;
import com.igloo.sector.response.SectorAdapter;
import com.igloo.sector.response.SectorResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectorService {

	@Autowired
	SectorRepository sectorRepository;

	@Autowired
	SectorAdapter sectorAdapter;

	public Sector create(String name) {

		Sector sector = new Sector();
		sector.setName(name);
		sectorRepository.save(sector);
		return sector;
	}

	public void edit(Integer id, String name) {

		Sector sector = sectorRepository.findById(id).get();
		sector.setName(name);
		sectorRepository.save(sector);
	}

	public List<SectorResponse> search() {

		List<Sector> sectors = sectorRepository.findAll();
		return sectorAdapter.of(sectors);
	}

	public Boolean delete(String id) {
		Boolean correct = false;
		try {
			int idToDelete = Integer.valueOf(id);
			sectorRepository.deleteById(idToDelete);
			correct = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return correct;
	}

}
