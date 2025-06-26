package com.onecognizant.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.onecognizant.entity.TransportServices;

@Repository
public interface TransportServicesRepository extends JpaRepository<TransportServices, Integer> {
	Set<TransportServices> findByPickupLocationContainingIgnoreCase(String pickupLocation);
	Set<TransportServices> findByOnRoutePickupPointsContainsAllIgnoreCase(String pickupPoints);
	Set<TransportServices> findById(int id);
}
