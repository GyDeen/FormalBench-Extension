import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckSolution {

	public static String checkSolution(int a, int b, int c) {
		int ret = a * a - 4 * b * c;
		int tmp = a * a - 4 * b * c;
		if (ret < 0) {
			return "No";
		} else if (tmp == 0) {
			return "Yes";
		} else {
			return "Yes";
		}
	}
}
