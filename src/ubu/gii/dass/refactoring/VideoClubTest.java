package ubu.gii.dass.refactoring;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tema Refactorizaciones
 * 
 * Ejemplo de aplicación de refactorizaciones. Actualizado para colecciones
 * genéricas de java 1.5
 * 
 * @author M. Fowler y <A HREF="mailto:clopezno@ubu.es">Carlos L�pez</A>
 *  @author <a HREF="mailto:edr1006@alu.ubu.es">Estíbalitz Diéz Rioja</a>
 * @author <a HREF="mailto:ldg1008@alu.ubu.es">Luis Ignacio De Luna Gómez</a>
 * @author <a HREF="mailto:amp1048@alu.ubu.es">Ahmad Mareie Pascual</a>
 * @version 1.2

 * 
 */
public class VideoClubTest {
	protected Movie m0, m11, m12, m2;
	protected Customer c1;
	
	@Before
	public void setUp() {
		m11 = new NewReleaseMovie("Sky Captain");
		m12 = new NewReleaseMovie("Alejandro Magno");
		m0 = new RegularMovie("Accion Mutante");
		m2 = new ChildrensMovie("Hermano Oso");

		c1 = new Customer("Manuel");
	}

	@After
	public void tearDown() throws Exception {}

	@Test
	public void testAlquiler() {

		Rental r1 = new Rental(m11, 5);
		Rental r2 = new Rental(m0, 1);
		Rental r3 = new Rental(m2, 10);

		c1.addRental(r1);
		c1.addRental(r2);
		c1.addRental(r3);

		String salida = c1.statement();

		String salidaEsperada = new String("Rental Record for Manuel\n"
				+ "\tSky Captain\t15.0\n" + "\tAccion Mutante\t2.0\n"
				+ "\tHermano Oso\t12.0\n" + "Amount owed is 29.0\n"
				+ "You earned 4 frequent renter points");

		assertTrue("Calcula mal el alquiler", salidaEsperada.equals(salida));

	}
	
	 @Test
	    public void testRegularMovieLongRental() {
	        // Prueba específica para cubrir el 'if' 
	        Movie regularMovie = new RegularMovie("Pelicula Larga Regular");
	        int daysRented = 5; // Un número mayor que 2
	        Rental longRegularRental = new Rental(regularMovie, daysRented);

	        // Calcula el importe esperado: 2 + (5 - 2) * 1.5 = 2 + 3 * 1.5 = 2 + 4.5 = 6.5
	        double expectedAmount = 6.5;
	        // Calcula los puntos esperados: siempre 1
	        int expectedPoints = 1;
	        
	        Customer testCustomer = new Customer("Test Regular");
	        testCustomer.addRental(longRegularRental);
	        String statementOutput = testCustomer.statement();
	        assertTrue("El extracto no contiene el importe esperado para alquiler largo regular", statementOutput.contains("Pelicula Larga Regular\t" + expectedAmount));
	        assertTrue("El extracto no contiene los puntos esperados para alquiler largo regular", statementOutput.contains("You earned " + expectedPoints + " frequent renter points"));
	    }
	  @Test
	     public void testChildrensMovieLongRental() {
	        Movie childrensMovie = new ChildrensMovie("Pelicula Larga Infantil");
	        int daysRented = 5; // Mayor que 3 para entrar en el if
	        Rental longChildrensRental = new Rental(childrensMovie, daysRented);
	        // Importe: 1.5 + (5 - 3) * 1.5 = 1.5 + 2 * 1.5 = 1.5 + 3.0 = 4.5
	        double expectedAmount = 4.5;
	        assertEquals("Importe incorrecto para Childrens > 3 dias", expectedAmount, childrensMovie.calculateAmount(daysRented), 0.001);
	        assertEquals("Puntos incorrectos para Childrens > 3 dias", 1, childrensMovie.calculateFrequentRenterPoints(daysRented));
	     }

	      @Test
	     public void testNewReleaseMovieBonusPoints() {
	        Movie newReleaseMovie = new NewReleaseMovie("Estreno Bonus");
	        int daysRented = 2; // Mayor que 1 para entrar en el if de puntos
	        Rental bonusNewRelease = new Rental(newReleaseMovie, daysRented);
	        // Importe: 2 * 3 = 6.0
	        double expectedAmount = 6.0;
	         // Puntos: 2 porque daysRented > 1
	        int expectedPoints = 2;
	        assertEquals("Importe incorrecto para New Release", expectedAmount, newReleaseMovie.calculateAmount(daysRented), 0.001);
	        assertEquals("Puntos incorrectos para New Release > 1 dia", expectedPoints, newReleaseMovie.calculateFrequentRenterPoints(daysRented));
	     }
	      
	      @Test
	      public void testNewReleaseMovieSingleDay() {
	          Movie newReleaseMovie = new NewReleaseMovie("Estreno Un Dia");
	          int daysRented = 1; // Exactamente 1 día
	          Rental singleDayNewRelease = new Rental(newReleaseMovie, daysRented);

	          // Importe esperado: 1 * 3 = 3.0
	          double expectedAmount = 3.0;
	          // Puntos esperados: 1 porque daysRented NO es > 1
	          int expectedPoints = 1;

	          Customer testCustomer = new Customer("Test Estreno 1 Dia");
	          testCustomer.addRental(singleDayNewRelease);
	          String statementOutput = testCustomer.statement();
	          assertTrue("El extracto no contiene el importe esperado para alquiler 1 día", statementOutput.contains("Estreno Un Dia\t" + expectedAmount));
	          assertTrue("El extracto no contiene los puntos esperados para alquiler 1 día", statementOutput.contains("You earned " + expectedPoints + " frequent renter points"));
	      }
}
