package com.onecognizant.mapper;

import com.onecognizant.dto.TransportServiceDTO;
import com.onecognizant.entity.TransportServices;

public class TransportServiceMapper {

    public static TransportServices toEntity(TransportServiceDTO dto) {
        TransportServices entity = new TransportServices();
        entity.setVehicleNo(dto.getVehicleNo());
        entity.setVehicleType(dto.getVehicleType());
        entity.setMonthlyFare(dto.getMonthlyFare());
        entity.setPickupLocation(dto.getPickupLocation());
        entity.setOnRoutePickupPoints(dto.getOnRoutePickupPoints());
        entity.setDriverPhoneNumber(dto.getDriverPhoneNumber());
        entity.setCurrentCapacity(dto.getCurrentCapacity());
        entity.setMaximumCapacity(dto.getMaximumCapacity());
        entity.setStartTime(dto.getStartTime());
        entity.setReturnTime(dto.getReturnTime());
        return entity;
    }

    public static TransportServiceDTO toDTO(TransportServices entity) {
        TransportServiceDTO dto = new TransportServiceDTO();
        dto.setVehicleNo(entity.getVehicleNo());
        dto.setVehicleType(entity.getVehicleType());
        dto.setMonthlyFare(entity.getMonthlyFare());
        dto.setPickupLocation(entity.getPickupLocation());
        dto.setOnRoutePickupPoints(entity.getOnRoutePickupPoints());
        dto.setDriverPhoneNumber(entity.getDriverPhoneNumber());
        dto.setCurrentCapacity(entity.getCurrentCapacity());
        dto.setMaximumCapacity(entity.getMaximumCapacity());
        dto.setStartTime(entity.getStartTime());
        dto.setReturnTime(entity.getReturnTime());
        return dto;
    }
}

