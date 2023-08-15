package com.onecognizant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onecognizant.entity.TransportSubscriptions;
import com.onecognizant.repository.TransportSubscriptionsRepository;
import com.onecognizant.service.TransportSubscriptionsService;

@Service
public class TransportSubscriptionsServiceImpl implements TransportSubscriptionsService {

	@Autowired
	private TransportSubscriptionsRepository transportSubscriptionsRepository;

	@Override
	public void addSubscription(TransportSubscriptions transportSubscriptions) {
		// TODO Auto-generated method stub

		transportSubscriptionsRepository.save(transportSubscriptions);

	}

	@Override
	public TransportSubscriptions findSubscriptionById(int id) {

		return transportSubscriptionsRepository.findById(id).get();
	}

	@Override
	public void deleteSubscription(int id) {

		transportSubscriptionsRepository.deleteById(id);

	}

}
