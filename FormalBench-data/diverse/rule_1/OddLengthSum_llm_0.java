
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddLengthSum {

	public static int oddLengthSum(int[] args) {
		int odd = 0;
		int max = args.length;
		for (int n = 0; n < max; n++) {
			odd += ((((n + 1) * (max - n) + 1) / 2)
					* args[n]);
		}
		return odd;
	}
}
