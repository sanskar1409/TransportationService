package com.onecognizant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onecognizant.entity.SubscriptionPayments;
import com.onecognizant.entity.TransportSubscriptions;
import com.onecognizant.repository.SubscriptionPaymentsRepository;
import com.onecognizant.service.SubscriptionPaymentService;

@Service
public class SubscriptionPaymentServiceImpl implements SubscriptionPaymentService {

	@Autowired
	private SubscriptionPaymentsRepository subscriptionPaymentsRepository;

	@Override
	public void addPaymentDetails(SubscriptionPayments subscriptionPayments) {

		subscriptionPaymentsRepository.save(subscriptionPayments);

	}

	@Override
	public SubscriptionPayments getSubscriptionByTransportSubscription(TransportSubscriptions transportSubscriptions) {

		return subscriptionPaymentsRepository.findByTransportSubscriptions(transportSubscriptions);
	}

	@Override
	public void deleteSubscriptionPaymentById(int id) {

		subscriptionPaymentsRepository.deleteById(id);

	}

}
