package com.onecognizant.service.impl;

import java.util.Set;

import com.onecognizant.dto.TransportServiceDTO;
import com.onecognizant.mapper.TransportServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onecognizant.entity.TransportServices;
import com.onecognizant.repository.TransportServicesRepository;
import com.onecognizant.service.TransportServicesService;

@Service
public class TransportServicesServiceImpl implements TransportServicesService {

	@Autowired
	private TransportServicesRepository transportRepository;

	@Override
	public void addNewService(TransportServiceDTO dto) {
		TransportServices entity = TransportServiceMapper.toEntity(dto);
		transportRepository.save(entity);
	}

	@Override
	public Set<TransportServices> getAllTransportServicesWhichContain(String pickupLocation) {
		return transportRepository.findByPickupLocationContainingIgnoreCase(pickupLocation);
	}

	@Override
	public TransportServices findById(int id) {
		return transportRepository.findById(id).orElse(null);
	}
}