
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
		int count = 0;
		while (count < n) {
			for (int j = count + 1; j < n; j++) {
				res[index][0] = testArray[count][0] + testArray[j][0];
				res[index][1] = testArray[count][1] + testArray[j][1];
				index++;
			}
			count++;
		}
		return res;
	}
}
