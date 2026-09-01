
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
		int num = testTup.length - 1;
		for (int i = 0; i < num; i++) {
			int ele = i + 1;
			result[i] = testTup[i] * testTup[ele];
		}
		return result;
	}
}
