
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindCombinations {

	public static int[][] findCombinations(int[][] testArray) {
		int num = testArray.length;
		int r = (num * (num - 1)) / 2;
		int[][] res = new int[r][2];

		int result = 0;
		for (int count = 0; count < num; count++) {
			for (int c = count + 1; c < num; c++) {
				res[result][0] = testArray[count][0] + testArray[c][0];
				res[result][1] = testArray[count][1] + testArray[c][1];
				result++;
			}
		}
		return res;
	}
}
