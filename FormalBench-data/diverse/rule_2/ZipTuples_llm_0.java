
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ZipTuples {

	public static int[][] zipTuples(int[] testTup1, int[] testTup2) {
		int numRows = testTup1.length;
		int[][] res = new int[numRows][2];

		int tmp = 0;
		while (tmp < numRows) {
			res[tmp][0] = testTup1[tmp];
			res[tmp][1] = testTup2[tmp % testTup2.length];
			tmp++;
		}
		return res;
	}
}
