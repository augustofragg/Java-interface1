package model.service;

import java.time.Duration;

import model.entities.Invoice;
import model.entities.carRental;

public class RentalService {
	private double pricePerHour;
	private double pricePerDay;
	
	private TaxService taxService;
	
	public RentalService() {
	}

	public RentalService(double pricePerHour, double pricePerDay,TaxService taxService) {
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.taxService = taxService;
	}

	public double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public TaxService getTaxService() {
		return taxService;
	}

	public void setTaxService(TaxService taxService) {
		this.taxService = taxService;
	}
	
	public void processInvoice(carRental CarRental) {
		double minutes = Duration.between(CarRental.getStart(),CarRental.getFinish()).toMinutes();
		double hours = minutes/60.0;
		
		double basicPayment;
		if(hours <= 12) {
			basicPayment = pricePerHour * Math.ceil(hours);
		}
		else {
			basicPayment = pricePerDay * Math.ceil(hours/24.0);
		}
		
		double tax = taxService.tax(basicPayment);
		
		CarRental.setInvoice(new Invoice(basicPayment,tax));
	}	
}
