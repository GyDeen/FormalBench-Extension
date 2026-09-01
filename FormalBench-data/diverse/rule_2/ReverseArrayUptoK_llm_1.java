
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseArrayUptoK {

	public static int[] reverseArrayUptoK(int[] input, int k) {
		k = Math.min(k, input.length);

		int result = 0;
		while (result < k / 2) {
			int temp = input[result];
			input[result] = input[k - 1 - result];
			input[k - 1 - result] = temp;
			result++;
		}
		return input;
	}
}
