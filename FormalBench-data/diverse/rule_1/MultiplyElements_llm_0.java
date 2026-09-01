
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MultiplyElements {

	public static int[] multiplyElements(int[] input) {
		if (input.length < 2) {
			return new int[0];
		}

		int[] test = new int[input.length - 1];
		for (int index = 0; index < input.length - 1; index++) {
			test[index] = input[index]
					* input[index + 1];
		}
		return test;
	}
}
