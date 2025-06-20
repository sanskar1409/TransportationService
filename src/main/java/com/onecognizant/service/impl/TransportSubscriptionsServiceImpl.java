package com.onecognizant.service.impl;

import com.onecognizant.dto.TransportSubscriptionDTO;
import com.onecognizant.entity.TransportServices;
import com.onecognizant.entity.TransportSubscriptions;
import com.onecognizant.mapper.TransportSubscriptionMapper;
import com.onecognizant.repository.TransportSubscriptionsRepository;
import com.onecognizant.service.TransportServicesService;
import com.onecognizant.service.TransportSubscriptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransportSubscriptionsServiceImpl implements TransportSubscriptionsService {

	@Autowired
	private TransportSubscriptionsRepository repository;

	@Autowired
	private TransportServicesService transportServicesService;

	@Override
	public void addSubscription(TransportSubscriptionDTO dto) {
		TransportServices service = transportServicesService.findById(dto.getTransportServiceId());
		TransportSubscriptions entity = TransportSubscriptionMapper.toEntity(dto, service);
		repository.save(entity);
	}

	@Override
	public TransportSubscriptionDTO findSubscriptionById(int id) {
		TransportSubscriptions entity = repository.findById(id).orElse(null);
		if (entity == null) return null;
		return TransportSubscriptionMapper.toDTO(entity);
	}

	@Override
	public void deleteSubscription(int id) {
		repository.deleteById(id);
	}
}
