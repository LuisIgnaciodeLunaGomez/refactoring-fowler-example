package ubu.gii.dass.refactoring;

/**
 * Tema Refactorizaciones
 * 
 * 
 * @author <a HREF="mailto:azs1004@alu.ubu.es">Adrián Zamora Sánchez</a>
 * @author <a HREF="mailto:edr1006@alu.ubu.es">Estíbalitz Diéz Rioja</a>
 * @author <a HREF="mailto:ldg1008@alu.ubu.es">Luis Ignacio De Luna Gómez</a>
 * @author <a HREF="mailto:amp1048@alu.ubu.es">Ahmad Mareie Pascual</a>
 * @since 1.1
 * @version 1.0
 * @see java.io.File
 * 
 */

//Nueva subclase para el caso regular 

public class RegularMovie extends Movie{

	public RegularMovie(String title) {
		super(title);
	}

	@Override
	public double calculateAmount(int daysRented) {
	      double amount = 2;
	        if (daysRented > 2)
	            amount += (daysRented - 2) * 1.5;
	        return amount;
	}

	@Override
	public int calculateFrequentRenterPoints(int daysRented) {
        return 1; // Las películas normales siempre dan 1 punto

	}

}
