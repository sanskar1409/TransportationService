package com.onecognizant.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "subscription_payments")
public class SubscriptionPayments {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;


	private LocalDate paymentDate;

	@Column
	private double amount;

	@Column(length = 20)
	private String paymentMode;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JsonBackReference
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
