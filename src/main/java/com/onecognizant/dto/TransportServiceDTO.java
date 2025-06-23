package com.onecognizant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;

public class TransportServiceDTO {

    @NotBlank
    private String vehicleNo;

    @NotBlank
    private String vehicleType;

    @NotNull
    private Double monthlyFare;

    @NotBlank
    private String pickupLocation;

    @NotBlank
    private String onRoutePickupPoints;

    @NotBlank
    @Size(min = 10, max = 10)
    private String driverPhoneNumber;

    @NotNull
    private Integer currentCapacity;

    @NotNull
    private Integer maximumCapacity;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime returnTime;

    // Getters and setters

    public @NotBlank String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(@NotBlank String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public @NotBlank String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(@NotBlank String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public @NotNull Double getMonthlyFare() {
        return monthlyFare;
    }

    public void setMonthlyFare(@NotNull Double monthlyFare) {
        this.monthlyFare = monthlyFare;
    }

    public @NotBlank String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(@NotBlank String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public @NotBlank String getOnRoutePickupPoints() {
        return onRoutePickupPoints;
    }

    public void setOnRoutePickupPoints(@NotBlank String onRoutePickupPoints) {
        this.onRoutePickupPoints = onRoutePickupPoints;
    }

    public @NotBlank @Size(min = 10, max = 10) String getDriverPhoneNumber() {
        return driverPhoneNumber;
    }

    public void setDriverPhoneNumber(@NotBlank @Size(min = 10, max = 10) String driverPhoneNumber) {
        this.driverPhoneNumber = driverPhoneNumber;
    }

    public @NotNull Integer getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(@NotNull Integer currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public @NotNull Integer getMaximumCapacity() {
        return maximumCapacity;
    }

    public void setMaximumCapacity(@NotNull Integer maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }

    public @NotNull LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(@NotNull LocalTime startTime) {
        this.startTime = startTime;
    }

    public @NotNull LocalTime getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(@NotNull LocalTime returnTime) {
        this.returnTime = returnTime;
    }
}