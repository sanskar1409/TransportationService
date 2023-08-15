package com.onecognizant.service;

import com.onecognizant.entity.SubscriptionPayments;
import com.onecognizant.entity.TransportSubscriptions;

public interface SubscriptionPaymentService {

	void addPaymentDetails(SubscriptionPayments subscriptionPayments);

	SubscriptionPayments getSubscriptionByTransportSubscription(TransportSubscriptions transportSubscriptions);

	void deleteSubscriptionPaymentById(int id);

}
