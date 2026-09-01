
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MultiplyElements {

	public static int[] multiplyElements(int[] testTup) {
		if (testTup.length < 2) {
			return new int[0];
		}

		int[] result = new int[testTup.length - 1];
		int test = 0;
		while (test < testTup.length - 1) {
			result[test] = testTup[test] * testTup[test + 1];
			test++;
		}
		return result;
	}
}
