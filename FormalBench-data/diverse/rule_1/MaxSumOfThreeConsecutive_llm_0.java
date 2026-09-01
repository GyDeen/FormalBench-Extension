
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSumOfThreeConsecutive {

	public static int maxSumOfThreeConsecutive(int[] args, int num) {
		int[] tmp = new int[num];
		if (num >= 1) {
			tmp[0] = args[0];
		}
		if (num >= 2) {
			tmp[1] = tmp[0] + args[1];
		}
		if (num > 2) {
			tmp[2] = Math.max(tmp[1],
					Math.max(args[1] + args[2], args[0] + args[2]));
		}
		for (int index = 3; index < num; index++) {
			tmp[index] = Math.max(
					Math.max(tmp[index - 1],
							tmp[index - 2] + args[index]),
					args[index] + args[index - 1]
							+ tmp[index - 3]);
		}
		return tmp[num - 1];
	}
}
