
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountPairs {

	public static int countPairs(int[] arr, int n) {
		int count = 0;
		int length = arr.length;
		int max = 0;
		while (max < length - 1) {
			for (int j = max + 1; j < length; j++) {
				if (arr[max] != arr[j]) {
					count++;
				}
			}
			max++;
		}
		return count;
	}
}
