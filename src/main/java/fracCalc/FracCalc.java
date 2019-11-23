/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*;

public class FracCalc {
	public static String user_equation;

	public static boolean loop = false;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.println("What is your equation?");
		String input = in.nextLine();
		input = input.toUpperCase();
		while (!input.equals("QUIT")) {
			System.out.println(produceAnswer(input));
			System.out.println("What is your equation?");
			input = in.nextLine();

		}
		
	}

	public static String produceAnswer(String input) {
		Scanner in = new Scanner(input);
		String frac1 = in.next();
		String op = in.next();
		String frac2 = in.next();

		String frac2_whole = find_whole(frac2);
		String frac2_Num = find_Num(frac2);
		String frac2_Den = find_Den(frac2);

		String frac2_Summary = frac2 +" Whole: " + frac2_whole + " Num: " + frac2_Num + " Denom: " + frac2_Den;

		return frac2_Summary;
	}

	public static String find_whole(String s) {
		if (s.contains("_")) {
			return s.substring(0, s.indexOf('_'));
		} else if (s.contains("/")) {
			return "0";
		} else {
			return s;
		}
	}

	public static String find_Num(String s) {
		if (s.contains("_")) {
			return s.substring(s.indexOf('_') + 1, s.indexOf('/'));
		}
		else if (s.contains("/")) {
			return s.substring(0, s.indexOf('/'));
		}else {
			return "0";
		}
	}
	public static String find_Den(String s) {
		if (s.contains("/")) {
			return s.substring(s.indexOf('/')+1);
		}else {
			return "1";
		}
		
		
	}

}
