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
		String result = "Rental Record for " + getName() + "\n";
		while (rentals.hasNext()) {
			double thisAmount = 0;
			Rental rental = rentals.next();
			// determine amounts for each line
			thisAmount = rental.calculateAmount();
			
			// add frequent renter points
			frequentRenterPoints += rental.calculateFrequentRenterPoints();
			// show figures for this rental
			result += "\t" + rental.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
			totalAmount += thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
		return result;
	}
}
