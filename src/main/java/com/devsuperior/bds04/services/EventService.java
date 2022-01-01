package com.devsuperior.bds04.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository repo;
	
	@Transactional(readOnly = true)   
	public Page<EventDTO> findAllPaged(Pageable pageable) {  // metodo para buscar todas as categorias
		Page<Event> list = repo.findAll(pageable);
		return list.map(x -> new EventDTO(x));
	}
	
	@Transactional
	public EventDTO insert(EventDTO dto) {
		Event e = new Event();
		e.setName(dto.getName());
		e.setDate(dto.getDate());
		e.setUrl(dto.getUrl());
		e.setCity(new City(dto.getCityId(), null));
		repo.save(e);
		return new EventDTO(e);
	}
}
