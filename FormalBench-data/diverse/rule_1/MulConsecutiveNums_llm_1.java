
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

		for (int index = 1; index < input.length; index++) {
			result[index - 1] = input[index - 1]
					* input[index];
		}

		return result;
	}
}
