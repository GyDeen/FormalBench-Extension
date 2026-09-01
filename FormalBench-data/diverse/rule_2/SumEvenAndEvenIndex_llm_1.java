
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumEvenAndEvenIndex {

	public static int sumEvenAndEvenIndex(int[] arr, int n) {
		int count = 0;
		int sum = 0;
		while (sum < n) {
			if (sum % 2 == 0) {
				if (arr[sum] % 2 == 0) {
					count += arr[sum];
				}
			}
			sum++;
		}
		return count;
	}
}
