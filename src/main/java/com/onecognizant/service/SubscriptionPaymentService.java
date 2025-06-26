package com.onecognizant.service;

import com.onecognizant.entity.SubscriptionPayments;
import com.onecognizant.entity.TransportSubscriptions;


public interface SubscriptionPaymentService {


	SubscriptionPayments save(SubscriptionPayments payment);

	SubscriptionPayments getSubscriptionByTransportSubscription(TransportSubscriptions transportSubscriptions);

	void deleteSubscriptionPaymentById(int id);

}
