
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaximizeElements {

	public static int[][] maximizeElements(int[][] testTup1, int[][] test) {
		int num = testTup1.length;
		int[][] output = new int[num][];

		for (int i = 0; i < num; i++) {
			int[] tuple1 = testTup1[i];
			int[] tuple = test[i];
			int length = Math.max(tuple1.length, tuple.length);
			output[i] = new int[length];
			for (int len = 0; len < length; len++) {
				output[i][len] = (len < tuple1.length ? tuple1[len]
						: Integer.MIN_VALUE);
				if (len < tuple.length) {
					output[i][len] = Math.max(output[i][len],
							tuple[len]);
				}
			}
		}

		return output;
	}
}
