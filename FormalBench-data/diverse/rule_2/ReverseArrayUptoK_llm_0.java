
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseArrayUptoK {

	public static int[] reverseArrayUptoK(int[] input, int k) {
		k = Math.min(k, input.length);

		int output = 0;
		while (output < k / 2) {
			int temp = input[output];
			input[output] = input[k - 1 - output];
			input[k - 1 - output] = temp;
			output++;
		}
		return input;
	}
}
