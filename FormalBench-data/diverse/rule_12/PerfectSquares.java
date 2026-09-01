
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PerfectSquares {

	public static int[] perfectSquares(int a, int b) {
		int count = 0;
		for (int i = (int) Math.ceil(Math.sqrt(a)); i <= (int) Math.floor(Math.sqrt(b)); i++) {
			count++;
		}

		int index = 0;
		int[] result = new int[count];

		for (int i = (int) Math.ceil(Math.sqrt(a)); i <= (int) Math.floor(Math.sqrt(b)); i++) {
			result[index++] = i * i;
		}

		return result;
	}
}
