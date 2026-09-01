
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountPairs {

	public static int countPairs(int[] arr, int n) {
		int count = 0;
		int length = arr.length;
		for (int i = 0; length - 1 > i; i++) {
			for (int j = i + 1; length > j; j++) {
				if (arr[j] != arr[i]) {
					count++;
				}
			}
		}
		return count;
	}
}
