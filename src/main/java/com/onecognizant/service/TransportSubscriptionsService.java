package com.onecognizant.service;

import com.onecognizant.dto.TransportSubscriptionDTO;
import com.onecognizant.entity.TransportSubscriptions;

public interface TransportSubscriptionsService {

	TransportSubscriptions addSubscription(TransportSubscriptionDTO dto);

	TransportSubscriptionDTO findSubscriptionById(int id);

	void deleteSubscription(int id);

	TransportSubscriptions findEntityById(int id);
}