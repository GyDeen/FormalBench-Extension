
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSumOfThreeConsecutive {

	public static int maxSumOfThreeConsecutive(int[] args, int num) {
		int[] ret = new int[num];
		if (num >= 1) {
			ret[0] = args[0];
		}
		if (num >= 2) {
			ret[1] = ret[0] + args[1];
		}
		if (num > 2) {
			ret[2] = Math.max(ret[1],
					Math.max(args[1] + args[2], args[0] + args[2]));
		}
		for (int index = 3; index < num; index++) {
			ret[index] = Math.max(
					Math.max(ret[index - 1],
							ret[index - 2] + args[index]),
					args[index] + args[index - 1]
							+ ret[index - 3]);
		}
		return ret[num - 1];
	}
}
