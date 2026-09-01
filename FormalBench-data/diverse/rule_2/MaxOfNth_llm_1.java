
import java.io.*;
import java.lang.*;
import java.util.*;

class MaxOfNth {

	public static int maxOfNth(int[][] testArray, int n) {
		int max = Integer.MIN_VALUE;
		int sum = 0;
		while (sum < testArray.length) {
			max = Math.max(max, testArray[sum][n]);
			sum++;
		}
		return max;
	}
}
