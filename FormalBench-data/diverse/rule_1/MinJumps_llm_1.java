
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinJumps {

	public static int minJumps(int[] args, int num) {
		int[] ret = new int[num];
		Arrays.fill(ret, Integer.MAX_VALUE);
		ret[0] = 0;

		for (int array = 1; array < num; array++) {
			for (int c = 0; c < array; c++) {
				if (args[c] + c >= array) {
					ret[array] = Math.min(ret[array],
							ret[c] + 1);
				}
			}
		}
		return ret[num - 1];
	}
}
