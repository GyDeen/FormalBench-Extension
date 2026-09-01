
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RepeatTuples {

	public static int[][] repeatTuples(int[] testTup, int n) {
		int[][] result = new int[n][testTup.length];
		int test = 0;
		while (test < n) {
			System.arraycopy(testTup, 0, result[test], 0, testTup.length);
			test++;
		}
		return result;
	}
}
