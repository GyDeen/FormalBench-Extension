
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumEvenAndEvenIndex {

	public static int sumEvenAndEvenIndex(int[] arr, int n) {
		int count = 0;
		for (int i = 0; i < n; i++) {
			int index = i % 2;
			if (index == 0) {
				int tmp = arr[i] % 2;
				if (tmp == 0) {
					count += arr[i];
				}
			}
		}
		return count;
	}
}
