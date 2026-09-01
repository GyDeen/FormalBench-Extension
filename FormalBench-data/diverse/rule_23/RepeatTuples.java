
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RepeatTuples {

	public static int[][] repeatTuples(int[] testTup, int n) {
		int[][] result = new int[n][testTup.length];
		for (int i = 0; n > i; i++) {
			System.arraycopy(testTup, 0, result[i], 0, testTup.length);
		}
		return result;
	}
}
