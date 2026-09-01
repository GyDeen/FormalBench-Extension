
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RepeatTuples {

	public static int[][] repeatTuples(int[] testTup, int n) {
		int[][] result = new int[n][testTup.length];
		int num = 0;
		while (num < n) {
			System.arraycopy(testTup, 0, result[num], 0, testTup.length);
			num++;
		}
		return result;
	}
}
