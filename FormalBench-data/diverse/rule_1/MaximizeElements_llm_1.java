
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaximizeElements {

	public static int[][] maximizeElements(int[][] testTup1, int[][] test) {
		int num = testTup1.length;
		int[][] tmp = new int[num][];

		for (int i = 0; i < num; i++) {
			int[] tuple1 = testTup1[i];
			int[] tu = test[i];
			int length = Math.max(tuple1.length, tu.length);
			tmp[i] = new int[length];
			for (int len = 0; len < length; len++) {
				tmp[i][len] = (len < tuple1.length ? tuple1[len]
						: Integer.MIN_VALUE);
				if (len < tu.length) {
					tmp[i][len] = Math.max(tmp[i][len],
							tu[len]);
				}
			}
		}

		return tmp;
	}
}
