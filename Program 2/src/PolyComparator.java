import java.util.Comparator;

/**
 * PolyComparator class to override the compare method for ordering the polynomial list
 * @author ’r“cˆ¤Ž÷
 *
 */
public class PolyComparator implements Comparator<Term>{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Term term1, Term term2) {

		int no1 = term1.getExponent();
		int no2 = term2.getExponent();

		if(no1<no2) {
			return 1;
		}

		else if(no1 == no2) {

			return 0;
		}

		else {
			return -1;
		}

	}

}
