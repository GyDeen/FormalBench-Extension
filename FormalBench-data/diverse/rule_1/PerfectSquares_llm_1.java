
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PerfectSquares {

	public static int[] perfectSquares(int Array, int c) {
		int array = 0;
		for (int i = (int) Math.ceil(Math.sqrt(Array)); i <= (int) Math
				.floor(Math.sqrt(c)); i++) {
			array++;
		}

		int[] result = new int[array];
		int start = 0;

		for (int j = (int) Math.ceil(Math.sqrt(Array)); j <= (int) Math
				.floor(Math.sqrt(c)); j++) {
			result[start++] = j * j;
		}

		return result;
	}
}
