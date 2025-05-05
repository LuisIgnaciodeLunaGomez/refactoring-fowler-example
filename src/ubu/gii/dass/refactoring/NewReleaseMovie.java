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
//Nueva subclase para eliminar el switch case
public class NewReleaseMovie extends Movie{

	public NewReleaseMovie(String title) {
		super(title);
	}

	@Override
	public double calculateAmount(int daysRented) {
		 return daysRented * 3;
	}

	@Override
	public int calculateFrequentRenterPoints(int daysRented) {
		 // Bonus por alquiler de estreno de más de un día
        return (daysRented > 1) ? 2 : 1;
	}

}
