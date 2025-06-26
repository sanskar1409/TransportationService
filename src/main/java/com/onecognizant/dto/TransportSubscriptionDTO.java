package com.onecognizant.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class TransportSubscriptionDTO {

    @NotNull
    private Integer subscribedByEmployee;
    @NotNull
    private Integer transportServiceId;
    @NotNull
    private LocalDate subscriptionStartDate;
    @NotNull
    private LocalDate subscriptionEndDate;

    private double amount;
    private String paymentMethod;


    // Getters and setters

    public @NotNull Integer getSubscribedByEmployee() {
        return subscribedByEmployee;
    }

    public void setSubscribedByEmployee(@NotNull Integer subscribedByEmployee) {
        this.subscribedByEmployee = subscribedByEmployee;
    }

    public @NotNull Integer getTransportServiceId() {
        return transportServiceId;
    }

    public void setTransportServiceId(@NotNull Integer transportServiceId) {
        this.transportServiceId = transportServiceId;
    }

    public @NotNull LocalDate getSubscriptionStartDate() {
        return subscriptionStartDate;
    }

    public void setSubscriptionStartDate(@NotNull LocalDate subscriptionStartDate) {
        this.subscriptionStartDate = subscriptionStartDate;
    }

    public @NotNull LocalDate getSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    public void setSubscriptionEndDate(@NotNull LocalDate subscriptionEndDate) {
        this.subscriptionEndDate = subscriptionEndDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}