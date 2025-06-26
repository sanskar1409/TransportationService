package com.onecognizant.service;

import java.util.Set;

import com.onecognizant.dto.TransportServiceDTO;
import com.onecognizant.entity.TransportServices;
import com.onecognizant.mapper.TransportServiceMapper;

public interface TransportServicesService {
	void addNewService(TransportServiceDTO dto);

	Set<TransportServices> getAllTransportServicesWhichContain(String pickupLocation);

	TransportServices findById(int id);

}