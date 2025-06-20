package com.onecognizant.service;

import com.onecognizant.dto.TransportSubscriptionDTO;

public interface TransportSubscriptionsService {

	void addSubscription(TransportSubscriptionDTO dto);

	TransportSubscriptionDTO findSubscriptionById(int id);

	void deleteSubscription(int id);
}
