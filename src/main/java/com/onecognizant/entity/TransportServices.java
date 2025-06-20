package com.onecognizant.entity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class TransportServices {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 20)
	private String pickupLocation;

	@Column(length = 100)
	private String onRoutePickupPoints;

	private LocalTime startTime;

	private LocalTime returnTime;

	@Column(length = 10, unique = true)
	private String vehicleNo;

	@Column(length = 10)
	private String vehicleType;


	@Column(length = 10, unique = true)
	private String driverPhoneNumber;

	@Column(length = 10)
	private int maximumCapacity;

	@Column(length = 10)
	private int currentCapacity;

	@Column(length = 19)
	private double monthlyFare;

	@OneToMany(mappedBy = "id")
	@JsonBackReference
	private List<TransportServices> transportServices = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPickupLocation() {
		return pickupLocation;
	}

	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
	}

	public String getOnRoutePickupPoints() {
		return onRoutePickupPoints;
	}

	public void setOnRoutePickupPoints(String onRoutePickupPoints) {
		this.onRoutePickupPoints = onRoutePickupPoints;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(LocalTime returnTime) {
		this.returnTime = returnTime;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}


	public String getVehicleType() {
	 	return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		 this.vehicleType = vehicleType;
	}


	public String getDriverPhoneNumber() {
		return driverPhoneNumber;
	}

	public void setDriverPhoneNumber(String driverPhoneNumber) {
		this.driverPhoneNumber = driverPhoneNumber;
	}

	public int getMaximumCapacity() {
		return maximumCapacity;
	}

	public void setMaximumCapacity(int maximumCapacity) {
		this.maximumCapacity = maximumCapacity;
	}

	public int getCurrentCapacity() {
		return currentCapacity;
	}

	public void setCurrentCapacity(int currentCapacity) {
		this.currentCapacity = currentCapacity;
	}

	public double getMonthlyFare() {
		return monthlyFare;
	}

	public void setMonthlyFare(double monthlyFare) {
		this.monthlyFare = monthlyFare;
	}

	public List<TransportServices> getTransportServices() {
		return transportServices;
	}

	public void setTransportServices(List<TransportServices> transportServices) {
		this.transportServices = transportServices;
	}

	public TransportServices(int id, String pickupLocation, String onRoutePickupPoints, LocalTime startTime,
			LocalTime returnTime, String vehicleNo, String vehicleType, String driverPhoneNumber, int maximumCapacity,
			int currentCapacity, double monthlyFare, List<TransportServices> transportServices) {
		super();
		this.id = id;
		this.pickupLocation = pickupLocation;
		this.onRoutePickupPoints = onRoutePickupPoints;
		this.startTime = startTime;
		this.returnTime = returnTime;
		this.vehicleNo = vehicleNo;
		this.vehicleType = vehicleType;
		this.driverPhoneNumber = driverPhoneNumber;
		this.maximumCapacity = maximumCapacity;
		this.currentCapacity = currentCapacity;
		this.monthlyFare = monthlyFare;
		this.transportServices = transportServices;
	}

	public TransportServices() {
		super();
		// TODO Auto-generated constructor stub
	}

}
