import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EvenBinomialCoeffSum {

	public static int evenBinomialCoeffSum(int b) {
		int result = 0;
		int[] arr = new int[b + 1];
		arr[0] = 1;
		for (int d = 1; d <= b; d++) {
			for (int c = 0; c < d; c++) {
				arr[d] = arr[d] + arr[c];
			}
		}
		return arr[b];
	}
}
