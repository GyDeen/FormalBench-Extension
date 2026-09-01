
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetPairsCount {

	public static int getPairsCount(int[] arr, int n, int sum) {
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				int p = arr[i] + arr[j];
				if (p == sum) {
					count++;
				}
			}
		}
		return count;
	}
}
