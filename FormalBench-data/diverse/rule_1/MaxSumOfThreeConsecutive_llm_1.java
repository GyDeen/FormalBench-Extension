
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSumOfThreeConsecutive {

	public static int maxSumOfThreeConsecutive(int[] values, int num) {
		int[] tmp = new int[num];
		if (num >= 1) {
			tmp[0] = values[0];
		}
		if (num >= 2) {
			tmp[1] = tmp[0] + values[1];
		}
		if (num > 2) {
			tmp[2] = Math.max(tmp[1],
					Math.max(values[1] + values[2], values[0] + values[2]));
		}
		for (int index = 3; index < num; index++) {
			tmp[index] = Math.max(
					Math.max(tmp[index - 1],
							tmp[index - 2] + values[index]),
					values[index] + values[index - 1]
							+ tmp[index - 3]);
		}
		return tmp[num - 1];
	}
}
