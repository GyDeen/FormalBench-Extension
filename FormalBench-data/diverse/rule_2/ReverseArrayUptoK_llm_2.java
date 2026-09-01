
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseArrayUptoK {

	public static int[] reverseArrayUptoK(int[] input, int k) {
		k = Math.min(k, input.length);

		int tmp = 0;
		while (tmp < k / 2) {
			int temp = input[tmp];
			input[tmp] = input[k - 1 - tmp];
			input[k - 1 - tmp] = temp;
			tmp++;
		}
		return input;
	}
}
