
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumEvenAndEvenIndex {

	public static int sumEvenAndEvenIndex(int[] arr, int n) {
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (0 == i % 2) {
				if (0 == arr[i] % 2) {
					count += arr[i];
				}
			}
		}
		return count;
	}
}
