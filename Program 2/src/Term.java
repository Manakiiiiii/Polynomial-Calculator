
/**
 * Term class object holds coefficient & exponent of Polynomial
 * @author ’r“cˆ¤Ž÷
 */
public class Term {

	private double coefficient;
	private int exponent;


	/**
	 * Constructor creates a Term object which holds passed coefficient and exponent
	 * @param coefficient coefficient of the term type double
	 * @param exponent exponent of the term type Integer
	 */
	public Term(double coefficient, Integer exponent) {

		this.coefficient = coefficient;	
		if(exponent == null) {
			this.exponent = 0;
		}
		else {
			this.exponent = exponent;
		}

	}



	/**
	 * This method returns the value of coefficient
	 * @return returns the coefficient value
	 */
	public double getCoefficient() {
		return this.coefficient;
	}

	/**
	 * This method returns the value of exponent
	 * @return returns the exponent value 
	 */
	public int getExponent() {
		return this.exponent;
	}

	/* (non-Javadoc) 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("");

		if(this.exponent == 1) {
			sb.append(this.coefficient+"x");
			return sb.toString();
		}

		if(this.exponent == 0 && this.coefficient != 0) {
			sb.append(this.coefficient);
			return sb.toString();

		}

		if(this.coefficient == 0) {
			sb.append("");
			return sb.toString();
		}

		else { 
			sb.append(this.coefficient+"x^"+this.exponent);
			return sb.toString();
		}
	}

	/**
	 * This method adds the passed coefficient to the this.term coefficient
	 * @param coefficient
	 */
	public void add(double coefficient) {
		this.coefficient += coefficient;
	}


	/**
	 * This method is used to change the value of the coefficient & exponent
	 * @param coefficient coefficient of the term
	 * @param exponent exponent of the term
	 */
	public void chageTerm(double coefficient, int exponent) {
		this.coefficient = coefficient;
		this.exponent = exponent;
	}

	/**
	 * reverse is a method reverse the plus and minus sign of the coefficient
	 */
	public void reverse() {
		this.coefficient *= -1;
	}

	/**
	 * compare is a method to compare Term objects
	 * if the term has a same exponent it would return 0, if the exponent is smaller than the passed exponent return -1 else it would return 1
	 * @param exponent term exponent
	 * @return returns a result of the comparison
	 */
	public int compare(int exponent){
		if(this.exponent == exponent) {
			return 0;
		}

		if(this.exponent<exponent) {
			return -1;
		}
		return 1;
	}

	/**
	 * multiply is a method to multiply to Terms
	 * coefficient would be multiplied and exponent would be added
	 * @param term term object
	 */
	public void multiply(Term term) {
		this.coefficient *=term.getCoefficient();
		this.exponent += term.getExponent();
	}

}
