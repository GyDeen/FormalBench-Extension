
import java.io.*;
import java.lang.*;
import java.math.*;

class MaxProductTuple {

	public static int maxProductTuple(int[][] tuples) {
		int ret = 0;
		for (int[] tu : tuples) {
			int sum = tu[0];
			for (int index : tu) {
				if (index < sum) {
					sum = index;
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
