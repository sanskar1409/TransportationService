package com.onecognizant.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class SubscriptionPayments {

	@Id
	@Column(length = 10)
	private int id;

	private LocalDate paymentDate;

	@Column(length = 19)
	private double amount;

	@Column(length = 20)
	private String paymentMode;

	@ManyToOne(cascade = CascadeType.MERGE)
	private TransportSubscriptions transportSubscriptions;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public TransportSubscriptions getTransportSubscriptions() {
		return transportSubscriptions;
	}

	public void setTransportSubscriptions(TransportSubscriptions transportSubscriptions) {
		this.transportSubscriptions = transportSubscriptions;
	}

	public SubscriptionPayments(int id, LocalDate paymentDate, double amount, String paymentMode,
			TransportSubscriptions transportSubscriptions) {
		super();
		this.id = id;
		this.paymentDate = paymentDate;
		this.amount = amount;
		this.paymentMode = paymentMode;
		this.transportSubscriptions = transportSubscriptions;
	}

	public SubscriptionPayments() {
		super();
		// TODO Auto-generated constructor stub
	}

}
