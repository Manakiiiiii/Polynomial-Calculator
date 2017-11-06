import java.util.Scanner;
import java.util.Stack;

/**
 * Calculator is the main class which do the calculation
 * @author írìcà§é˜
 *
 */
public class Calculator {
	
	private static boolean calculation = true;
	public static void main(String args[]) throws Exception {
		
		Scanner sc = new Scanner(System.in);
		Board board = new Board();
		Polynomial basePoly = new Polynomial();
		Polynomial poly = new Polynomial();
		boolean isEnd = false;

		board.printInstruction();

		System.out.println("Type your base Polynomial");
		basePoly.read();
		do {
			do {
				System.out.println("Type which calculation you want to apply (*,+,-,C,A)");
				String operation = sc.nextLine();

				calculation = true;
				switch(operation) {

				case("+"):
					System.out.println("Type your Polynomial to do the calculation");
				poly.read();
				basePoly.sum(poly);
				System.out.println(basePoly);
				break;

				case("-"):
					System.out.println("Type your Polynomial to do the calculation");
				poly.read();
				basePoly.subtract(poly);
				System.out.println(basePoly);
				break;
				case("*"):
					System.out.println("Type your Polynomial to do the calculation");
				poly.read();
				basePoly.multiply(poly);
				System.out.println(basePoly);
				break;
				case("C"):
					basePoly.change();
				calculation = false;
				break;
				case("A"):
					System.out.println("type the coefficient");
				double coe = sc.nextDouble();
				System.out.println("type the exponent");
				Integer exp = sc.nextInt();
				basePoly.addTerm(coe, exp);
				calculation = false;
				break;
				}
			}while(!calculation);


			System.out.println("do you want to continue? (yes/no)");

			String check = sc.nextLine();

			if(check.equalsIgnoreCase("no")) {
				isEnd = true;
			}

		}while(!isEnd);
		System.out.println("Thank you! have a nice day (*`•É÷•)ÅU ");




	}









}
