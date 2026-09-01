
import java.io.*;
import java.lang.*;
import java.util.*;

class MaxOfNth {

	public static int maxOfNth(int[][] testArray, int n) {
		int max = Integer.MIN_VALUE;
		int min = 0;
		while (min < testArray.length) {
			max = Math.max(max, testArray[min][n]);
			min++;
		}
		return max;
	}
}
