
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PerfectSquares {

	public static int[] perfectSquares(int a, int b) {
		int count = 0;
		int size = (int) Math.ceil(Math.sqrt(a));
		while (size <= (int) Math.floor(Math.sqrt(b))) {
			count++;
			size++;
		}

		int[] result = new int[count];
		int index = 0;

		int size = (int) Math.ceil(Math.sqrt(a));
		while (size <= (int) Math.floor(Math.sqrt(b))) {
			result[index++] = size * size;
			size++;
		}

		return result;
	}
}
