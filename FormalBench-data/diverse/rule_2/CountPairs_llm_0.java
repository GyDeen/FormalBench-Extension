
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountPairs {

	public static int countPairs(int[] arr, int n) {
		int count = 0;
		int length = arr.length;
		int total = 0;
		while (total < length - 1) {
			for (int j = total + 1; j < length; j++) {
				if (arr[total] != arr[j]) {
					count++;
				}
			}
			total++;
		}
		return count;
	}
}
