import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckSolution {

	public static String checkSolution(int code, int d, int n) {
		if (code * code - 4 * d * n < 0) {
			return "No";
		} else if (code * code - 4 * d * n == 0) {
			return "Yes";
		} else {
			return "Yes";
		}
	}
}
