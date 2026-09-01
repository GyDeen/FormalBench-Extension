
import java.io.*;
import java.lang.*;
import java.math.*;

class MaxProductTuple {

	public static int maxProductTuple(int[][] tuples) {
		int ret = 0;
		for (int[] i : tuples) {
			int sum = i[0];
			for (int index : i) {
				if (index < sum) {
					sum = index;
				}
			}
			int total = sum * i[i.length - 1];
			if (total > ret) {
				ret = total;
			}
		}
		return ret;
	}
}
