import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Polynomial class holds the List which stores each term of the polynomial
 * @author ’r“cˆ¤Ž÷
 */
/**
 * @author ’r“cˆ¤Ž÷
 *
 */
public class Polynomial {

	public LinkedList<Term> list;
	Scanner sc = new Scanner(System.in);

	/**
	 * Constructor which creates a new Polynomial object
	 * It creates a new LinkedList type Term object
	 */
	public Polynomial() {
		list  = new LinkedList<Term>();
	}
	/**
	 * read method which prompts the user for polynomial input e.g 3x^2-3x+4
	 * <pre> input polynomial should be ordered and should not contain any other character except x or x^
	 * <post> creates a ordered LinkedList which holds each term
	 * @throws UserMissInputException 
	 */
	public void read() throws UserMissInputException {


		String input = sc.nextLine();
		if(input.isEmpty()) {
			throw new UserMissInputException();
		}


		Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
		Matcher matcher = pattern.matcher(input);



		while(matcher.find()) {

			String baseTerm = matcher.group(1);
			//if it has a exponent
			if(baseTerm.contains("^")) {
				String devide[] = baseTerm.split("x\\^");
				Term term = new Term(Double.valueOf(devide[0]),Integer.parseInt(devide[1]));
				list.add(term);


			}
			//if it is does not have a exponent
			else if(baseTerm.contains("x")) {

				String test[] = baseTerm.split("x");
				Term term = new Term(Double.valueOf(test[0]),1);
				list.add(term);

			}
			//else just a number 
			else {
				Term term = new Term(Double.valueOf(baseTerm),0);
				list.add(term);
			}

		}
	}

	/**
	 * addTerm is a method adds a new Term to a existing list
	 * If there is a same Term which has the same exponent term coefficient will be added to that Term. 
	 * @param coefficient coefficient of the Term
	 * @param exponent exponent of the Term
	 * <pre> passed parameter needs to be a actual number not character
	 * <post> Adds a new Term object to a list
	 */
	public void addTerm(double coefficient, int exponent) {

		boolean isEqual = false;
		int index = 0;
		if(!(list.isEmpty())) {
			index	 = list.size()-1;
		}
		for(Term ex : list) {

			if(ex.compare(exponent) == 0) {
				ex.add(coefficient);
				isEqual = true;
				break;
			}
			else if(ex.compare(exponent)== -1) {
				index = list.indexOf(ex);
				break;
			}
		}
		if(!isEqual) {
			Term term = new Term(coefficient,exponent);
			list.add(index, term);
		}
	}

	/**
	 * Change method is used to modify the coefficient and exponent of the selected Term
	 * if the modified Term has a same exponent with the Term in the list coefficient would be added to that Term.
	 */
	public void change() throws Exception{

		boolean isSame = false;

		System.out.println("Type the Term you want to change ");

		this.printInOrder();

		int index = sc.nextInt();
		if(this.list.size()-1<index) {
			throw new IllegalArgumentException();
		}

		System.out.println("Type the new Coefficient");
		double coefficient = sc.nextDouble();
		System.out.println("Type the new Exponent");
		int exponent = sc.nextInt();

		for(Term n: list) {

			if(n.compare(exponent)== 0) {

				n.add(coefficient);
				isSame = true;
				break;
			}
		}

		if(isSame) {
			list.remove(index);
		}
		else {
			list.remove(index);
			this.addTerm(coefficient, exponent);
		}

	}
	/**
	 * sort object which sorts the list which passed Polynomial object holds.
	 * @param poly Polynomial object which you want to sort
	 */
	public void sort(Polynomial poly) {

		Collections.sort(this.list,new PolyComparator());

	}

