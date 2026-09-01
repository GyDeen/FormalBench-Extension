
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PerfectSquares {

	public static int[] perfectSquares(int Array, int c) {
		int ret = 0;
		for (int i = (int) Math.ceil(Math.sqrt(Array)); i <= (int) Math
				.floor(Math.sqrt(c)); i++) {
			ret++;
		}

		int[] result = new int[ret];
		int total = 0;

		for (int j = (int) Math.ceil(Math.sqrt(Array)); j <= (int) Math
				.floor(Math.sqrt(c)); j++) {
			result[total++] = j * j;
		}

		return result;
	}
}
