
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindCombinations {

	public static int[][] findCombinations(int[][] testArray) {
		int n = testArray.length;
		int result = n * (n - 1);
		int combinationCount = (result) / 2;
		int[][] res = new int[combinationCount][2];

		int index = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				res[index][0] = testArray[i][0] + testArray[j][0];
				res[index][1] = testArray[i][1] + testArray[j][1];
				index++;
			}
		}
		return res;
	}
}
