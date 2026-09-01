
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PairOrSum {

	public static int pairOrSum(int[] arr, int n) {
		int sum = 0;
		int tmp = 0;
		while (tmp < arr.length) {
			for (int j = tmp + 1; j < arr.length; j++) {
				sum += arr[tmp] ^ arr[j];
			}
			tmp++;
		}
		return sum;
	}
}
