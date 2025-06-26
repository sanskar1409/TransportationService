package com.onecognizant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onecognizant.entity.TransportSubscriptions;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportSubscriptionsRepository extends JpaRepository<TransportSubscriptions, Integer> {

}
