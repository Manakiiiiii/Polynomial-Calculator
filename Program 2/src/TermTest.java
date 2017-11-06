import static org.junit.Assert.*;

import org.junit.Test;

public class TermTest {

	@Test
	public void test() {
		//when exponent and coefficient is 0 it will not print out anything
		Term test = new Term(0,0);
		assertEquals(test.toString(),"");

		//when exponent is 0 it Term will not contain x
		Term test2 = new Term(1,0);
		assertEquals(test2.toString(),"1.0");

		//When both coefficient and exponent holds a value it creates a term with x^
		Term test3 = new Term(2,2);
		assertEquals(test3.toString(),"2.0x^2");

		//addition test
		test.add(3.5);
		assertEquals(test.toString(),"3.5");

		//reverse plus minus test
		test2.reverse();
		assertEquals(test2.toString(),"-1.0");

		//compare method test returns 0 when exponent is equal/ returns -1 when exponent is smaller than parameter / else return 1

		Term test4 = new Term(2,3); //Term 2x^3
		Term test5 = new Term(8,3); //Term 8x^3
		Term test6 = new Term(2,4); // Term 2x^4

		assertEquals(test4.compare(test5.getExponent()),0); //test4 and test 5 exponent is equal
		assertEquals(test4.compare(test.getExponent()),1); // test4's exponent is larger than test1
		assertEquals(test4.compare(test6.getExponent()),-1); // test4 has a smaller exponent than test6

		//multiply test
		test4.multiply(test5);
		assertEquals(test4.toString(),"16.0x^6"); //(2x^3)*(8x^3) = 16.0x^6

		//change Term test
		test.chageTerm(3, 3);
		assertEquals(test.toString(),"3.0x^3"); // changed Term 0 to 3.0x^3

		//when matcher could not find exponent which means there is no exponent
		Term test7 = new Term(4,null);
		assertEquals(test7.toString() ,"4.0");

		//when exponent is 1 it will not print out ^
		Term test8 = new Term(3,1);
		assertEquals(test8.toString(),"3.0x");

	}

}
