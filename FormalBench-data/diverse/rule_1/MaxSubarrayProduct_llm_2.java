
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSubarrayProduct {

	public static int maxSubarrayProduct(int[] args) {
		int max = Integer.MIN_VALUE, break = Integer.MAX_VALUE, total = 0;

		for (int n = 0; n < args.length; n++) {
			if (args[n] < 0) {
				int r = max;
				max = break;
				break = r;
			}

			max = Math.max(max * args[n], args[n]);
			break = Math.min(break * args[n],
					args[n]);
			total = Math.max(total, max);
		}
		return total;
	}
}
