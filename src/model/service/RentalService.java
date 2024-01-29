package model.service;

import java.time.Duration;

import model.entities.Invoice;
import model.entities.carRental;

public class RentalService {
	private double pricePerHour;
	private double pricePerDay;
	
	private BrazilTaxService TaxService;
	
	public RentalService() {
	}

	public RentalService(double pricePerHour, double pricePerDay, BrazilTaxService taxService) {
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.TaxService = taxService;
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

	public BrazilTaxService getTaxService() {
		return TaxService;
	}

	public void setTaxService(BrazilTaxService taxService) {
		TaxService = taxService;
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
		
		double tax = TaxService.tax(basicPayment);
		
		CarRental.setInvoice(new Invoice(basicPayment,tax));
	}	
}
