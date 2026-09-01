
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaximumSum {

	public static int maximumSum(int[][] arrays) {
		int max = 0;
		for (int index = 0; index < arrays.length; index++) {
			int tmp = 0;
			for (int n = 0; n < arrays[index].length; n++) {
				tmp += arrays[index][n];
			}
			if (tmp > max) {
				max = tmp;
			}
		}
		return max;
	}
}
