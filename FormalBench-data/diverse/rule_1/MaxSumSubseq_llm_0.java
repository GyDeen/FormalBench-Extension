
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSumSubseq {

	public static int maxSumSubseq(int[] arr) {
		int ret = arr.length;
		if (ret == 0)
			return 0;
		if (ret == 1)
			return arr[0];

		int[] b = new int[ret + 1];
		b[0] = 0;
		b[1] = arr[0];

		for (int d = 2; d <= ret; d++) {
			b[d] = Math.max(b[d - 1],
					b[d - 2] + arr[d - 1]);
		}
		return b[ret];
	}
}
