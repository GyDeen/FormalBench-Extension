import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EvenBinomialCoeffSum {

	public static int evenBinomialCoeffSum(int seed) {
		int result = 0;
		int[] temp = new int[seed + 1];
		temp[0] = 1;
		for (int d = 1; d <= seed; d++) {
			for (int c = 0; c < d; c++) {
				temp[d] = temp[d] + temp[c];
			}
		}
		return temp[seed];
	}
}
