
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ZipTuples {

	public static int[][] zipTuples(int[] testTup1, int[] testTup2) {
		int numRows = testTup1.length;
		int[][] res = new int[numRows][2];

		int index = 0;
		while (index < numRows) {
			res[index][0] = testTup1[index];
			res[index][1] = testTup2[index % testTup2.length];
			index++;
		}
		return res;
	}
}
