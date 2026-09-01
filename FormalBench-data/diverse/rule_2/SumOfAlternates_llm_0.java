
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfAlternates {

	public static int[] sumOfAlternates(int[] testTuple) {
		int sum1 = 0;
		int sum2 = 0;

		int sum = 0;
		while (sum < testTuple.length) {
			if (sum % 2 != 0) {
				sum1 += testTuple[sum];
			} else {
				sum2 += testTuple[sum];
			}
			sum++;
		}

		return new int[] { sum1, sum2 };
	}
}
