
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BigDiff {

	public static int bigDiff(int[] arr) {
		int result = arr[0];
		int num = arr[0];
		for (int n = 1; n < arr.length; n++) {
			if (arr[n] > result) {
				result = arr[n];
			}
			if (arr[n] < num) {
				num = arr[n];
			}
		}
		return result - num;
	}
}
