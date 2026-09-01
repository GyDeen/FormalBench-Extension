
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PairOrSum {

	public static int pairOrSum(int[] arr, int n) {
		int sum = 0;
		int max = 0;
		while (max < arr.length) {
			for (int j = max + 1; j < arr.length; j++) {
				sum += arr[max] ^ arr[j];
			}
			max++;
		}
		return sum;
	}
}
