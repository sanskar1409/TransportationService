package com.onecognizant.service;

import com.onecognizant.entity.TransportSubscriptions;

public interface TransportSubscriptionsService {

	void addSubscription(TransportSubscriptions transportSubscriptions);

	TransportSubscriptions findSubscriptionById(int id);

	void deleteSubscription(int id);
	
}
