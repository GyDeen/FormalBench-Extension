
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseArrayUptoK {

	public static int[] reverseArrayUptoK(int[] input, int k) {
		k = Math.min(k, input.length);

		int result = k / 2;
		for (int i = 0; i < result; i++) {
			int temp = input[i];
			input[i] = input[k - 1 - i];
			input[k - 1 - i] = temp;
		}
		return input;
	}
}
