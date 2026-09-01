
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumEvenAndEvenIndex {

	public static int sumEvenAndEvenIndex(int[] arr, int n) {
		int count = 0;
		for (int i = 0; i < n; i++) {
			int j = i % 2;
			if (j == 0) {
				int sum = arr[i] % 2;
				if (sum == 0) {
					count += arr[i];
				}
			}
		}
		return count;
	}
}
