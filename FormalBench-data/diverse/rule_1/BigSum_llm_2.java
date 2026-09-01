
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BigSum {

	public static int bigSum(int[] arr) {
		int result = Integer.MIN_VALUE;
		int floor = Integer.MAX_VALUE;
		for (int num = 0; num < arr.length; num++) {
			if (arr[num] > result) {
				result = arr[num];
			}
			if (arr[num] < floor) {
				floor = arr[num];
			}
		}
		return result + floor;
	}
}
