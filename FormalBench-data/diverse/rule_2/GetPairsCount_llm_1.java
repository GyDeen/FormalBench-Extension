
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetPairsCount {

	public static int getPairsCount(int[] arr, int n, int sum) {
		int count = 0;
		int result = 0;
		while (result < n) {
			for (int j = result + 1; j < n; j++) {
				if (arr[result] + arr[j] == sum) {
					count++;
				}
			}
			result++;
		}
		return count;
	}
}
