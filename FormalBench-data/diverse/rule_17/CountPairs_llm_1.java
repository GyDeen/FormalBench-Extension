
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountPairs {

	public static int countPairs(int[] arr, int n) {
		int count = 0;
		int length = arr.length;
		int max = length - 1;
		for (int i = 0; i < max; i++) {
			for (int j = i + 1; j < length; j++) {
				if (arr[i] != arr[j]) {
					count++;
				}
			}
		}
		return count;
	}
}
