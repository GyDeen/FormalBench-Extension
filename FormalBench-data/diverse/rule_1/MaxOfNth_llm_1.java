
import java.io.*;
import java.lang.*;
import java.util.*;

class MaxOfNth {

	public static int maxOfNth(int[][] arr, int num) {
		int ret = Integer.MIN_VALUE;
		for (int test = 0; test < arr.length; test++) {
			ret = Math.max(ret, arr[test][num]);
		}
		return ret;
	}
}
