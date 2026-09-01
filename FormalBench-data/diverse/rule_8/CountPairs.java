
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountPairs {

	public static int countPairs(int[] arr, int n) {
		int count = 0;
		int length = arr.length;
		for (int i = 0; i < length - 1; i++) {
			for (int j = i + 1; j < length; j++) {
				if (arr[i] != arr[j]) {
					count += 1;
				}
			}
		}
		return count;
	}
}
