package com.onecognizant.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.onecognizant.dto.TransportSubscriptionDTO;
import com.onecognizant.mapper.TransportSubscriptionMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.onecognizant.entity.SubscriptionPayments;
import com.onecognizant.entity.TransportServices;
import com.onecognizant.entity.TransportSubscriptions;
import com.onecognizant.service.SubscriptionPaymentService;
import com.onecognizant.service.TransportServicesService;
import com.onecognizant.service.TransportSubscriptionsService;

@RestController
@CrossOrigin
@RequestMapping("/api/subscriptions")
public class SubscriptionsController {

	@Autowired
	private TransportSubscriptionsService transportSubscriptionsService;

	@Autowired
	private SubscriptionPaymentService subscriptionPaymentService;

	@Autowired
	private TransportServicesService transportServicesService;

	@PostMapping("/new")
	public ResponseEntity<String> serviceSubscribe(@Valid @RequestBody TransportSubscriptionDTO dto) {
		TransportSubscriptions subscription = new TransportSubscriptions();
		subscription.setSubscribedByEmployee(dto.getSubscribedByEmployee());
		subscription.setSubscriptionStartDate(dto.getSubscriptionStartDate());
		subscription.setSubscriptionEndDate(dto.getSubscriptionEndDate());
		// ... fetch transportService by ID and set it
		transportSubscriptionsService.addSubscription(dto);
		return ResponseEntity.ok("Successfully Subscribed to service...");
	}


	@GetMapping("/{id}")
	ResponseEntity<TransportSubscriptionDTO> findSubscriptionById(@PathVariable("id") int id) {
		TransportSubscriptionDTO request = transportSubscriptionsService.findSubscriptionById(id);
		if (request == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(request);
	}

	@DeleteMapping("/{id}/unsubscribe")
	public ResponseEntity<String> deleteSubscriptionById(@PathVariable("id") int id) {
		TransportSubscriptionDTO dto = transportSubscriptionsService.findSubscriptionById(id);
		if (dto == null) {
			return ResponseEntity.notFound().build();
		}
		TransportSubscriptions entity = TransportSubscriptionMapper.toEntity(
				dto,
				transportServicesService.findById(dto.getTransportServiceId())
		);
		SubscriptionPayments subscription = subscriptionPaymentService.getSubscriptionByTransportSubscription(entity);
		entity.setSubscriptionStatus("Cancelled");
		double returnAmount = subscription.getAmount();
		if (entity.getSubscriptionStartDate().isBefore(LocalDate.now())) {
			long daysBetween = ChronoUnit.DAYS.between(entity.getSubscriptionStartDate(), entity.getSubscriptionEndDate());
			double perDayFare = returnAmount / daysBetween;
			long remainingDays = ChronoUnit.DAYS.between(LocalDate.now(), entity.getSubscriptionEndDate());
			returnAmount = perDayFare * remainingDays;
		}
		TransportServices transportServices = transportServicesService.findById(entity.getTransportService().getId());
		transportServices.setCurrentCapacity(transportServices.getCurrentCapacity() - 1);
		subscriptionPaymentService.deleteSubscriptionPaymentById(subscription.getId());
		transportSubscriptionsService.deleteSubscription(id);
		return ResponseEntity.ok("Successfully Unsubscribed. Amount " + returnAmount + " will be returned in 7 business days.");
	}


}