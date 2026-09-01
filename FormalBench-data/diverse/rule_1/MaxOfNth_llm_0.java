
import java.io.*;
import java.lang.*;
import java.util.*;

class MaxOfNth {

	public static int maxOfNth(int[][] array, int num) {
		int ret = Integer.MIN_VALUE;
		for (int test = 0; test < array.length; test++) {
			ret = Math.max(ret, array[test][num]);
		}
		return ret;
	}
}
