package com.onecognizant.controller;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onecognizant.entity.TransportServices;
import com.onecognizant.service.TransportServicesService;

@Controller
@RequestMapping("/api/transports")
public class TransportsController {

	@Autowired
	private TransportServicesService transportServicesService;

	@PostMapping("/addNewService")
	ResponseEntity<String> addNewTransportService(@RequestBody TransportServices transportServices) throws Exception {

		TransportServices newService = new TransportServices();

		try {

			newService.setId(transportServices.getId());

			if (transportServices.getStartTime().isBefore(transportServices.getReturnTime())) {
				newService.setStartTime(transportServices.getStartTime());
				newService.setReturnTime(transportServices.getReturnTime());
			} else {
				throw new Exception("Return time must also be greater than start time....");
			}

			if (transportServices.getDriverPhoneNumber().length() == 10) {
				newService.setDriverPhoneNumber(transportServices.getDriverPhoneNumber());
			} else {
				throw new Exception("Please enter valid Phone number");
			}

			if (transportServices.getMaximumCapacity() > transportServices.getCurrentCapacity()) {
				newService.setCurrentCapacity(transportServices.getCurrentCapacity());
				newService.setMaximumCapacity(transportServices.getMaximumCapacity());
			} else {
				throw new Exception("Current capacity should not exceed maximum capacity...");
			}

			newService.setMonthlyFare(transportServices.getMonthlyFare());
			newService.setOnRoutePickupPoints(transportServices.getOnRoutePickupPoints());
			newService.setPickupLocation(transportServices.getPickupLocation());
			newService.setVehicleNo(transportServices.getVehicleNo());
			newService.setVehicleType(transportServices.getVehicleType());

			transportServicesService.addNewService(newService);

		} catch (Exception e) {
			throw new Exception(e.getLocalizedMessage());
		}

		return ResponseEntity.ok("New Service added successfully");
	}

	@GetMapping("/{pickupPoints}")
	ResponseEntity<Set<TransportServices>> getAllTransportServicesWhichContain(
			@PathVariable("pickupPoints") String pickupPoints) {

		Set<TransportServices> request = transportServicesService.getAllTransportServicesWhichContain(pickupPoints);

		return ResponseEntity.ok(request);

	}

}
