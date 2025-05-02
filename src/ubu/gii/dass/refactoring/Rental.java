package ubu.gii.dass.refactoring;
/**
 * Tema Refactorizaciones
 * 
 * Ejemplo de aplicación de refactorizaciones. Actualizado para colecciones
 * genéricas de java 1.5.
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
public class Rental {
	private Movie _movie;
	private int _daysRented;

	public Rental(Movie movie, int daysRented) {
		_movie = movie;
		_daysRented = daysRented;
	}

	public int getDaysRented() {
		return _daysRented;
	}

	public Movie getMovie() {
		return _movie;
	}

	/**
	 * Calcula el importe de una película en función de su tipo y los días.
	 * 
	 * @return
	 */
	public double calculateAmount() {
		double amount = 0;
		switch (getMovie().getPriceCode()) {
		case Movie.REGULAR:
			amount += 2;
			if (getDaysRented() > 2)
				amount += (getDaysRented() - 2) * 1.5;
			break;
		case Movie.NEW_RELEASE:
			amount += getDaysRented() * 3;
			break;
		case Movie.CHILDRENS:
			amount += 1.5;
			if (getDaysRented() > 3)
				amount += (getDaysRented() - 3) * 1.5;
			break;
		}
		return amount;
	}

	/**
	 * Calcula los puntos de alquiler frecuentes. Se le añade un punto por cada.
	 * 
	 * @return
	 */
	public int calculateFrequentRenterPoints() {
		int frequentRenterPoints = 1;
		// add bonus for a two day new release rental
		if ((getMovie().getPriceCode() == Movie.NEW_RELEASE) && getDaysRented() > 1)
			frequentRenterPoints++;
		return frequentRenterPoints;
	}

}
