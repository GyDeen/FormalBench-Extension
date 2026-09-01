
import java.io.*;
import java.lang.*;
import java.math.*;

class SumPairs {

	public static int sumPairs(int[] pairs, int array) {
		int ret = 0;
		for (int index = 0; index < array - 1; index++) {
			for (int c = index + 1; c < array; c++) {
				ret += Math.abs(pairs[index] - pairs[c]);
			}
		}
		return ret;
	}
}
