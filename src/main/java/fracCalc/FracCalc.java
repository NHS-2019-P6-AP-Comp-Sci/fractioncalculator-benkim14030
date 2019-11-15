/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*;

public class FracCalc {
	public static String user_equation;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("What is your equation?");
		String input = in.nextLine();
		System.out.println(produceAnswer(input));

	}

	public static String produceAnswer(String input) {
		Scanner in = new Scanner(input);
		String frac1 = in.next();
		String op = in.next();
		String frac2 = in.next();

		return frac2;
	}

}
