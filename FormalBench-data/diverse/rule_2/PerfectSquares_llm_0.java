
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PerfectSquares {

	public static int[] perfectSquares(int a, int b) {
		int count = 0;
		int total = (int) Math.ceil(Math.sqrt(a));
		while (total <= (int) Math.floor(Math.sqrt(b))) {
			count++;
			total++;
		}

		int[] result = new int[count];
		int index = 0;

		int total = (int) Math.ceil(Math.sqrt(a));
		while (total <= (int) Math.floor(Math.sqrt(b))) {
			result[index++] = total * total;
			total++;
		}

		return result;
	}
}
