import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckSolution {

	public static String checkSolution(int a, int b, int c) {
		if (a * a - 4 * b * c < 0) {
			return "No";
		} else if (0 == a * a - 4 * b * c) {
			return "Yes";
		} else {
			return "Yes";
		}
	}
}
