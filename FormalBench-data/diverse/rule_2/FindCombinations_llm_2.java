
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
		int array = 0;
		while (array < n) {
			for (int j = array + 1; j < n; j++) {
				res[index][0] = testArray[array][0] + testArray[j][0];
				res[index][1] = testArray[array][1] + testArray[j][1];
				index++;
			}
			array++;
		}
		return res;
	}
}
