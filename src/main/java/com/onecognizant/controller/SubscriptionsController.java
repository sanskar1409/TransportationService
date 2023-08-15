package com.onecognizant.controller;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onecognizant.entity.SubscriptionPayments;
import com.onecognizant.entity.TransportServices;
import com.onecognizant.entity.TransportSubscriptions;
import com.onecognizant.service.SubscriptionPaymentService;
import com.onecognizant.service.TransportServicesService;
import com.onecognizant.service.TransportSubscriptionsService;

@Controller
@CrossOrigin
@RequestMapping("/api/subscriptions")

public class SubscriptionsController {

	@Autowired
	private TransportSubscriptionsService transportSubscriptionsService;

	@Autowired
	private SubscriptionPaymentService subscriptionPaymentService;

	@Autowired
	private TransportServicesService transportServicesService;

//	Getting errors some validation are remaining 

	@PostMapping("/new")
	ResponseEntity<String> serviceSubscribe(@RequestBody TransportSubscriptions transportSubscriptions)
			throws Exception {

		try {

			TransportSubscriptions newSubscription = new TransportSubscriptions();

			newSubscription.setId(transportSubscriptions.getId());
			newSubscription.setSubscribedByEmployee(transportSubscriptions.getSubscribedByEmployee());

			newSubscription.setTransportService(transportSubscriptions.getTransportService());

			if (transportSubscriptions.getSubscriptionStartDate()
					.isBefore(transportSubscriptions.getSubscriptionEndDate())) {
				newSubscription.setSubscriptionStartDate(transportSubscriptions.getSubscriptionStartDate());
				newSubscription.setSubscriptionEndDate(transportSubscriptions.getSubscriptionEndDate());
			} else {
				throw new Exception("Subscription end date should not be less then subscription start date...");
			}

			if (transportSubscriptions.getSubscriptionEndDate().isAfter(LocalDate.now())) {
				newSubscription.setSubscriptionStatus("Active");
			} else {
				newSubscription.setSubscriptionStatus("Expired");
			}

			SubscriptionPayments subscriptionPayments = new SubscriptionPayments();

			subscriptionPayments.setTransportSubscriptions(newSubscription);
			subscriptionPayments.setPaymentDate(LocalDate.now());
			subscriptionPayments.setPaymentMode("Online G-Pay");

			TransportServices transportServices = transportServicesService
					.findById(newSubscription.getTransportService().getId());

			Double monthlyFair = transportServices.getMonthlyFare();

//			We can improve logic here 

			Double dailyFair = monthlyFair / 30;

			System.out.println(dailyFair);

			Long daysBetween = ChronoUnit.DAYS.between(newSubscription.getSubscriptionStartDate(),
					newSubscription.getSubscriptionEndDate());

			System.out.println(daysBetween);

			Double fairAmount = dailyFair * daysBetween;

			subscriptionPayments.setAmount(fairAmount);

			int currentCapacity = transportServices.getCurrentCapacity() + 1;
			if (currentCapacity < transportServices.getMaximumCapacity()) {

				transportServices.setCurrentCapacity(currentCapacity);
			} else {
				throw new Exception("Maximum capacity reached... Please find another service");
			}

			transportSubscriptionsService.addSubscription(newSubscription);

			subscriptionPaymentService.addPaymentDetails(subscriptionPayments);

		} catch (Exception e) {
			throw new Exception(e.getLocalizedMessage());
		}

		return ResponseEntity.ok("Successfully Subscribed to service...");
	}

	@GetMapping("/{id}")
	ResponseEntity<TransportSubscriptions> findSubscriptionById(@PathVariable("id") int id) {
		TransportSubscriptions request = transportSubscriptionsService.findSubscriptionById(id);

		if (request == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(request);
	}

	@DeleteMapping("/{id}/unsubscribe")
	ResponseEntity<String> deleteSubscriptionById(@PathVariable("id") int id) {

		TransportSubscriptions request = transportSubscriptionsService.findSubscriptionById(id);

		SubscriptionPayments subscription = subscriptionPaymentService.getSubscriptionByTransportSubscription(request);

		request.setSubscriptionStatus("Cancelled");
		double returnAmount = subscription.getAmount();

		if (request.getSubscriptionStartDate().isBefore(LocalDate.now())) {
			Long daysBetween = ChronoUnit.DAYS.between(request.getSubscriptionStartDate(),
					request.getSubscriptionEndDate());

			Double perDayFair = returnAmount / daysBetween;

			Long remainingDays = ChronoUnit.DAYS.between(LocalDate.now(), request.getSubscriptionEndDate());
			returnAmount = perDayFair * remainingDays;
		}

		TransportServices transportServices = transportServicesService.findById(request.getTransportService().getId());

		int currentCapacity = transportServices.getCurrentCapacity() - 1;
		transportServices.setCurrentCapacity(currentCapacity);

		subscriptionPaymentService.deleteSubscriptionPaymentById(subscription.getId());

		return ResponseEntity.ok("Successfully Unsubscribed for service. Amount " + returnAmount
				+ " will be returned in 7 business days...");
	}

}
