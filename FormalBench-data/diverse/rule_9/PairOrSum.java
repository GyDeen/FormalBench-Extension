
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PairOrSum {

	public static int pairOrSum(int[] arr, int n) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				sum = sum + (arr[i] ^ arr[j]);
			}
		}
		return sum;
	}
}
