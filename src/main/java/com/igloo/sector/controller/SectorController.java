package com.igloo.sector.controller;

import java.util.LinkedList;
import java.util.List;

import com.igloo.sector.service.SectorRepository;
import com.igloo.sector.response.SectorResponse;
import com.igloo.sector.service.SectorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SectorController {

	@Autowired
	SectorRepository sectorRepository;

	@Autowired
	SectorService sectorService;

	@GetMapping("api/sector/add")
	@ResponseBody
	public List<SectorResponse> add_API(@RequestParam(required = false) String id, @RequestParam String name) {

		if (id == null) {
			sectorService.create(name);
			
		} else {
			int idInt = Integer.valueOf(id);
			sectorService.edit(idInt, name);
		}

		return sectorService.search();
	}

	@GetMapping("/api/sector/get")
	@ResponseBody
	public List<SectorResponse> search_API() {

		return sectorService.search();
	}

	@GetMapping("/api/sector/delete")
	@ResponseBody
	public List<SectorResponse> delete_API(@RequestParam String id) {
		Boolean correct = sectorService.delete(id);
		List<SectorResponse> sectors;
		if (correct) {
			sectors = sectorService.search();
		} else {
			sectors = new LinkedList<SectorResponse>();
		}
		return sectors;
	}

}
