
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ZipTuples {

	public static int[][] zipTuples(int[] testTup1, int[] testTup2) {
		int numRows = testTup1.length;
		int[][] res = new int[numRows][2];

		int num = 0;
		while (num < numRows) {
			res[num][0] = testTup1[num];
			res[num][1] = testTup2[num % testTup2.length];
			num++;
		}
		return res;
	}
}
