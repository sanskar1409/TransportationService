package com.onecognizant.controller;

import java.util.Set;

import com.onecognizant.dto.TransportServiceDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.onecognizant.entity.TransportServices;
import com.onecognizant.service.TransportServicesService;

@RestController
@CrossOrigin
@RequestMapping("/api/transports")

public class TransportsController {

	@Autowired
	private TransportServicesService transportServicesService;

	@PostMapping("/addNewService")
	public ResponseEntity<String> addNewTransportService(@Valid @RequestBody TransportServiceDTO dto) {
		transportServicesService.addNewService(dto);
		return ResponseEntity.ok("New Service added successfully");
	}



	@GetMapping("/{pickupPoints}")
	ResponseEntity<Set<TransportServices>> getAllTransportServicesWhichContain(
			@PathVariable("pickupPoints") String pickupPoints) {

		Set<TransportServices> request = transportServicesService.getAllTransportServicesWhichContain(pickupPoints);

		return ResponseEntity.ok(request);

	}
	

}
