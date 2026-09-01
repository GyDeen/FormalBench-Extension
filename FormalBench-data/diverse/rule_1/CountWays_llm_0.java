import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountWays {

	public static int countWays(int count) {
		int[] arr = new int[count + 1];
		int[] C = new int[count + 1];
		arr[0] = 1;
		arr[1] = 0;
		C[0] = 0;
		C[1] = 1;
		for (int w = 2; w <= count; w++) {
			arr[w] = arr[w - 2]
					+ 2 * C[w - 1];
			C[w] = arr[w - 1] + C[w - 2];
		}
		return arr[count];
	}
}
