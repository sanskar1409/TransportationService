
package com.onecognizant.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;

@Entity
public class TransportSubscriptions {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 6)
	private String subscribedByEmployee;

	private LocalDate subscriptionStartDate;

	private LocalDate subscriptionEndDate;

	@Column(length = 10)
	@Pattern(regexp = "^(Active|Expired|Cancelled)$")
	private String subscriptionStatus;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JsonBackReference
	private TransportServices transportService;

	@OneToMany(mappedBy = "id")
	private List<SubscriptionPayments> subscriptionPayment = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubscribedByEmployee() {
		return subscribedByEmployee;
	}

	public void setSubscribedByEmployee(String subscribedByEmployee) {
		this.subscribedByEmployee = subscribedByEmployee;
	}

	public LocalDate getSubscriptionStartDate() {
		return subscriptionStartDate;
	}

	public void setSubscriptionStartDate(LocalDate subscriptionStartDate) {
		this.subscriptionStartDate = subscriptionStartDate;
	}

	public LocalDate getSubscriptionEndDate() {
		return subscriptionEndDate;
	}

	public void setSubscriptionEndDate(LocalDate subscriptionEndDate) {
		this.subscriptionEndDate = subscriptionEndDate;
	}

	public String getSubscriptionStatus() {
		return subscriptionStatus;
	}

	public void setSubscriptionStatus(String subscriptionStatus) {
		this.subscriptionStatus = subscriptionStatus;
	}

	public TransportServices getTransportService() {
		return transportService;
	}

	public void setTransportService(TransportServices transportService) {
		this.transportService = transportService;
	}

	public List<SubscriptionPayments> getSubscriptionPayment() {
		return subscriptionPayment;
	}

	public void setSubscriptionPayment(List<SubscriptionPayments> subscriptionPayment) {
		this.subscriptionPayment = subscriptionPayment;
	}

	public TransportSubscriptions(int id, String subscribedByEmployee, LocalDate subscriptionStartDate,
			LocalDate subscriptionEndDate, String subscriptionStatus, TransportServices transportService,
			List<SubscriptionPayments> subscriptionPayment) {
		super();
		this.id = id;
		this.subscribedByEmployee = subscribedByEmployee;
		this.subscriptionStartDate = subscriptionStartDate;
		this.subscriptionEndDate = subscriptionEndDate;
		this.subscriptionStatus = subscriptionStatus;
		this.transportService = transportService;
		this.subscriptionPayment = subscriptionPayment;
	}

	public TransportSubscriptions() {
		super();
		// TODO Auto-generated constructor stub
	}

}
