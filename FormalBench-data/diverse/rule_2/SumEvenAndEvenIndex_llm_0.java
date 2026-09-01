
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumEvenAndEvenIndex {

	public static int sumEvenAndEvenIndex(int[] arr, int n) {
		int count = 0;
		int total = 0;
		while (total < n) {
			if (total % 2 == 0) {
				if (arr[total] % 2 == 0) {
					count += arr[total];
				}
			}
			total++;
		}
		return count;
	}
}
