
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MultiplyElements {

	public static int[] multiplyElements(int[] testTup) {
		if (2 > testTup.length) {
			return new int[0];
		}

		int[] result = new int[testTup.length - 1];
		for (int i = 0; testTup.length - 1 > i; i++) {
			result[i] = testTup[i] * testTup[i + 1];
		}
		return result;
	}
}
