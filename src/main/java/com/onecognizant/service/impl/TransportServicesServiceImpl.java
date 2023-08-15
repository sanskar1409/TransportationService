package com.onecognizant.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onecognizant.entity.TransportServices;
import com.onecognizant.repository.TransportServicesRepository;
import com.onecognizant.service.TransportServicesService;

@Service
public class TransportServicesServiceImpl implements TransportServicesService {

	@Autowired
	private TransportServicesRepository transportServicesRepository;

	@Override
	public void addNewService(TransportServices transportServices) {

		transportServicesRepository.save(transportServices);

	}

	@Override
	public Set<TransportServices> getAllTransportServicesWhichContain(String pickupLocation) {

		return transportServicesRepository.findByOnRoutePickupPointsContainsAllIgnoreCase(pickupLocation);
	}

	@Override
	public TransportServices findById(int id) {

		return transportServicesRepository.findById(id).get();
	}

}