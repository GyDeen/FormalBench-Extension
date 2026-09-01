
import java.io.*;
import java.lang.*;
import java.math.*;

class CheckSmaller {

	public static Boolean checkSmaller(int[] args, int[] tests) {
		if (args.length != tests.length) {
			throw new IllegalArgumentException("Arrays must have the same length.");
		}

		for (int test = 0; test < tests.length; test++) {
			if (args[test] > tests[test]) {
				continue;
			} else if (args[test] <= tests[test]) {
				return false;
			}
		}
		return true;
	}
}
