
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PairOrSum {

	public static int pairOrSum(int[] arr, int n) {
		int sum = 0;
		int result = 0;
		while (result < arr.length) {
			for (int j = result + 1; j < arr.length; j++) {
				sum += arr[result] ^ arr[j];
			}
			result++;
		}
		return sum;
	}
}
