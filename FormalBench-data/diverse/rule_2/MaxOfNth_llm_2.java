
import java.io.*;
import java.lang.*;
import java.util.*;

class MaxOfNth {

	public static int maxOfNth(int[][] testArray, int n) {
		int max = Integer.MIN_VALUE;
		int tmp = 0;
		while (tmp < testArray.length) {
			max = Math.max(max, testArray[tmp][n]);
			tmp++;
		}
		return max;
	}
}
