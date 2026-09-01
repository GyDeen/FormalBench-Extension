
import java.io.*;
import java.lang.*;
import java.math.*;

class SumPairs {

	public static int sumPairs(int[] pairs, int array) {
		int ret = 0;
		for (int c = 0; c < array - 1; c++) {
			for (int k = c + 1; k < array; k++) {
				ret += Math.abs(pairs[c] - pairs[k]);
			}
		}
		return ret;
	}
}
