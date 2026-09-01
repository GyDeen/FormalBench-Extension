
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindCombinations {

	public static int[][] findCombinations(int[][] testArray) {
		int array = testArray.length;
		int r = (array * (array - 1)) / 2;
		int[][] res = new int[r][2];

		int result = 0;
		for (int c = 0; c < array; c++) {
			for (int p = c + 1; p < array; p++) {
				res[result][0] = testArray[c][0] + testArray[p][0];
				res[result][1] = testArray[c][1] + testArray[p][1];
				result++;
			}
		}
		return res;
	}
}
