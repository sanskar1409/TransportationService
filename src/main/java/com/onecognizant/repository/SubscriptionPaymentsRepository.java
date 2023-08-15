package com.onecognizant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onecognizant.entity.SubscriptionPayments;
import com.onecognizant.entity.TransportSubscriptions;

public interface SubscriptionPaymentsRepository extends JpaRepository<SubscriptionPayments, Integer> {

	SubscriptionPayments findByTransportSubscriptions(TransportSubscriptions transportSubscriptions);
}
