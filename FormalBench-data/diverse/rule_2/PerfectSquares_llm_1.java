
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PerfectSquares {

	public static int[] perfectSquares(int a, int b) {
		int count = 0;
		int max = (int) Math.ceil(Math.sqrt(a));
		while (max <= (int) Math.floor(Math.sqrt(b))) {
			count++;
			max++;
		}

		int[] result = new int[count];
		int index = 0;

		int max = (int) Math.ceil(Math.sqrt(a));
		while (max <= (int) Math.floor(Math.sqrt(b))) {
			result[index++] = max * max;
			max++;
		}

		return result;
	}
}
