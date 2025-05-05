package ubu.gii.dass.refactoring;
/**
 * Tema Refactorizaciones
 * 
 * Ejemplo de aplicación de refactorizaciones. Actualizado para colecciones
 * genéricas de java 1.5.
 * 
 * @author M. Fowler y <A HREF="mailto:clopezno@ubu.es">Carlos López</A>
 * * @author <a HREF="mailto:azs1004@alu.ubu.es">Adrián Zamora Sánchez</a>
* @author <a HREF="mailto:edr1006@alu.ubu.es">Estíbalitz Diéz Rioja</a>
* @author <a HREF="mailto:ldg1008@alu.ubu.es">Luis Ignacio De Luna Gómez</a>
* @author <a HREF="mailto:amp1048@alu.ubu.es">Ahmad Mareie Pascual</a>
 * @version 1.2
 * @see java.io.File
 * 
 */

public abstract class Movie {
	
	private String _title;
	

	public Movie(String title) {
		_title = title;
	}

	public String getTitle() {
		return _title;
	}

	/**
	 * Calcula el importe de una película en función de su tipo y los días.
	 * @param daysRented 
	 * 
	 * @return
	 */
	public abstract double calculateAmount(int daysRented); 
	//Manual no existe un replace Conditional Polimorphish https://refactoring.com/catalog/replaceConditionalWithPolymorphism.html

	/**
	 * Calcula los puntos de alquiler frecuentes. Se le añade un punto por cada.
	 * @param daysRented 
	 * 
	 * @return
	 */
	public abstract int calculateFrequentRenterPoints(int daysRented);
}
