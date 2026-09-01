import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EvenBinomialCoeffSum {

	public static int evenBinomialCoeffSum(int seed) {
		int result = 0;
		int[] arr = new int[seed + 1];
		arr[0] = 1;
		for (int d = 1; d <= seed; d++) {
			for (int c = 0; c < d; c++) {
				arr[d] = arr[d] + arr[c];
			}
		}
		return arr[seed];
	}
}
