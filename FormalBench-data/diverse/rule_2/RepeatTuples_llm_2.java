
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RepeatTuples {

	public static int[][] repeatTuples(int[] testTup, int n) {
		int[][] result = new int[n][testTup.length];
		int arr = 0;
		while (arr < n) {
			System.arraycopy(testTup, 0, result[arr], 0, testTup.length);
			arr++;
		}
		return result;
	}
}
