
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
		int num = 0;
		while (num < testTup.length - 1) {
			result[num] = testTup[num] * testTup[num + 1];
			num++;
		}
		return result;
	}
}
