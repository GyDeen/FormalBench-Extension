
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckGreater {

	public static Boolean checkGreater(int[] args, int[] tests) {
		if (args.length != tests.length) {
			throw new IllegalArgumentException("Arrays must be of the same length");
		}

		for (int test = 0; test < args.length; test++) {
			if (tests[test] <= args[test]) {
				return false;
			}
		}
		return true;
	}
}
