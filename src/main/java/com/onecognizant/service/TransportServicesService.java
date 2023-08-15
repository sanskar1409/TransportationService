package com.onecognizant.service;

import java.util.Set;

import com.onecognizant.entity.TransportServices;

public interface TransportServicesService {
	void addNewService(TransportServices transportServices);

	Set<TransportServices> getAllTransportServicesWhichContain(String pickupLocation);

	TransportServices findById(int id);
}
