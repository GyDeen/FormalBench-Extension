
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddLengthSum {

	public static int oddLengthSum(int[] array) {
		int odd = 0;
		int max = array.length;
		for (int n = 0; n < max; n++) {
			odd += ((((n + 1) * (max - n) + 1) / 2)
					* array[n]);
		}
		return odd;
	}
}
