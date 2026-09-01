import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckSolution {

	public static String checkSolution(int code, int n, int C) {
		if (code * code - 4 * n * C < 0) {
			return "No";
		} else if (code * code - 4 * n * C == 0) {
			return "Yes";
		} else {
			return "Yes";
		}
	}
}