	/**
	 * sum is a method to add two polynomial(addition)
	 * If the Term in passed Polynomial list has a same exponent in the this.list only the coefficient would be added
	 * otherwise the exponent would be added to the this.list
	 * @param poly polynomial you want to add 
	 * @return returns a this polynomial object result of the addition calculation
	 */
	public Polynomial sum(Polynomial poly) {
		//just a test using for loop 
		for(int i = 0; i<=this.list.size()-1; i++) {

			for(int j = 0; j<=poly.list.size()-1; j++) {
				//if the exponent is larger than the largest in list then add it to the list
				if(list.get(i).getExponent()<poly.list.get(j).getExponent()) {
					addTerm(poly.list.get(j).getCoefficient(),poly.list.get(j).getExponent());
					poly.list.remove(j);
				}
				//if the exponent is equal add that coefficient to the term in list
				if(this.list.get(i).getExponent() == poly.list.get(j).getExponent()) {
					this.list.get(i).add(poly.list.get(j).getCoefficient());
					poly.list.remove(j);
				}
				else {
					addTerm(poly.list.get(j).getCoefficient(), poly.list.get(j).getExponent());
					poly.list.remove(j);
				}
			}
		}
		if(!(poly.list.isEmpty())){
			this.list.add(poly.list.get(poly.list.size()-1));
		}
		sort(this);

		return this;
	}

	/**
	 * multiply is a method to multiply two polynomials
	 * This method gets each term from the this.list then multiply with each Term in passed polynomial. After that it would be added to temp list.
	 * After all calculations are done copy the temp to this.list then sort the list using sort method.
	 * @param poly Polynomial object you want to multiply
	 * @return returns a this polynomial object result of the multiply calculation
	 */
	public Polynomial multiply(Polynomial poly) {
		LinkedList<Term> temp = new LinkedList();

		for(int i = 0; i<list.size(); i++) {

			for(int j = 0; j<poly.list.size(); j++) {
				int exponent  = list.get(i).getExponent();
				double coefficient = list.get(i).getCoefficient();

				Term tempTerm = new Term(coefficient,exponent);
				tempTerm.multiply(poly.list.get(j));
				temp.add(tempTerm);
			}
		}
		this.list = temp;
		sort(this);
		fixOrder();
		return this;
	}

	/**
	 * subtract is a method to subtract two polynomials
	 * This method reverse the plus minus sign of the Term(using reverse method in Term class)
	 * Then call sum method to add(subtract) the polynomial
	 * @param poly Polynomial object you want to subtract
	 * @return
	 */
	public Polynomial subtract(Polynomial poly) {

		for(Term m: poly.list){
			m.reverse();
		}
		return sum(poly);

	}

	/**
	 * printInorder is a method which prints out the polynomial with index number
	 * This method is used inside of change method
	 */
	public void printInOrder() {
		int index = 0;
		for(Term m: list) {
			System.out.println("["+index+"]"+m);
			index++;
		}
	}

	/**
	 * fixOrder is a method to check if there is a duplicate exponent e.g 3x^2+4z^2
	 * If this method finds a duplicate it would add both coefficient it and remove one of it from the list 
	 */
	public void fixOrder() {
		LinkedList<Term> temp = new LinkedList<Term>();
		boolean isOrdered = true;
		do {

			for(int i = 0; i<list.size()-1; i++) {
				if(list.get(i).getExponent() == list.get(i+1).getExponent()) {
					Term tempTerm = list.get(i);
					temp.add(tempTerm);
					list.remove(i);
					isOrdered = false;
				}
				else isOrdered = true;
			}
		} while(!isOrdered);

		Iterator<Term> iter = temp.iterator();
		while(iter.hasNext()) {
			Term tempTerm = iter.next();
			this.addTerm(tempTerm.getCoefficient(), tempTerm.getExponent());
		}


	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {


		StringBuilder sb = new StringBuilder();

		for(Term m: list) {

			if(!(sb.length() == 0)) { //if sb is not empty

				if(m.toString().equals("")) {
					sb.append("");
				}

				else if(!(m.toString().contains("-"))){ //if Term is not a negative value add +
					sb.append(m.toString()+"+");
				}
				else {
					sb.deleteCharAt(sb.length()-1); // if Term contains - delete the + after the previous term
					sb.append(m.toString()+"+");
				}
			}
			else {
				sb.append(m.toString()+"+");
			}

		}
		if(sb.length() != 0) {
			if(sb.charAt(sb.length()-1) == '+') {
				sb.deleteCharAt(sb.length()-1);
			}	
		}
		return sb.toString();


	}

}





