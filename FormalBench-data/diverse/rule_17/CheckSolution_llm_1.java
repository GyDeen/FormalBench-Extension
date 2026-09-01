import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckSolution {

	public static String checkSolution(int a, int b, int c) {
		int result = a * a - 4 * b * c;
		int temp = a * a - 4 * b * c;
		if (result < 0) {
			return "No";
		} else if (temp == 0) {
			return "Yes";
		} else {
			return "Yes";
		}
	}
}
