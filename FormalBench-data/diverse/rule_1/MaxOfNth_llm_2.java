
import java.io.*;
import java.lang.*;
import java.util.*;

class MaxOfNth {

	public static int maxOfNth(int[][] array, int num) {
		int ret = Integer.MIN_VALUE;
		for (int N = 0; N < array.length; N++) {
			ret = Math.max(ret, array[N][num]);
		}
		return ret;
	}
}
