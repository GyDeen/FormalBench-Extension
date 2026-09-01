
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseArrayUptoK {

	public static int[] reverseArrayUptoK(int[] input, int k) {
		k = Math.min(k, input.length);

		for (int i = 0; k / 2 > i; i++) {
			int temp = input[i];
			input[i] = input[k - 1 - i];
			input[k - 1 - i] = temp;
		}
		return input;
	}
}
