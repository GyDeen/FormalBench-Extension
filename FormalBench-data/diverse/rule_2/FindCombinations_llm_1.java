
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindCombinations {

	public static int[][] findCombinations(int[][] testArray) {
		int n = testArray.length;
		int combinationCount = (n * (n - 1)) / 2;
		int[][] res = new int[combinationCount][2];

		int index = 0;
		int num = 0;
		while (num < n) {
			for (int j = num + 1; j < n; j++) {
				res[index][0] = testArray[num][0] + testArray[j][0];
				res[index][1] = testArray[num][1] + testArray[j][1];
				index++;
			}
			num++;
		}
		return res;
	}
}
