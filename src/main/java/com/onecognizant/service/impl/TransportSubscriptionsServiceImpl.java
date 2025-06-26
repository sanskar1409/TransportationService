package com.onecognizant.service.impl;

import com.onecognizant.dto.TransportSubscriptionDTO;
import com.onecognizant.entity.SubscriptionPayments;
import com.onecognizant.entity.TransportServices;
import com.onecognizant.entity.TransportSubscriptions;
import com.onecognizant.mapper.TransportSubscriptionMapper;
import com.onecognizant.repository.TransportServicesRepository;
import com.onecognizant.repository.TransportSubscriptionsRepository;
import com.onecognizant.service.SubscriptionPaymentService;
import com.onecognizant.service.TransportServicesService;
import com.onecognizant.service.TransportSubscriptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TransportSubscriptionsServiceImpl implements TransportSubscriptionsService {

	@Autowired
	private TransportSubscriptionsRepository repository;

	@Autowired
	private TransportServicesService transportServicesService;

	@Autowired
	private SubscriptionPaymentService subscriptionPaymentService;

	@Autowired
	private TransportServicesRepository transportServicesRepository;




	@Override
	public TransportSubscriptions addSubscription(TransportSubscriptionDTO dto) {
		TransportSubscriptions subscription = new TransportSubscriptions();
		subscription.setSubscribedByEmployee(dto.getSubscribedByEmployee());
		subscription.setSubscriptionStartDate(dto.getSubscriptionStartDate());
		subscription.setSubscriptionEndDate(dto.getSubscriptionEndDate());

		TransportServices service = transportServicesRepository.findById(dto.getTransportServiceId())
				.orElseThrow(() -> new RuntimeException("Transport service not found"));
		subscription.setTransportService(service);
		subscription.setSubscriptionStatus("Active");

		// Save subscription
		TransportSubscriptions savedSubscription = repository.save(subscription);

		// Create and save payment
		SubscriptionPayments payment = new SubscriptionPayments();
		payment.setAmount(dto.getAmount());
		payment.setPaymentDate(LocalDate.now());
		payment.setTransportSubscriptions(savedSubscription);
//		payment.setPaymentMethod(dto.getPaymentMethod());

		subscriptionPaymentService.save(payment);

		// Update capacity
		service.setCurrentCapacity(service.getCurrentCapacity() + 1);
		transportServicesRepository.save(service);

		return savedSubscription;
	}


	@Override
	public TransportSubscriptionDTO findSubscriptionById(int id) {
		TransportSubscriptions entity = repository.findById(id).orElse(null);
		if (entity == null) return null;
		return TransportSubscriptionMapper.toDTO(entity);
	}

	@Override
	public void deleteSubscription(int id) {
		repository.deleteById(id);
	}

	@Override
	public TransportSubscriptions findEntityById(int id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Subscription not found"));
	}
}
