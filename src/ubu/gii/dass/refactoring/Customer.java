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

	/**
	 * Enum con los formatos de recibo.	
	 */
	private enum Format {
	    TEXT, HTML
	}
	
	/**
	 * Genera el recibo de alquiler en formato texto.
	 * 
	 * @return Recibo de alquiler en formato texto
	 */
	public String statement() {
	    return generateStatement(Format.TEXT);
	}

	/**
	 * Genera el recibo de alquiler en formato HTML.
	 * 
	 * @return Recibo de alquiler en formato HTML
	 */
	public String statementHTML() {
	    return generateStatement(Format.HTML);
	}
	
	/**
	 * Genera el recibo de alquiler en función del formato solicitado.
	 * 
	 * @param format Formato del recibo (texto o HTML)
	 * @return Recibo de alquiler
	 */
	private String generateStatement(Format format) {
	    double totalAmount = 0;
	    int frequentRenterPoints = 0;
	    StringBuilder result = new StringBuilder();

	    // Genera la cabecera
	    result.append(format == Format.TEXT ? generateHeader() : generateHeaderHTML());

	    // Calcula el importe y los puntos de alquiler frecuentes
	    for (Rental rental : _rentals) {
	        double thisAmount = rental._movie.calculateAmount(rental.getDaysRented());
	        frequentRenterPoints += rental._movie.calculateFrequentRenterPoints(rental.getDaysRented());
	        result.append(format == Format.TEXT 
	            ? appendRentalLine(thisAmount, rental) 
	            : appendRentalLineHTML(thisAmount, rental));
	        totalAmount += thisAmount;
	    }

	    // Genera el pie
	    result.append(format == Format.TEXT 
	        ? generateFooter(totalAmount, frequentRenterPoints) 
	        : generateFooterHTML(totalAmount, frequentRenterPoints));

	    return result.toString();
	}
	
	/**
	 * Genera el pie del recibo de alquiler en formato HTML.
	 */
	private String generateFooterHTML(double totalAmount, int frequentRenterPoints) {
		String result = "</ul>\n";
		result += "<p>Amount owed: " + totalAmount + "<p>\n";
		result += "<p>Frequent renter points earned: " + frequentRenterPoints + "</p>\n";
		result += "</body>\n</html>";
		return result;
	}

	/**
	 * Genera una línea del recibo de alquiler en formato HTML.
	 */
	private String appendRentalLineHTML(double thisAmount, Rental rental) {
		return "<li>" + rental.getMovie().getTitle() + ": " + thisAmount + "</li>\n";
	}

	/**
	 * Genera el encabezado del recibo de alquiler en formato HTML.
	 */
	private String generateHeaderHTML() {
		String result = "<!DOCTYPE html>\n<html>\n<head><title>Rental Record</title></head>\n<body>\n";
		result += "<h1>Rental Record for <em>" + getName() + "</em></h1>\n";
		result += "<ul>\n";
		return result;
	}

	/**
	 * Genera el encabezado del recibo de alquiler.
	 */
	private String appendRentalLine(double thisAmount, Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
	}

	/**
	 * Genera el encabezado del recibo de alquiler.
	 */
	private String generateHeader() {
		return "Rental Record for " + getName() + "\n";
	}

	/**
	 * Genera el pie del recibo de alquiler.
	 */
	private String generateFooter(double totalAmount, int frequentRenterPoints) {
		String result = "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
		return result;
	}
}
