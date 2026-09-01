
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetPairsCount {

	public static int getPairsCount(int[] arr, int n, int sum) {
		int count = 0;
		int total = 0;
		while (total < n) {
			for (int j = total + 1; j < n; j++) {
				if (arr[total] + arr[j] == sum) {
					count++;
				}
			}
			total++;
		}
		return count;
	}
}
