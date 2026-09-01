
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaximumSum {

	public static int maximumSum(int[][] array) {
		int ret = 0;
		for (int index = 0; index < array.length; index++) {
			int tmp = 0;
			for (int n = 0; n < array[index].length; n++) {
				tmp += array[index][n];
			}
			if (tmp > ret) {
				ret = tmp;
			}
		}
		return ret;
	}
}
