package ubu.gii.dass.refactoring;

/**
* Tema  Refactorizaciones 
*
* Ejemplo de aplicación de refactorizaciones. Actualizado para colecciones genéricas de java 1.5
*
* @author M. Fowler y <A HREF="mailto:clopezno@ubu.es">Carlos López</A>
* @author <a HREF="mailto:azs1004@alu.ubu.es">Adrián Zamora Sánchez</a>
* @author <a HREF="mailto:edr1006@alu.ubu.es">Estíbalitz Diéz Rioja</a>
* @author <a HREF="mailto:ldg1008@alu.ubu.es">Luis Ignacio De Luna Gómez</a>
* @author <a HREF="mailto:amp1048@alu.ubu.es">Ahmad Mareie Pascual</a>
* @since 1.1
* @version 1.2
* @see java.io.File
*
*/
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Customer {
	private String _name;
	private List<Rental> _rentals;

	public Customer(String name) {
		_name = name;
		_rentals = new ArrayList<Rental>();

	};

	public void addRental(Rental arg) {
		_rentals.add(arg);
	}

	public String getName() {
		return _name;
	};

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Iterator<Rental> rentals = _rentals.iterator();
		
		String result = generateHeader();
		while (rentals.hasNext()) {
			double thisAmount = 0;
			Rental rental = rentals.next();
			// determine amounts for each line
			thisAmount = rental._movie.calculateAmount(rental.getDaysRented());
			
			// add frequent renter points
			frequentRenterPoints = accumulateFrequentRenterPoints(frequentRenterPoints, rental);
			// show figures for this rental
			result = appendRentalLine(result, thisAmount, rental);
			totalAmount = accumulateAmount(totalAmount, thisAmount);
		}
		// add footer lines
		result = generateFooter(totalAmount, frequentRenterPoints, result);
		return result;
	}
	
	public String statementHTML() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Iterator<Rental> rentals = _rentals.iterator();

		String result = generateHeaderHTML();

		while (rentals.hasNext()) {
			double thisAmount = 0;
			Rental rental = rentals.next();
			thisAmount = rental._movie.calculateAmount(rental.getDaysRented());

			frequentRenterPoints = accumulateFrequentRenterPoints(frequentRenterPoints, rental);
			result = appendRentalLineHTML(result, thisAmount, rental);
			totalAmount = accumulateAmount(totalAmount, thisAmount);
		}

		result = generateFooterHTML(totalAmount, frequentRenterPoints, result);

		return result;
	}

	private String generateFooterHTML(double totalAmount, int frequentRenterPoints, String result) {
		result += "</ul>\n";
		result += "<p>Amount owed: " + totalAmount + "<p>\n";
		result += "<p>Frequent renter points earned: " + frequentRenterPoints + "</p>\n";
		result += "</body>\n</html>";
		return result;
	}

	private String appendRentalLineHTML(String result, double thisAmount, Rental rental) {
		result += "<li>" + rental.getMovie().getTitle() + ": " + thisAmount + "</li>\n";
		return result;
	}

	private String generateHeaderHTML() {
		String result = "<!DOCTYPE html>\n<html>\n<head><title>Rental Record</title></head>\n<body>\n";
		result += "<h1>Rental Record for <em>" + getName() + "</em></h1>\n";
		result += "<ul>\n";
		return result;
	}

	private int accumulateFrequentRenterPoints(int frequentRenterPoints, Rental rental) {
		frequentRenterPoints += rental._movie.calculateFrequentRenterPoints(rental.getDaysRented());
		return frequentRenterPoints;
	}

	private double accumulateAmount(double totalAmount, double thisAmount) {
		totalAmount += thisAmount;
		return totalAmount;
	}

	private String appendRentalLine(String result, double thisAmount, Rental rental) {
		result += "\t" + rental.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
		return result;
	}

	private String generateHeader() {
		String result = "Rental Record for " + getName() + "\n";
		return result;
	}

	private String generateFooter(double totalAmount, int frequentRenterPoints, String result) {
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
		return result;
	}
}
