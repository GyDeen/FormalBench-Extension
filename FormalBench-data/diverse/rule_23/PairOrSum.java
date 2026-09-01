
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PairOrSum {

	public static int pairOrSum(int[] arr, int n) {
		int sum = 0;
		for (int i = 0; arr.length > i; i++) {
			for (int j = i + 1; arr.length > j; j++) {
				sum += arr[i] ^ arr[j];
			}
		}
		return sum;
	}
}
