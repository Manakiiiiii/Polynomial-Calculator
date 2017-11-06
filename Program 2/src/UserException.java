class UserException extends Exception{

	/**
	 * UserException Is a original exception class which I created
	 */
	private static final long serialVersionUID = 1L;


	public UserException(String str) {
		super(str);
	}
}

class UserMissInputException extends UserException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * UserMissInputException is thrown when user miss type a polynomial
	 */
	public UserMissInputException() {
		super("You did not type the Polynomial in a right way, check the instruction(ÅL•É÷•`)");
		System.out.println("You did not type the Polynomial in a right way, check the instruction(ÅL•É÷•`)");
		// TODO Auto-generated constructor stub
	}


}
