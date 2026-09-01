
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
		int index = 0;
		while (index < testTup.length - 1) {
			result[index] = testTup[index] * testTup[index + 1];
			index++;
		}
		return result;
	}
}
