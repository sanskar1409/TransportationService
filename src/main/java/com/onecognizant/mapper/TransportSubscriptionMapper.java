package com.onecognizant.mapper;

import com.onecognizant.dto.TransportSubscriptionDTO;
import com.onecognizant.entity.TransportSubscriptions;
import com.onecognizant.entity.TransportServices;

public class TransportSubscriptionMapper {

    public static TransportSubscriptions toEntity(TransportSubscriptionDTO dto, TransportServices service) {
        TransportSubscriptions entity = new TransportSubscriptions();
        entity.setSubscribedByEmployee(dto.getSubscribedByEmployee());
        entity.setTransportService(service);
        entity.setSubscriptionStartDate(dto.getSubscriptionStartDate());
        entity.setSubscriptionEndDate(dto.getSubscriptionEndDate());

        if (dto.getSubscriptionEndDate().isAfter(java.time.LocalDate.now())) {
            entity.setSubscriptionStatus("Active");
        } else {
            entity.setSubscriptionStatus("Expired");
        }

        return entity;
    }

    public static TransportSubscriptionDTO toDTO(TransportSubscriptions entity) {
        TransportSubscriptionDTO dto = new TransportSubscriptionDTO();
        dto.setSubscribedByEmployee(entity.getSubscribedByEmployee());
        dto.setTransportServiceId(entity.getTransportService().getId());
        dto.setSubscriptionStartDate(entity.getSubscriptionStartDate());
        dto.setSubscriptionEndDate(entity.getSubscriptionEndDate());
        return dto;
    }
}

