import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

/**
 * Dear Professor Salem
 * How can I test my Polynomial class? I do not pass any parameters to read method?
 * Inside of the read method my program prompts the user for input
 * so, I can not test my program without the main class 
 * @author ’r“cˆ¤Ž÷
 */
public class PolynomialTest {

	// first this test will run to so type enter to check if the exception will be thrown
	@Test(expected = UserMissInputException.class)
	public void readTest() throws Exception {
		Polynomial test = new Polynomial();
		test.read();
	}

	/**
	 * Multiply test type 2 Polynomial to test the program
	 * Need to calculate by hand to check the answer
	 * @throws UserMissInputException When user did not type a polynomial or miss typed it will be thrown
	 */
	@Test
	public void multiplyTest() throws UserMissInputException  {

		Polynomial test = new Polynomial();
		Polynomial test2 = new Polynomial();

		test.read();  
		test2.read();
		test.multiply(test2);
		System.out.println(test);	

	}

	/**
	 * Subtract test need to type 2 polynomial for testing
	 * need to calculate by hand to check the answer
	 * @throws UserMissInputException when user miss input  thrown by read() method
	 */
	@Test
	public void subtractTest() throws UserMissInputException {

		Polynomial test = new Polynomial();
		Polynomial test2 = new Polynomial();
		test.read();
		test2.read();
		test.subtract(test2);
		System.out.println(test);

	}

	/**
	 * Addition test need to type 2 polynomial for testing
	 * need to calculate by hand to chekc the answer
	 * @throws UserMissInputException when user miss type thrown by read() method
	 */
	@Test
	public void sumTest() throws UserMissInputException{
		Polynomial test = new Polynomial();
		Polynomial test2 = new Polynomial();
		test.read();
		test2.read();
		test.sum(test2);
		System.out.println(test);

	}








}

