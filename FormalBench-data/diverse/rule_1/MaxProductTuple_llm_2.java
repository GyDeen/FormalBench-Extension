
import java.io.*;
import java.lang.*;
import java.math.*;

class MaxProductTuple {

	public static int maxProductTuple(int[][] tuples) {
		int ret = 0;
		for (int[] tu : tuples) {
			int sum = tu[0];
			for (int len : tu) {
				if (len < sum) {
					sum = len;
				}
			}
			int total = sum * tu[tu.length - 1];
			if (total > ret) {
				ret = total;
			}
		}
		return ret;
	}
}
