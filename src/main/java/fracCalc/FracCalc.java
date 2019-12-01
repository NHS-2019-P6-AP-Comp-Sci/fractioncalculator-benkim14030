package fracCalc;

import java.util.Scanner;

public class FracCalc {
// public variables
	public static int frac_whole1;
	public static int frac_num1;
	public static int frac_den1;
	public static int frac_imp1;

	public static int frac_whole2;
	public static int frac_num2;
	public static int frac_den2;
	public static int frac_imp2;

	public static String operand;
	public static String result;

	public static int temp_num;
	public static int temp_den;
	public static int com_den;

/*
 *  Input loop: If the user enter quit then the loop ends.
 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Equation: ");
		String input = in.next();

		while (!input.equalsIgnoreCase("quit")) {
			System.out.println(produceAnswer(input));
			System.out.print("Equation: ");
			input = in.next();
		}
		System.out.print("Closed");
	}

	/*
	 * Checks how valid the input is: If its not a equation then it will produce an
	 * error. Also splits and stores the fractions Array is used in order to get more
	 * than 2 fractions.
	 */
	public static String produceAnswer(String input) {
		String[] a_input = input.split(" ");
		if (a_input.length < 3) {
			return "ERROR";
		} else {
			calc_ex(a_input[1], a_input[0], a_input[2]);
			for (int i = 3; i < a_input.length; i += 2) {
				calc_ex(a_input[i], result, a_input[i + 1]);
			}
			return result;
		}
	}

	/*
	 * Calculating the expression: checks the operand and if its more than 2 then
	 * it prints an error
	 */

	public static void calc_ex(String operand, String op1, String op2) {
		p_frac(op1, true);
		p_frac(op2, false);
		if (frac_den1 == 0 || frac_den2 == 0) {
			result = "ERROR";
		} else {
			if (operand.equals("+")) {
				Add();
			} else if (operand.equals("*")) {
				Multiply();
			} else if (operand.equals("-")) {
				Subract();
			} else if (operand.equals("/")) {
				Divide();
			} else if (operand.length() > 1) {
				result = "ERROR";
			}
		}
	}

	/*
	 * Parsing the fraction
	 */
	public static void p_frac(String op, Boolean op1) {
		String whole_num = "0";
		String num_op = "0";
		String den_op = "0";
		String[] splitwhole_num = op.split("_");
		if (splitwhole_num.length == 2) {
			whole_num = op.split("_")[0];
			num_op = op.split("_")[1].split("/")[0];
			den_op = op.split("_")[1].split("/")[1];
		} else {
			String[] split = op.split("/");
			if (split.length == 2) {
				num_op = split[0];
				den_op = split[1];
			} else {
				whole_num = op;
				den_op = "1";
			}
		}

		set_values(op1, Integer.parseInt(whole_num), Integer.parseInt(num_op), Integer.parseInt(den_op));
	}

//
	public static void set_values(Boolean op1, int whole_num, int num_op, int den_op) {
		if (op1) {
			frac_whole1 = whole_num;
			frac_num1 = num_op;
			frac_den1 = den_op;
			if (whole_num != 0) {

				if (Integer.toString(whole_num).contains("-")) {
					whole_num = Integer.parseInt(Integer.toString(whole_num).split("-")[1]);

					frac_imp1 = ((whole_num * den_op) + num_op) * (-1);
				} else {
					frac_imp1 = (whole_num * den_op) + num_op;
				}
			} else {
				frac_imp1 = num_op;
			}

		} else {
			frac_whole2 = whole_num;
			frac_num2 = num_op;
			frac_den2 = den_op;
			if (whole_num != 0) {

				if (Integer.toString(whole_num).contains("-")) {
					whole_num = Integer.parseInt(Integer.toString(whole_num).split("-")[1]);
					// Convert back to negative
					frac_imp2 = ((whole_num * den_op) + num_op) * (-1);
				} else {
					frac_imp2 = (whole_num * den_op) + num_op;
				}
			} else {
				frac_imp2 = num_op;
			}
		}
	}

//
	public static void SetResult(int num_op, int den_op) {
		if (num_op % den_op == 0) {
			result = Integer.toString(num_op / den_op);
		} else {

			int gcd = findGcd(num_op, den_op);

			if (Integer.toString(gcd).contains("-")) {
				gcd = Integer.parseInt(Integer.toString(gcd).split("-")[1]);
			}
			int sim_num = num_op / gcd;
			int sim_den = den_op / gcd;

			result = converter(sim_num, sim_den);
		}
	}

//
	public static int findGcd(int num1, int num2) {
		if (num2 == 0) {
			return num1;
		}
		return findGcd(num2, num1 % num2);
	}

//
	public static String converter(int num_op, int den_op) {
		Integer whole_num = num_op / den_op;
		Integer r = num_op % den_op;
		if (whole_num < 0) {
			if (Integer.toString(r).contains("-")) {
				r = Integer.parseInt(Integer.toString(r).split("-")[1]);
			}
		}
		return whole_num != 0 ? (whole_num + "_" + r + "/" + den_op) : (r + "/" + den_op);
	}

	// Calculations
	public static String Add() {

		if (frac_den1 == frac_den2) {

			temp_num = frac_imp1 + frac_imp2;

			SetResult(temp_num, frac_den1);
		} else {
			com_den = frac_den1 * frac_den2;
			frac_imp1 = frac_imp1 * (com_den / frac_den1);
			frac_imp2 = frac_imp2 * (com_den / frac_den2);
			temp_num = frac_imp1 + frac_imp2;
			SetResult(temp_num, com_den);
		}
		return result;
	}

	public static String Multiply() {

		temp_num = frac_imp1 * frac_imp2;
		temp_den = frac_den1 * frac_den2;
		SetResult(temp_num, temp_den);
		return result;
	}

	public static String Subract() {

		if (frac_den1 == frac_den2) {

			temp_num = frac_imp1 - frac_imp2;

			SetResult(temp_num, frac_den1);
		} else {
			com_den = frac_den1 * frac_den2;
			frac_imp1 = frac_imp1 * (com_den / frac_den1);
			frac_imp2 = frac_imp2 * (com_den / frac_den2);
			temp_num = frac_imp1 - frac_imp2;
			SetResult(temp_num, com_den);
		}
		return result;
	}

	public static String Divide() {

		if (frac_imp2 < 0) {
			frac_imp2 = Integer.parseInt(Integer.toString(frac_imp2).split("-")[1]);
			int temp_val = frac_den2;
			frac_den2 = frac_imp2;
			frac_imp2 = temp_val;
			temp_num = (frac_imp1 * -1) * frac_imp2;
			temp_den = frac_den1 * frac_den2;
		} else {
			int temp_val = frac_den2;
			frac_den2 = frac_imp2;
			frac_imp2 = temp_val;
			temp_num = frac_imp1 * frac_imp2;
			temp_den = frac_den1 * frac_den2;
		}
		SetResult(temp_num, temp_den);
		return result;
	}
}