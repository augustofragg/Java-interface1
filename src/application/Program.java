package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Vehicle;
import model.entities.carRental;
import model.service.BrazilTaxService;
import model.service.RentalService;

public class Program {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); 
		
		System.out.println("Entre com os dados do aluguel");
		System.out.print("Modelo de carro: ");
		String model = sc.nextLine();
		System.out.print("Retirada (dd/MM/yyyy hh:mm): ");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(), dtf);
		System.out.print("Saida (dd/MM/yyyy hh:mm): ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), dtf);
		carRental cr = new carRental(start, finish,new Vehicle(model));
		
		System.out.print("Entre com o preço por hora: ");
		double pricePerHour = sc.nextDouble();
		System.out.print("Entre com o preço por dia: ");
		double pricePerDay = sc.nextDouble();
		RentalService rs = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
		rs.processInvoice(cr);
		
		System.out.println("FATURA:");
		System.out.printf("Pagamento basico: %.2f\n",cr.getInvoice().getBasicPayment());
		System.out.printf("Imposto: %.2f\n",cr.getInvoice().getTax());
		System.out.printf("Pagamento total %.2f\n",cr.getInvoice().getTotalPayment());
		System.out.println();
		
		sc.close();
	}
}
