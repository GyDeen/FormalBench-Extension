
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MulConsecutiveNums {

	public static int[] mulConsecutiveNums(int[] input) {
		if (input.length < 2) {
			return new int[0];
		}

		int[] result = new int[input.length - 1];

		for (int num = 1; num < input.length; num++) {
			result[num - 1] = input[num - 1]
					* input[num];
		}

		return result;
	}
}
