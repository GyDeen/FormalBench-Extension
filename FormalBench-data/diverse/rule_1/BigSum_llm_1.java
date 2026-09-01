
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BigSum {

	public static int bigSum(int[] arr) {
		int ret = Integer.MIN_VALUE;
		int sum = Integer.MAX_VALUE;
		for (int num = 0; num < arr.length; num++) {
			if (arr[num] > ret) {
				ret = arr[num];
			}
			if (arr[num] < sum) {
				sum = arr[num];
			}
		}
		return ret + sum;
	}
}
